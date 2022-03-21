package com.example.laladui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.*;
import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class NavigationActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioButton zixun,chaxun,shipin,wode;
    private Drawable drawable_zixun,drawable_chaxun,drawable_shipin,drawable_wode;

    private static String mzixun = "mzixunFragment";//登录默认首页
    protected ImageView yuan;

    private List<Fragment> mFragments=new ArrayList<Fragment>();
    private FragmentZixun fragmentZixun;
    private FragmentChaxun fragmentChaxun;
    private FragmentYouxi fragmentYouxi;
    private FragmentShipin fragmentShipin;
    private FragmentWode fragmentWode;

    private FragmentManager fm;
    //FragmentTransaction transaction;
    private RadioGroup mRadioButtonRg;
    private FragmentTransaction transaction1;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        initView();
        //mFragments=getFragments();
        //mRadioButtonRg.setOnCheckedChangeListener(this);
        if(savedInstanceState==null){
            FragmentManager fragmentManager=getSupportFragmentManager();
            fragmentZixun=new FragmentZixun();
            fragmentManager.beginTransaction().replace(R.id.fl,fragmentZixun,mzixun).commit();
        }

    }


     //初始化控件
    private void initView() {
        mRadioButtonRg = (RadioGroup) findViewById(R.id.radioGrop);
        mRadioButtonRg.setOnCheckedChangeListener(this);
        fm = getSupportFragmentManager();//获得Fragment管理器
        transaction1 = fm.beginTransaction();
        fragmentZixun = (FragmentZixun) fm.findFragmentByTag("mzixun");
        fragmentChaxun= (FragmentChaxun) fm.findFragmentByTag("mchaxun");
        fragmentYouxi = (FragmentYouxi) fm.findFragmentByTag("myouxi");
        fragmentShipin = (FragmentShipin) fm.findFragmentByTag("mshipin");
        fragmentWode = (FragmentWode) fm.findFragmentByTag("mwode");
        yuan = (ImageView) findViewById(R.id.yuan);

        zixun = findViewById(R.id.zixun);
        //定义底部标签图片大小和位置
        drawable_zixun = getResources().getDrawable(R.drawable.zinan);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_zixun.setBounds(0, 0, 80, 80);
        //设置图片在文字的哪个方向
        zixun.setCompoundDrawables(null, drawable_zixun, null, null);

        //查询按钮
        chaxun = (RadioButton) findViewById(R.id.chaxun);
        drawable_chaxun = getResources().getDrawable(R.drawable.chaxun);
        drawable_chaxun.setBounds(0, 0, 80, 80);
        chaxun.setCompoundDrawables(null, drawable_chaxun, null, null);

        //视频按钮
        shipin = (RadioButton) findViewById(R.id.shipin);
        drawable_shipin = getResources().getDrawable(R.drawable.shipin);
        drawable_shipin.setBounds(0, 0, 80, 80);
        shipin.setCompoundDrawables(null, drawable_shipin, null, null);

        //我的按钮
        wode = (RadioButton) findViewById(R.id.wode);
        drawable_wode = getResources().getDrawable(R.drawable.wode);
        drawable_wode.setBounds(0, 0, 80, 80);
        wode.setCompoundDrawables(null, drawable_wode, null, null);

    }
    //页面切换事件处理
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        transaction1=fm.beginTransaction();//开启一个事务
        if(fragmentZixun!=null){
            transaction1.hide(fragmentZixun);
        }
        if(fragmentChaxun!=null){
            transaction1.hide(fragmentChaxun);
        }
        if(fragmentYouxi!=null){
            transaction1.hide(fragmentYouxi);
        }
        if(fragmentShipin!=null){
            transaction1.hide(fragmentShipin);
        }
        if(fragmentWode!=null){
            transaction1.hide(fragmentWode);
        }
        switch (checkedId){
            case R.id.zixun://资讯按钮,选中资讯,把其他的变灰
                drawable_zixun = getResources().getDrawable(R.drawable.zinan1);
                drawable_zixun.setBounds(0, 0, 80, 80);//60,60为宽高
                zixun.setCompoundDrawables(null, drawable_zixun, null, null);

                //把另外三个变灰，查询显示灰色
                drawable_chaxun = getResources().getDrawable(R.drawable.chaxun);
                drawable_chaxun.setBounds(0, 0, 80, 80);//60,60为宽高
                chaxun.setCompoundDrawables(null, drawable_chaxun, null, null);

                //视频显示灰色
                drawable_shipin = getResources().getDrawable(R.drawable.shipin);
                drawable_shipin.setBounds(0, 0, 80, 80);//60,60为宽高
                shipin.setCompoundDrawables(null, drawable_shipin, null, null);

                //我的显示灰色
                drawable_wode = getResources().getDrawable(R.drawable.wode);
                drawable_wode.setBounds(0, 0, 80, 80);//60,60为宽高
                wode.setCompoundDrawables(null, drawable_wode, null, null);

                if(fragmentZixun==null){
                    fragmentZixun=new FragmentZixun();
                    transaction1.add(R.id.fl,fragmentZixun,mzixun);

                }else {
                    transaction1.show(fragmentZixun);
                }

                yuan.setImageResource(R.drawable.yuan);

                break;

            case R.id.chaxun://查询按钮,选中查询,把其他的变灰
                drawable_chaxun = getResources().getDrawable(R.drawable.chaxun1);
                drawable_chaxun.setBounds(0, 0, 80, 80);//60,60为宽高
                chaxun.setCompoundDrawables(null, drawable_chaxun, null, null);

                drawable_zixun = getResources().getDrawable(R.drawable.zinan);
                drawable_zixun.setBounds(0, 0, 80, 80);//60,60为宽高
                zixun.setCompoundDrawables(null, drawable_zixun, null, null);

                //视频显示灰色
                drawable_shipin = getResources().getDrawable(R.drawable.shipin);
                drawable_shipin.setBounds(0, 0, 80, 80);//60,60为宽高
                shipin.setCompoundDrawables(null, drawable_shipin, null, null);

                //我的显示灰色
                drawable_wode = getResources().getDrawable(R.drawable.wode);
                drawable_wode.setBounds(0, 0, 80, 80);//60,60为宽高
                wode.setCompoundDrawables(null, drawable_wode, null, null);

                if(fragmentChaxun==null){
                    fragmentChaxun=new FragmentChaxun();
                    transaction1.add(R.id.fl,fragmentChaxun,"mchaxun");

                }else {
                    transaction1.show(fragmentChaxun);
                }

                yuan.setImageResource(R.drawable.yuan);
                break;

            case R.id.shipin://视频按钮,选中视频,
                drawable_shipin = getResources().getDrawable(R.drawable.shipin1);
                drawable_shipin.setBounds(0, 0, 80, 80);//60,60为宽高
                shipin.setCompoundDrawables(null, drawable_shipin, null, null);

                drawable_chaxun = getResources().getDrawable(R.drawable.chaxun);
                drawable_chaxun.setBounds(0, 0, 80, 80);//60,60为宽高
                chaxun.setCompoundDrawables(null, drawable_chaxun, null, null);

                //视频显示灰色
                drawable_zixun = getResources().getDrawable(R.drawable.zinan);
                drawable_zixun.setBounds(0, 0, 80, 80);//60,60为宽高
                zixun.setCompoundDrawables(null, drawable_zixun, null, null);

                //我的显示灰色
                drawable_wode = getResources().getDrawable(R.drawable.wode);
                drawable_wode.setBounds(0, 0, 80, 80);//60,60为宽高
                wode.setCompoundDrawables(null, drawable_wode, null, null);

                if(fragmentShipin==null){
                    fragmentShipin=new FragmentShipin();
                    transaction1.add(R.id.fl,fragmentShipin,"mshipin");
                }else {
                    transaction1.show(fragmentShipin);
                }


                yuan.setImageResource(R.drawable.yuan);
                break;

            case R.id.wode://我的按钮,选中我的,把其他的变灰
                drawable_wode = getResources().getDrawable(R.drawable.wode1);
                drawable_wode.setBounds(0, 0, 80, 80);//60,60为宽高
                wode.setCompoundDrawables(null, drawable_wode, null, null);

                drawable_chaxun = getResources().getDrawable(R.drawable.chaxun);
                drawable_chaxun.setBounds(0, 0, 80, 80);//60,60为宽高
                chaxun.setCompoundDrawables(null, drawable_chaxun, null, null);

                //视频显示灰色
                drawable_shipin = getResources().getDrawable(R.drawable.shipin);
                drawable_shipin.setBounds(0, 0, 80, 80);//60,60为宽高
                shipin.setCompoundDrawables(null, drawable_shipin, null, null);

                //我的显示灰色
                drawable_zixun = getResources().getDrawable(R.drawable.zinan);
                drawable_zixun.setBounds(0, 0, 80, 80);//60,60为宽高
                zixun.setCompoundDrawables(null, drawable_zixun, null, null);

                if(fragmentWode==null){
                    fragmentWode=new FragmentWode();
                    transaction1.add(R.id.fl,fragmentWode,"mwode");
                }else {
                    transaction1.show(fragmentWode);
                }

                yuan.setImageResource(R.drawable.yuan);
                break;
            case R.id.youxi://我的按钮,选中我的,把其他的变灰
                drawable_wode = getResources().getDrawable(R.drawable.wode);
                drawable_wode.setBounds(0, 0, 80, 80);//60,60为宽高
                wode.setCompoundDrawables(null, drawable_wode, null, null);

                drawable_chaxun = getResources().getDrawable(R.drawable.chaxun);
                drawable_chaxun.setBounds(0, 0, 80, 80);//60,60为宽高
                chaxun.setCompoundDrawables(null, drawable_chaxun, null, null);

                //视频显示灰色
                drawable_shipin = getResources().getDrawable(R.drawable.shipin);
                drawable_shipin.setBounds(0, 0, 80, 80);//60,60为宽高
                shipin.setCompoundDrawables(null, drawable_shipin, null, null);

                //我的显示灰色
                drawable_zixun = getResources().getDrawable(R.drawable.zinan);
                drawable_zixun.setBounds(0, 0, 80, 80);//60,60为宽高
                zixun.setCompoundDrawables(null, drawable_zixun, null, null);

                if(fragmentYouxi==null){
                    fragmentYouxi=new FragmentYouxi();
                    transaction1.add(R.id.fl,fragmentYouxi,"myouxi");
                }else {
                    transaction1.show(fragmentYouxi);
                }

                yuan.setImageResource(R.drawable.yuan);
                break;


        }
        transaction1.commit();//提交事务

    }

}
