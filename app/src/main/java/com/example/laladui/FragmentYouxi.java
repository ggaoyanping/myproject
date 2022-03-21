package com.example.laladui;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.laladui.youximenu.MenuView;
import static com.example.laladui.SocketTool.CREDIT;
import static com.example.laladui.youximenu.MenuView.socketTool;

public class FragmentYouxi extends Fragment {
    protected View rootView;
    protected Toolbar toolbar;
    private ActionBar actionBar;
    //private String credit_youxi;
    private MenuView menuView;
    private Button button_duihuan;
    //public  SocketTool socketTool = new SocketTool("121.196.104.55",7839);
    //public static int CREDIT = 0;
    //private FragmentShipin fragmentShipin;
   // private FragmentManager fm;
    //private Activity mCtx;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_youxi, container, false);
        iniToolbar();
        //fm = getActivity().getSupportFragmentManager();//获得Fragment管理器
        //fragmentShipin = (FragmentShipin)fm.findFragmentByTag("mshipin");
        menuView = rootView.findViewById(R.id.menu_view);
        Log.d("001", "当前积分" + SocketTool.CREDIT);

            menuView.setOnTouchBlockListener(new MenuView.OnTouchBlockListener() {

                @Override
                public void onTop() {

                }

                @Override
                public void onBottom() {

                }

                @Override
                public void onLeft() {

                }

                @Override
                public void onRight() {

                }

                @Override
                public void onCenter() {

                }
            });
            /*
       if (SocketTool.CREDIT<10){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //在runOnUiThread()中更新UI，弹出，进度条/提示框
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //progressDialog.dismiss();//前面点击登录按钮时出现进度条，这里登录失败进度条消失
                            //在登录界面生成对话框提示
                                //menuView = rootView.findViewById(R.id.menu_view);
                                new AlertDialog.Builder(getActivity())
                                        .setTitle("提示！！")
                                        .setMessage("积分需要大于10才能抓娃娃。看视频可以获得积分噢！"+"当前积分为" + CREDIT+"。")
                                        .setCancelable(true)
                                        .setNegativeButton("确定 ", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();//点击确定，对话框消失

                                            }
                                        }).create().show();
                        }
                    });
                }
            }).start();
        }
        */


        //兑换按钮的点击事件
        button_duihuan=rootView.findViewById(R.id.duihuan);
        //兑换的点击事件
        button_duihuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //在runOnUiThread()中更新UI，弹出，进度条/提示框
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //progressDialog.dismiss();//前面点击登录按钮时出现进度条，这里登录失败进度条消失
                                //在登录界面生成对话框提示
                                if (CREDIT<5){
                                    new AlertDialog.Builder(getActivity())
                                            .setTitle("提示")
                                            .setMessage("积分需要大于等于5才能兑换奖品哦！当前积分为"+CREDIT+"。")
                                            .setCancelable(true)
                                            .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    dialogInterface.dismiss();//点击确定，对话框消失
                                                }
                                            }).create().show();

                                }
                                else {
                                    //SocketTool socketTool=new SocketTool("121.196.104.55",7839);
                                    SocketTool.setSend1(view);//设置发送数据
                                    Log.d("ggg","测试发送数据是否成功");
                                   // SocketTool socketTool=new SocketTool("121.196.104.55",7839);//发送兑奖指令
                                    socketTool.send();
                                    Log.d("ggg","发送数据成功");
                                    CREDIT-=5;//较少相应积分
                                }

                            }
                        });
                    }
                }).start();

                SocketTool.setSend1(view);//设置发送数据
                Log.d("ggg","测试发送数据是否成功");
                // SocketTool socketTool=new SocketTool("121.196.104.55",7839);//发送兑奖指令
                socketTool.send();
                Log.d("ggg","发送数据成功");
                CREDIT-=5;//较少相应积分



            }
        });


        return rootView;
    }
    public void iniToolbar(){
        setHasOptionsMenu(true);//自定义标题栏必须调用此方法，用于显示菜单
        toolbar = rootView.findViewById(R.id.toolbar3);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        actionBar=((AppCompatActivity) getActivity()).getSupportActionBar();//获得ActionBar的实例
        if(actionBar!=null){
            //actionBar.setDisplayHomeAsUpEnabled(true);//显示导航按钮
            //actionBar.setHomeAsUpIndicator(R.drawable.caidan);//设置导航按钮图标
            actionBar.setTitle("游戏" );
        }
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_youxi, menu);
    }
    @Override
    //标题栏图标的点击事件
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saoyisao:

                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}


