package com.nellpoi.task1_guide;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity4 extends AppCompatActivity {
    EditText user, email, pwd, confirm_pwd;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        setTitle("注册账号");
        initView();

        // TODO: 12/31/21 注册功能
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 验证表单中的所有输入框是否均有输入
                if (user.getText().length() == 0 || email.getText().length() == 0 || pwd.getText().length() == 0 || confirm_pwd.getText().length() == 0) {
                    showAlertWithTitleAndMessage("输入不合法", "用户名、邮箱、密码、确认密码均不能为空！");
                } else {
                    // 验证用户名是否为纯字母且位数符合条件
                    boolean isAllPureLetters = user.getText().toString().matches("[a-zA-Z]+");
                    if ((user.getText().length() >= 3 || user.getText().length() <= 6) && isAllPureLetters) {
                        // 当用户名符合条件，将其统一转为小写，目的是为了不区分大小写功能
                        user.setText(user.getText().toString().toLowerCase());
                        // 判断邮箱是否合法
                        if (email.getText().toString().contains("@")) {
                            // 验证密码位数是否符合条件
                            if ((pwd.getText().length() >= 3 || pwd.getText().length() <= 6)) {
                                // 判断两个密码是否一致
                                if (!pwd.getText().toString().equals(confirm_pwd.getText().toString())) {
                                    showAlertWithTitleAndMessage("输入不合法", "密码和确认密码应保持一致");
                                } else if (!confirm_pwd.getText().toString().equals(pwd.getText().toString())) {
                                    showAlertWithTitleAndMessage("输入不合法", "密码和确认密码应保持一致");
                                } else {
                                    // 弹窗让用户确认注册的信息，用户用户二级确认是否信息无误确定注册账号
                                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity4.this);
                                    builder.setTitle("账号创建成功");
                                    builder.setMessage("您创建的账号信息如下\n" +
                                            "用户名：" + user.getText().toString() +
                                            "\n邮箱：" + email.getText().toString() +
                                            "\n密码：******");
                                    builder.setPositiveButton("确定注册", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            // 用户在确认信息无误，确认注册账号
                                            Toast.makeText(getApplicationContext(), "注册成功！", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplication(), MainActivity3.class);
                                            intent.putExtra("i_user", user.getText().toString());
                                            intent.putExtra("i_email", email.getText().toString());
                                            intent.putExtra("i_pwd", pwd.getText().toString());
                                            intent.putExtra("i_confirm_pwd", confirm_pwd.getText().toString());
                                            startActivityForResult(intent, 0x01);
                                        }
                                    });
                                    builder.setNegativeButton("再考虑考虑", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            AlertDialog show = builder.show();
                                            show.dismiss();
                                        }
                                    });
                                    builder.show();
                                }
                            } else {
                                showAlertWithTitleAndMessage("输入不合法", "密码应为3－6位字母与数字组合");
                            }
                        } else {
                            showAlertWithTitleAndMessage("输入不合法", "邮箱地址应为合法的邮箱地址");
                        }
                    } else {
                        showAlertWithTitleAndMessage("输入不合法", "用户名应为3－6位纯字母，忽略大小写");
                    }
                }

            }
        });
    }

    public void login(View view) {
        finish();
    }

    // TODO: 12/31/21 初始化控件
    public void initView() {
        user = findViewById(R.id.et_username);
        email = findViewById(R.id.et_email);
        pwd = findViewById(R.id.et_pwd);
        confirm_pwd = findViewById(R.id.et_confirm_pwd);
        button = findViewById(R.id.btn_register);
    }

    public void showAlertWithTitleAndMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity4.this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
}