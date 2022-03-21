package com.example.laladui.listview_news;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.laladui.R;

import java.nio.channels.AcceptPendingException;
import java.util.ArrayList;

/*
详细的新闻界面
 */
public class WebInformationActivity extends AppCompatActivity {
    private Toolbar toolbar_web;
    private ActionBar actionBar;
    private WebView webView;
    private String uri;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_information);
        init();//初始化
        webView.getSettings().setJavaScriptEnabled(true);//设置WebView允许执行JavaScript脚本，默认false
        webView.setWebViewClient(new WebViewClient());
        if (!uri.isEmpty()){
            webView.loadUrl(uri);//加载相应的url

        }else {
            Toast.makeText(WebInformationActivity.this, "地址错误!", Toast.LENGTH_SHORT).show();
        }

    }

    public void init(){

        //setHasOptionsMenu(true);//自定义标题栏必须调用此方法，用于显示菜单
        toolbar_web = findViewById(R.id.toolbar_web);

        setSupportActionBar(toolbar_web);
        actionBar=getSupportActionBar();//获得ActionBar的实例
        if(actionBar!=null){
            //actionBar.setDisplayHomeAsUpEnabled(true);//显示导航按钮
            //actionBar.setHomeAsUpIndicator(R.drawable.caidan);//设置导航按钮图标
            actionBar.setTitle("资讯" );
        }
        webView=findViewById(R.id.web);
       //获得首页点击传来的intent
        uri=getIntent().getStringExtra("uri");
    }



}
