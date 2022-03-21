package com.example.laladui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.AlteredCharSequence;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_register;//立即注册，
    private Button botton_login;//登录按钮
    private EditText et_zhanghao, et_password;//账号密码编辑框
    private final  String TAG="aaa";
    private final String REQUEST_LOGIN = "http://47.96.255.59:8080/ljfl/login";
    private ProgressDialog progressDialog = null;//对话框

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
        botton_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
    }
    //初始化控件
    public void init(){
        botton_login = findViewById(R.id.login);
        tv_register = findViewById(R.id.login_register);
        et_zhanghao = findViewById(R.id.login_zhanghao);
        et_password = findViewById(R.id.login_mima);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login: {
                String number = et_zhanghao.getText().toString();
                String password = et_password.getText().toString();
                progressDialog = ProgressDialog.show(LoginActivity.this, "请稍等...", "获取数据中...", true);
                isSuccessful(number, password);
                break;
            }


            case R.id.login_register:{
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);//跳转到注册界面
                startActivity(intent);
                break;
            }


        }
    }
    /*
    通过同步请求判断账号密码是否正确
     */
    public void isSuccessful(String phone,String password){
        String uri=REQUEST_LOGIN+"?phone="+phone+"&password="+password;
        Log.d(TAG,"isSuccessful:"+uri);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response=HttpUtil.http(uri);//获取服务端响应
                   //如果响应为空（没办法连接到服务端），则提示网络错误，
                    if (response==null){
                        Log.d(TAG,"断网");
                        //在runOnUiThread()中更新UI，弹出，进度条/提示框
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();//前面点击登录按钮时出现进度条，这里登录失败进度条消失
                                //在登录界面生成对话框提示
                                new AlertDialog.Builder(LoginActivity.this)
                                        .setTitle("错误")
                                        .setTitle("网络错误")
                                        .setCancelable(true)
                                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();//点击确定，对话框消失
                                            }
                                        }).create().show();
                            }
                        });

                    } else {
                        String responseData=response.body().string();//将服务端的响应转成字符串
                        JSONObject object1=new JSONObject(responseData);
                        boolean ifExist=object1.getBoolean("ifExist");
                        if (ifExist==false){
                            //更新UI弹出提示框
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();//进度条消失
                                    new AlertDialog.Builder(LoginActivity.this)
                                            .setTitle("错误")
                                            .setMessage("账号密码错误！请重新登录")
                                            .setCancelable(true)
                                            .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    dialogInterface.dismiss();
                                                }
                                            }).create().show();//显示提示框
                                }
                            });
                        }else {
                            Log.d(TAG,"response.body().string()="+responseData);
                            //解析服务端返回的响应
                            JSONObject object=new JSONObject(responseData);
                            //获取解析的json对象的id
                            int id=object.getInt("id");
                            //更新UI，弹出提示款
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();
                                    Intent intent=new Intent(LoginActivity.this,NavigationActivity.class);
                                    intent.putExtra("id",id);
                                    Log.d(TAG,"run"+id);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG,"JSON"+e.getMessage());
                    //更新UI
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                            new AlertDialog.Builder(LoginActivity.this)
                                    .setTitle("错误")
                                    .setMessage("JSON网络错误")
                                    .setCancelable(true)
                                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();//对话框消失
                                        }
                                    }).create().show();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d(TAG,"IO"+e.getMessage());
                }

            }
        }).start();
    }
}
