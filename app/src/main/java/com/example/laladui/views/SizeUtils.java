package com.example.laladui.views;

import android.content.Context;

public class SizeUtils {
    public static int dip2px(Context context,float dpValue){
        float scale=context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5f);
    }
}
