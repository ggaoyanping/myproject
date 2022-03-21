package com.example.laladui.youximenu;

import android.content.Context;
import android.widget.Toast;
public class ToastUtils {
    private static Toast mToast;
    public static void showToast(Context context, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }
}