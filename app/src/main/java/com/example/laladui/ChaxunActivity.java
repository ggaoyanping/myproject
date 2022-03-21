package com.example.laladui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.widget.ImageView;

public class ChaxunActivity extends AppCompatActivity {
    protected SearchView searchView;
    protected ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_chaxun);
        searchView=findViewById(R.id.sv);//加载搜索框
        searchView.setQueryHint("请输入垃圾名称");
    }
}
