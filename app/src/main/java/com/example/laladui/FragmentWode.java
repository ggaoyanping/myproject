package com.example.laladui;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.example.laladui.SocketTool.CREDIT;

public class FragmentWode extends Fragment {
    private View rootView;
    ImageView blurImageView;
    ImageView avatarImageView;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private TextView textView_credit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_wode,container,false);
        textView_credit=rootView.findViewById(R.id.user_val);
        textView_credit.setText("积分:"+SocketTool.CREDIT);
        return rootView;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //实现个人中心头部磨砂布局
        blurImageView= rootView.findViewById(R.id.iv_blur);
        avatarImageView = rootView.findViewById(R.id.iv_avatar);
        Glide.with(getActivity()).load(R.drawable.wawaji).bitmapTransform(new BlurTransformation(getActivity(), 25), new CenterCrop(getActivity())).into(blurImageView);
        Glide.with(getActivity()).load(R.drawable.wawaji).bitmapTransform(new CropCircleTransformation(getActivity())).into(avatarImageView);
        iniToolbar();
    }
    public void iniToolbar(){
        setHasOptionsMenu(true);//自定义标题栏必须调用此方法，用于显示菜单
        toolbar = rootView.findViewById(R.id.toolbar5);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        actionBar=((AppCompatActivity) getActivity()).getSupportActionBar();//获得ActionBar的实例
        if(actionBar!=null){
            //actionBar.setDisplayHomeAsUpEnabled(true);//显示导航按钮
            //actionBar.setHomeAsUpIndicator(R.drawable.caidan);//设置导航按钮图标
            actionBar.setTitle("个人中心" );
        }
    }

}
