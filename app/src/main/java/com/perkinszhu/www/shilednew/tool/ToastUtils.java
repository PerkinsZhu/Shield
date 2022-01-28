package com.perkinszhu.www.shilednew.tool;

import android.app.AlertDialog;

import com.perkinszhu.www.shilednew.MainActivity;

public class ToastUtils {
    public static void tip(String tip) {
        //添加确定按钮
        //添加返回按钮
        new AlertDialog.Builder(MainActivity.mainActivity).setTitle("信息提示")//设置对话框标题
                .setMessage(tip)
                .setPositiveButton("是", (dialog, which) -> {//确定按钮的响应事件
                }).setNegativeButton("否", (dialog, which) -> {//响应事件
        }).show();//在按键响应事件中显示此对话框
    }

}
