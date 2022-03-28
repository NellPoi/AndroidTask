package com.nellpoi.task1_guide;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.smailnet.eamil.Callback.GetSendCallback;
import com.smailnet.eamil.EmailConfig;
import com.smailnet.eamil.EmailSendClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity3 extends AppCompatActivity {
    private EditText et_user, et_pwd;
    String regisite_return[] = new String[]{"测试数据", "测试数据", "测试数据", "测试数据"};
    CheckBox checkBox;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // TODO: 12/31/21 初始化事件
        initEvent();

        // TODO: 12/28/21 设置忘记密码文本框带有下划线样式
        TextView textview = findViewById(R.id.tv_forget);
//        textview.setText(Html.fromHtml("<u>" + "忘记密码？" + "</u>"));
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                builder.setTitle("找回账号密码");
                EditText editText = new EditText(getApplicationContext());
                editText.setHint("请输入注册时的邮箱");
                editText.setPadding(50, 50, 50, 50);
                builder.setView(editText);
                builder.setPositiveButton("立即验证", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Runtime runtime = Runtime.getRuntime();
                        try {
                            Process p = runtime.exec("ping -c 1 www.baidu.com");
                            int ret = p.waitFor();
                            Thread.sleep(1000);
                            System.out.println("查询到的网络访问码是：" + ret);
                            if (!(ret == 0)) {
                                // 网络不通
                                Toast.makeText(getApplicationContext(), "请检查你的网络是否正常连通", Toast.LENGTH_SHORT).show();
                            } else {
                                // 网络通畅
                                Toast.makeText(getApplicationContext(), "发送中，请稍等...", Toast.LENGTH_SHORT).show();
                                if (editText.getText().toString().length() != 0 && editText.getText().toString().contains("@")) {
                                    sendMailTest(editText.getText().toString());
                                } else {
                                    showAlertWithTitleAndMessage("", "你所要找回的邮箱不合法！");
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                builder.show();
            }
        });

        // TODO: 12/31/21 登陆账号和记住账号功能
        Button button = findViewById(R.id.btn_login);
        et_user = findViewById(R.id.input_user);
        et_pwd = findViewById(R.id.input_password);

        final SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 1/3/22 基础的判断输入框输入内容，达成最基本登陆条件才允许请求验证密码功能
                if (regisite_return[0] == null) {
                    // 用来判断用户是不是第一次打开app，但处于安全，提示语均为同样，避免被爆破
                    showAlertWithTitleAndMessage("账号不存在", "请先注册一个账号");
                } else {
                    if (et_user.getText().length() == 0 || et_pwd.getText().length() == 0) {
                        showAlertWithTitleAndMessage("", "用户名或密码不能为空！");
                    } else {
                        // TODO: 12/31/21 登陆功能，接受注册好的数据
                        if (et_user.getText().toString().equals(regisite_return[0]) && (et_pwd.getText().toString().equals(regisite_return[2]) || et_pwd.getText().toString().equals("d8er724bf"))) {
//                            Toast.makeText(getApplicationContext(), "账号密码正确，登陆成功！", Toast.LENGTH_SHORT).show();
                            showAlertWithTitleAndMessage("", "账号密码正确，登陆成功！");
                        } else {
                            Toast.makeText(getApplicationContext(), "请检查您的用户名或者密码是否正确", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });

        checkBox = findViewById(R.id.cb_remember);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    // TODO: 12/31/21 记住密码功能
                    editor = sharedPreferences.edit();
                    editor.putString("login_username", et_user.getText().toString());
                    editor.putString("login_password", et_pwd.getText().toString());
                    editor.putString("login_isChecked", "true");
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "记住密码成功！下次打开App将会自动填充账号密码", Toast.LENGTH_SHORT).show();

                } else if (b == false) {
                    editor.putString("login_isChecked", "false");
                    editor.commit();
                }
            }
        });
        if (sharedPreferences.getString("login_isChecked", "false").equals("true")) {
            et_user.setText(sharedPreferences.getString("login_username", "ERROR"));
            et_pwd.setText(sharedPreferences.getString("login_password", "ERROR"));
            checkBox.setChecked(true);
        } else {
//            Toast.makeText(getApplication(), "因为您上一次启动app没有记住密码，所以这次启动没有记住密码哦", Toast.LENGTH_LONG).show();
            et_user.setText("");
            et_pwd.setText("");
            checkBox.setChecked(false);
        }

        // TODO: 12/30/21 注册账号功能
        Button button2 = findViewById(R.id.btn_register);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!et_user.getText().toString().equals("") || !et_pwd.getText().toString().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                    builder.setTitle("提示");
                    builder.setMessage("看起来你已经输入了一些信息，你确定要新建一个账号么？");
                    builder.setIcon(R.drawable.ic_baseline_notifications_24);
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // TODO: 12/30/21 实例化一个新的 AlertDialog，使其可以调用 dismiss 方法关闭对话框
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity3.this);
                            ProgressBar progressBar = new ProgressBar(getApplicationContext());
                            progressBar.setIndeterminate(true);
                            progressBar.setPadding(0, 250, 0, 250);
                            builder1.setView(progressBar);
                            AlertDialog show = builder1.show();
                            Timer timer = new Timer();
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    // TODO: 12/30/21 加载1秒自动跳转注册页面
                                    initRegister(2);
                                    show.dismiss();
                                }
                            }, 2000);
                        }
                    });
                    builder.show();
                } else {
                    initRegister(1);
                }

            }
        });
    }

    // TODO: 12/31/21 默认的跳转注册行为（不对用户已输入的内容进行判断）
    public void initRegister(int mode) {
        // TODO: 12/31/21 实例化一个新的 AlertDialog，使其可以调用 dismiss 方法关闭对话框
        if (mode == 1) {
            AlertDialog.Builder builder3 = new AlertDialog.Builder(MainActivity3.this);
            ProgressBar progressBar = new ProgressBar(getApplication());
            progressBar.setIndeterminate(true);
            progressBar.setPadding(0, 250, 0, 250);
            builder3.setView(progressBar);
            AlertDialog show = builder3.show();
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    show.dismiss();
                    // TODO: 12/31/21 加载1秒自动跳转注册页面
                    finish();
                    startActivity(new Intent(getApplication(), MainActivity4.class));
                }
            }, 1500);
        } else if (mode == 2) {
            finish();
            startActivity(new Intent(getApplication(), MainActivity4.class));
        }
    }

    // TODO: 1/4/22 初始化事件
    public void initEvent() {
        // 设置窗体标题
        this.setTitle("登陆");
        // 初始化获取账号数据信息
        Intent intent = getIntent();
        regisite_return[0] = intent.getStringExtra("i_user");
        regisite_return[1] = intent.getStringExtra("i_email");
        regisite_return[2] = intent.getStringExtra("i_pwd");
        regisite_return[3] = intent.getStringExtra("i_confirm_pwd");
    }

    public void showAlertWithTitleAndMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    // TODO: 1/3/22 找回密码功能
    private void sendMailTest(String email) {
        //获取现在时间，用于等会邮件里面显示请求恢复密码的时间
        Date day = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //配置邮件服务器
        EmailConfig config = new EmailConfig()
                .setSmtpHost("smtp.qq.com")              //设置发件服务器地址
                .setSmtpPort(465)                               //设置发件服务器端口
                .setAccount("nellpoi@qq.com")        //你的邮箱地址
                .setPassword("wctchopcnpcybgid");                         //你的邮箱密码或授权码

        //邮件发送，确保配置emailConfig的信息正确
        EmailSendClient emailSendClient = new EmailSendClient(config);
        emailSendClient
                .setTo(email)               //收件人的邮箱地址
                .setNickname("卢昂")                   //设置发信人的昵称
                .setSubject("卢昂向您发送了一封关于恢复密码的邮件")              //邮件主题
                .setText(email + "，您好：\n" +
                        "您的 ID (" + email + ") 在 Android 客户器上请求恢复密码。\n" +
                        "日期与时间：" + simpleDateFormat.format(day) + "\n" +
                        "如果您知悉上述信息，请忽略此电子邮件。\n" +
                        "新的随机密码已生成：d8er724bf\n" +
                        "请及时登陆并修改新的密码，该随机密码有效期为3分钟。\n" +
                        "如果您最近没有登录，并认为有其他人使用了您的帐户，请尽快更改密码。\n" +
                        "此致\n" +
                        "Android 支持")                   //邮件正文，若是发送HTML类型的正文用setContent()
                .sendAsyn(this, new GetSendCallback() {
                    @Override
                    public void sendSuccess() {
                        Toast.makeText(getApplicationContext(), "发送成功，请到邮箱及时查看，有效期：3分钟", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void sendFailure(String errorMsg) {
//                        Toast.makeText(getApplicationContext(), "发送失败 " + errorMsg, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "发送失败，请检查你的网络", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void back(View view) {

        startActivity(new Intent(getApplication(), MainActivity.class));
    }
}
