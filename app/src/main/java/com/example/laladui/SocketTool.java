package com.example.laladui;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.laladui.youximenu.MenuView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;


import static java.lang.Thread.sleep;


public class SocketTool {
    private static String SEND = null;//发送的数据
//    private static String ip = "121.196.104.55";
//    private static int port = 7839;
    public static int CREDIT = 0;
    private static Socket socket;
    private static BufferedReader inStream;
    private static BufferedWriter outStream;
    private static Thread recvThread;

    public SocketTool(String ip, int port)  {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try
                {
                    socket = new Socket(ip,port);
                    Log.d("123","socket成功");
                    inStream = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
                    Log.d("123","instream不为空");
                    outStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
                    Log.d("123","outstream不为空");
                    recv();
                } catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    //圆盘按钮点击设置发送数据
    public static void setSend(MenuView.Block block) {
        switch (block) {
            case TOP:
                SEND = "1front";
                break;
            case BOTTOM:
                SEND = "1behind";
                break;
            case LEFT:
                SEND = "1left";
                break;
            case RIGHT:
                SEND = "1right";
                break;
            case CENTER:
                SEND = "1ok";
                break;
            default:
                break;
        }
    }
    //兑换奖品按钮点击设置发送数据
    public static void setSend1(View view){
        switch (view.getId()){
            case R.id.duihuan:
                SEND = "1open";
                Log.d("123","dfdsf");
                break;

            default:
                break;
        }
    }


    public void recv(){
        new Thread(){
            @Override
            public void run() {
                while (true) {
                    Log.d("123","接收数据方法启动。");
                    try {
                        Log.d("123","接收信息阻塞。");
                        String recv = inStream.readLine();
                        Log.d("123",recv);
                        if (recv == null) {
                            Log.d("123","接收数据为空。");
                            //break;
                        }
                        else if (recv.contains("right")) {
                            CREDIT += 1;

                            Log.d("123", "正确CREDIT=" + CREDIT);
                        }
                        else if (recv.contains("wrong")){
                            CREDIT -= 1;
                            Log.d("123", "错误CREDIT=" + CREDIT);
                        }
                        recv = "";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }
    public void send() {
        new Thread(() -> {
            Log.d("123","发送数据方法启动。");
            if(outStream == null){
                Log.d("123","outStream为空");
            }else {
                try {
                    Log.d("123","发送数据方法启动1。");
                    outStream.write(SEND);
                    Log.d("123","发送数据方法启动2。");
                    outStream.flush();
                    Log.d("123","发送数据方法启动3。");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Log.d("123","发送数据方法启动4。");
    }
    //设置积分小于10所发送的数据
    public static void setNoAction(MenuView.Block block) {
        switch (block) {
            case TOP:
                SEND = "1Nofront";
                break;
            case BOTTOM:
                SEND = "1Nobehind";
                break;
            case LEFT:
                SEND = "1Noleft";
                break;
            case RIGHT:
                SEND = "1Noright";
                break;
            case CENTER:
                SEND = "1Nook";
                break;
            default:
                break;
        }
    }

}
