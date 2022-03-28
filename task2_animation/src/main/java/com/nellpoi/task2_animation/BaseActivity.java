package com.nellpoi.task2_animation;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class BaseActivity extends AppCompatActivity {
    // TODO: 1/15/22 封装SnackBar
    protected void showSnackBar(@NonNull View view, @NonNull String msg, boolean isDissmiss, String action, final ISnackBarClickEvent iSnackBarClickEvent) {
        int duringTime = Snackbar.LENGTH_LONG;
        if (isDissmiss) {
            duringTime = Snackbar.LENGTH_LONG;
        } else {
            duringTime = Snackbar.LENGTH_INDEFINITE;
        }
        Snackbar snackbar;
        snackbar = Snackbar.make(view, msg, duringTime).setAction(action, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 以接口方式发送出去，便于自定义自己的业务逻辑
                iSnackBarClickEvent.clickEvent();
            }
        });
        // TODO: 1/15/22 设置SnackBar背景色和ActionBar颜色一样
        snackbar.setBackgroundTint(getResources().getColor(R.color.colorPrimary));
        // TODO: 1/15/22 设置action文字的颜色
        snackbar.setActionTextColor(getResources().getColor(R.color.white));
        // TODO: 1/15/22 设置SnackBar的图标 这里是获取SnackBar的TextView 然后给Textview增加左边的图标的方式来实现
        View snackBarView = snackbar.getView();
        TextView textview = snackBarView.findViewById(R.id.snackbar_text);
        Drawable drawable = getResources().getDrawable(R.drawable.ic_baseline_notifications_24);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textview.setCompoundDrawables(drawable, null, null, null);
        // TODO: 1/15/22 增加文字和图标的距离
        textview.setCompoundDrawablePadding(20);
        // TODO: 1/15/22 显示SnackBar
        snackbar.show();
    }

    // TODO: 1/15/22 定义SnackBar的action事件
    public interface ISnackBarClickEvent {
        void clickEvent();
    }

    // TODO: 1/15/22 简单Toast
    protected void showSimpleToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    // TODO: 1/15/22 获取上下文
    protected Activity getContext() {
        return this;
    }
}