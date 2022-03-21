package com.example.laladui;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {
    private final static String TAG="bbb";
    /*
    异步请求
     */
    public static void sendOkHttpRequest(String address,Callback callback){
        //生成客户端
        OkHttpClient client=new OkHttpClient.Builder().callTimeout(10,TimeUnit.SECONDS)
                .callTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .build();
        Request request=new Request.Builder()
                .url(address)
                .build();//生成请求
        client.newCall(request).enqueue(callback);//向服务端发起请求
    }
    /*
    同步请求，返回Response对象
     */
    public static Response http(String address){
        Response response=null;
        //生成客户端
        OkHttpClient client=new OkHttpClient.Builder().callTimeout(10,TimeUnit.SECONDS)
                .callTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .build();
        //请求
        Request request=new Request.Builder().url(address).build();

        Call call=client.newCall(request);
        try {
            response=call.execute();
        }catch (IOException e){
            e.printStackTrace();
        }
        return  response;
    }

}
