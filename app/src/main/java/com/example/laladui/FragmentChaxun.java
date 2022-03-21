package com.example.laladui;


import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class FragmentChaxun extends Fragment {
    protected View rootView;
    protected SearchView searchView;
    protected ImageView imageView;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private Map<String,String> map;//垃圾数据

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView=inflater.inflate(R.layout.fragment_chaxun,container,false);

        iniToolbar();
        initData();
        iniSearch();
        return rootView;
    }
    public void iniToolbar(){
        setHasOptionsMenu(true);//自定义标题栏必须调用此方法，用于显示菜单
        toolbar = rootView.findViewById(R.id.toolbar2);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        actionBar=((AppCompatActivity) getActivity()).getSupportActionBar();//获得ActionBar的实例
        if(actionBar!=null){
            //actionBar.setDisplayHomeAsUpEnabled(true);//显示导航按钮
            //actionBar.setHomeAsUpIndicator(R.drawable.fanhui);//设置导航按钮图标
            //((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
            //((AppCompatActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
            actionBar.setTitle("查询分类" );
        }
    }
    public void iniSearch(){
        searchView=rootView.findViewById(R.id.sv);//加载搜索框
        searchView.setIconifiedByDefault(false);//设置不缩放
        //设置搜索文本监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           //点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                boolean flag=map.containsKey(query);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //在runOnUiThread()中更新UI，弹出，进度条/提示框
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //progressDialog.dismiss();//前面点击登录按钮时出现进度条，这里登录失败进度条消失
                                //在登录界面生成对话框提示
                                if (flag==true){
                                    new AlertDialog.Builder(getActivity())
                                            .setTitle("查询结果")
                                            .setMessage(map.get(query))
                                            .setCancelable(true)
                                            .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    dialogInterface.dismiss();//点击确定，对话框消失
                                                }
                                            }).create().show();

                                }
                                else {
                                    new AlertDialog.Builder(getActivity())
                                            .setTitle("查询结果")
                                            .setMessage("抱歉，未找到该垃圾类型")
                                            .setCancelable(true)
                                            .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    dialogInterface.dismiss();//点击确定，对话框消失
                                                }
                                            }).create().show();
                                }

                            }
                        });
                    }
                }).start();

                return true;

            }
            //当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

    }
    public void initData(){
        map=new HashMap<>();
        map.put("剩菜","湿垃圾");
        map.put("剩饭","湿垃圾");
        map.put("瓜皮","湿垃圾");
        map.put("香蕉皮","湿垃圾");
        map.put("花卉绿植","湿垃圾");
        map.put("橙皮","湿垃圾");
        map.put("葱","湿垃圾");
        map.put("饼干","湿垃圾");
        map.put("番茄酱","湿垃圾");
        map.put("宠物饲料","湿垃圾");
        map.put("蛋壳","湿垃圾");
        map.put("西瓜皮","湿垃圾");
        map.put("玉米","湿垃圾");
        map.put("骨头","湿垃圾");
        map.put("虾壳","湿垃圾");
        map.put("蛋糕","湿垃圾");
        map.put("巧克力","湿垃圾");

        map.put("报纸","可回收垃圾");
        map.put("酱油瓶","可回收垃圾");
        map.put("玻璃瓶","可回收垃圾");
        map.put("玻璃","可回收垃圾");
        map.put("易拉罐","可回收垃圾");
        map.put("饮料瓶","可回收垃圾");
        map.put("洗发水瓶","可回收垃圾");
        map.put("塑料玩具","可回收垃圾");
        map.put("书本","可回收垃圾");
        map.put("广告单","可回收垃圾");
        map.put("纸板箱","可回收垃圾");
        map.put("衣服","可回收垃圾");
        map.put("塑料瓶","可回收垃圾");
        map.put("食品罐头","可回收垃圾");
        map.put("旧书包","可回收垃圾");
        map.put("旧鞋子","可回收垃圾");
        map.put("纸箱","可回收垃圾");




        map.put("废电池","有害垃圾");
        map.put("温度计","有害垃圾");
        map.put("油漆桶","有害垃圾");
        map.put("银光灯管","有害垃圾");
        map.put("废药品","有害垃圾");
        map.put("杀虫剂","有害垃圾");
        map.put("医用纱布","有害垃圾");
        map.put("医用棉签","有害垃圾");
        map.put("打火机","有害垃圾");
        map.put("创可贴","有害垃圾");
        map.put("酒精调色板","有害垃圾");
        map.put("灯泡","有害垃圾");
        map.put("废油漆桶","有害垃圾");
        map.put("电池","有害垃圾");
        map.put("废电池","有害垃圾");
        map.put("废电池","有害垃圾");


        map.put("纸盒","干垃圾");
        map.put("餐巾纸","干垃圾");
        map.put("湿纸巾","干垃圾");
        map.put("卫生间用纸","干垃圾");
        map.put("塑料袋","干垃圾");
        map.put("食品包装袋","干垃圾");
        map.put("烟蒂","干垃圾");
        map.put("纸尿裤","干垃圾");
        map.put("一次性杯子","干垃圾");
        map.put("大骨头","干垃圾");
        map.put("贝壳","干垃圾");
        map.put("陶瓷花盆","干垃圾");
        map.put("陶瓷","干垃圾");
        map.put("核桃","干垃圾");
        map.put("一次性筷子","干垃圾");
        map.put("果核","干垃圾");
        map.put("湿垃圾袋","干垃圾");
        map.put("扫把","干垃圾");
        map.put("手机壳","干垃圾");
        map.put("手机膜","干垃圾");


    }



    }
