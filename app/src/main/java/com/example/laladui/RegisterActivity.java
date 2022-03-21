package com.example.laladui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
    private TextView register_title;//标题
    //标题布局
    private RelativeLayout rl_title_bar;
    private Button button;
    private EditText et_zhanghao, et_password;
    private ProgressDialog progressDialog;
    private final String REQUEST_REGISTER = "http://47.96.255.59:8080/ljfl/register";
    private final  String TAG="aaa";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置页面布局 ,注册界面
        setContentView(R.layout.activity_register);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog=ProgressDialog.show(RegisterActivity.this, "请稍等...", "获取数据中...", true);
                Register(et_zhanghao.getText().toString(),et_password.getText().toString());


            }
        });

    }

    private void init() {
        //从main_title_bar.xml 页面布局中获取对应的UI控件
        register_title=findViewById(R.id.main_title);
        register_title.setText("注册");
        //布局根元素
        rl_title_bar=findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.TRANSPARENT);
        button = findViewById(R.id.zc_register);
        et_zhanghao = findViewById(R.id.zc_zhanghao);
        et_password = findViewById(R.id.zc_mima);

    }
    //注册
    public void Register(String phone,String password){
        String url=REQUEST_REGISTER+"?phone"+phone+"&password"+password;
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //更新UI，弹出提示框
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(600);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        progressDialog.dismiss();
                        new AlertDialog.Builder(RegisterActivity.this)
                                .setTitle("错误")
                                .setMessage("网络错误")
                                .setCancelable(true)
                                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                }).create().show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject object=new JSONObject(response.body().string());
                    if (!object.getString("id").isEmpty()){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(600);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                progressDialog.dismiss();
                                new AlertDialog.Builder(RegisterActivity.this)
                                        .setTitle("提示")
                                        .setMessage("注册成功")
                                        .setCancelable(true)
                                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                                finish();
                                            }
                                        }).create().show();
                            }
                        });

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                           try {
                               Thread.sleep(600);
                           } catch (InterruptedException e1) {
                               e1.printStackTrace();
                           }
                           progressDialog.dismiss();
                           new AlertDialog.Builder(RegisterActivity.this)
                                   .setTitle("错误")
                                   .setMessage("注册失败，请重试！")
                                   .setCancelable(true)
                                   .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                       @Override
                                       public void onClick(DialogInterface dialogInterface, int i) {
                                           dialogInterface.dismiss();
                                       }
                                   }).create().show();
                        }
                    });
                }
            }
        });
    }


}
