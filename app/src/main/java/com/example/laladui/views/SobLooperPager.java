package com.example.laladui.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.laladui.R;

public class SobLooperPager extends LinearLayout {
    private ViewPager mViewPager;
    private LinearLayout mpointContainer;
    private TextView mtitleTv;
    private BindTitleListener mTitleSetListener=null;
    private InnerAdapter mInnerAdapter=null;

    public SobLooperPager(Context context) {
        this(context,null);
    }

    public SobLooperPager(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SobLooperPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.looper_pager_layout,this,true);//加载布局
        init();
    }

    private void init() {
        initView();
        initEvent();//设置页面切换的滑动监听
    }

    private void initEvent() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //切换的一个回调方法
            }

            @Override
            public void onPageSelected(int position) {
               // int realPosition=position%mInnerAdapter.getCount();
                //切换停下来的回调方法
                if(mInnerAdapter!=null) {
                    int realPosition = position % mInnerAdapter.getDataSize();
                    if (mTitleSetListener != null) {
                        mtitleTv.setText(mTitleSetListener.getTitle(realPosition));//停下来以后设置标题
                    }
                    //切换指示器焦点
                    updateIndicator();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //切换状态改变的回调
            }
        });
    }

    public interface BindTitleListener{
        String getTitle(int position);

    }

    public void setData(InnerAdapter innerAdapter,BindTitleListener listener){
        this.mTitleSetListener=listener;
        mViewPager.setAdapter(innerAdapter);
        mViewPager.setCurrentItem(Integer.MAX_VALUE/2+1);
        this.mInnerAdapter=innerAdapter;
        //拿到数据个数，动态设置圆点
        if(listener!=null){
            mtitleTv.setText(listener.getTitle(mViewPager.getCurrentItem()%mInnerAdapter.getDataSize()));
        }
        updateIndicator();
    }
    public abstract static class InnerAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View)object);
        }
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            int realPosition=position%getDataSize();
            View itemView=getSubView(container,realPosition);
            container.addView(itemView);

            return itemView;
        }

        protected abstract int getDataSize();

        protected abstract View getSubView(ViewGroup container,int position);
    }

    private void updateIndicator() {
        if(mInnerAdapter!=null&&mTitleSetListener!=null){
            int count=mInnerAdapter.getDataSize();
            mpointContainer.removeAllViews();
            for (int i=0;i<count;i++){
                View point=new View(getContext());
                if(mViewPager.getCurrentItem()%mInnerAdapter.getDataSize()==i) {
                    point.setBackgroundColor(Color.parseColor("#00574B"));
                }else {
                    point.setBackgroundColor(Color.parseColor("#ffffff"));
                }
                //设置大小
                LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(SizeUtils.dip2px(getContext(),5),SizeUtils.dip2px(getContext(),5));
                layoutParams.setMargins(SizeUtils.dip2px(getContext(),5),0,SizeUtils.dip2px(getContext(),5),0);
                point.setLayoutParams(layoutParams);
                //设置到容器里去
                mpointContainer.addView(point);
            }
        }
    }

    private void initView() {
        mViewPager=this.findViewById(R.id.looper_pager_vp);
        mpointContainer=this.findViewById(R.id.looper_point_container_lv);
        mtitleTv=this.findViewById(R.id.looper_title_tv);
    }
}
