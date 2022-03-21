package com.example.laladui;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;


import com.google.android.material.navigation.NavigationView;

import static com.example.laladui.SocketTool.CREDIT;


public class FragmentShipin extends Fragment {
    private View rootView;
    private DrawerLayout mDrawerLayout;
    private FrameLayout contentFrameLayout;
    private Toolbar toolbar;
    private CardView cardView;
    private NavigationView navigationView;
    private ListView listView;
    private VideoView videoView;
    //private static int credit=0;//积分
    private Intent intent;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_shipin, container, false);
        iniDrawer();
        playVideo(R.raw.shipin1);


        return rootView;

    }

    public void iniDrawer(){
        mDrawerLayout = rootView.findViewById(R.id.drawer_layout);
        mDrawerLayout.setScrimColor(Color.TRANSPARENT); // 菜单滑动时content不被阴影覆盖
        cardView=rootView.findViewById(R.id.card_view);
        setHasOptionsMenu(true);//显示菜单
        toolbar = rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        navigationView=rootView.findViewById(R.id.nav_view);
        ActionBar actionBar=((AppCompatActivity) getActivity()).getSupportActionBar();//获得ActionBar的实例
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);//显示导航按钮

            actionBar.setHomeAsUpIndicator(R.drawable.caidan);//设置导航按钮图标
            actionBar.setTitle("视频" );
        }
        navigationView.setCheckedItem(R.id.five);//设置默认选中
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.five:
                        mDrawerLayout.closeDrawers();
                        playVideo(R.raw.shipin1);
                        Toast.makeText(getContext(),"该视频为五积分视频",Toast.LENGTH_SHORT).show();
                        CREDIT+=5;
                        Log.d("yyy","credit1="+CREDIT);

                        break;
                    case R.id.ten:
                        mDrawerLayout.closeDrawers();
                        playVideo(R.raw.shipin2);
                        Toast.makeText(getContext(),"该视频为十积分视频",Toast.LENGTH_SHORT).show();
                        CREDIT+=10;
                        Log.d("yyy","credit2="+CREDIT);
                        break;
                    case R.id.fifteen:
                        mDrawerLayout.closeDrawers();
                        playVideo(R.raw.shipin4);
                        Toast.makeText(getContext(),"该视频为十五积分视频",Toast.LENGTH_SHORT).show();
                        CREDIT+=15;
                        Log.d("yyy","credit3="+CREDIT);
                        break;
                    case R.id.twenty:
                        mDrawerLayout.closeDrawers();
                        playVideo(R.raw.shipin3);
                        Toast.makeText(getContext(),"该视频为二十积分视频",Toast.LENGTH_SHORT).show();
                        CREDIT+=20;
                        Log.d("yyy","credit4="+CREDIT);
                        break;
                    default:
                        break;

                }

                return true;
            }
        });
        clickDrawer();
    }
    public void clickDrawer(){

        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View mContent = mDrawerLayout.getChildAt(0);
                View mMenu = drawerView;
                float scale = 1 - slideOffset;
                float rightScale = 0.8f + scale * 0.2f;
                float leftScale = 0.5f + slideOffset * 0.5f;
                mMenu.setAlpha(leftScale);
                mMenu.setScaleX(leftScale);
                mMenu.setScaleY(leftScale);
                mContent.setPivotX(0);
                mContent.setPivotY(mContent.getHeight() * 1 / 2);
                mContent.setScaleX(rightScale);
                mContent.setScaleY(rightScale);
                mContent.setTranslationX(mMenu.getWidth() * slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                cardView.setRadius(20);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                cardView.setRadius(0);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**播放 res/raw 目录下的文件
     * android.resource:// ：前缀固定
     * com.example.administrator.helloworld：为当前类的所在的包路径，可以使用 String packageName = getPackageName(); 动态获取
     * R.raw.shipin：最后接 res/raw 目录中的文件名
     * */
    public void playVideo(int path) {
        videoView = rootView.findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + path));

         //为 VideoView 视图设置媒体控制器，设置了之后就会自动由进度条、前进、后退等操作
        videoView.setMediaController(new MediaController(getContext()));

        //视频准备完成时回调
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.i("tag", "--------------视频准备完毕,可以进行播放.......");
            }
        });

         //视频播放完成时回调
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.i("tag", "------------------视频播放完毕..........");
                Toast.makeText(getContext(),"视频播放完毕加上相应积分",Toast.LENGTH_SHORT).show();
                /**播放完成时，再次循环播放*/
                videoView.suspend();
            }
        });


         //视频播放发送错误时回调
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.i("tag", "---------------------视频播放失败...........");
                Toast.makeText(getContext(),"视频播放错误",Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //开始播放视频
        videoView.start();
    }

}

