package com.nellpoi.task2_account_management;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MainActivity extends father {

    private ImageView back;
    private TickerView tickerView;
    private RelativeLayout appbar;
    private Button topUp;
    private View view;
    private CardView card1;
    private CardView card2;
    private LinearLayout templateTable;
    private Button withdrawal;
    float balance;
    private ImageView more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // TODO: 1/8/22 初始化视图
        initView();

        // TODO: 1/8/22 初始化余额
        initMoney();

        // TODO: 1/8/22 顶部返回功能
        back.setOnClickListener(v -> back());

        // TODO: 1/8/22 充值功能
        topUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                balanceChangeAlertDialog("余额充值");
            }
        });

        // TODO: 1/8/22 提现功能
        withdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                balanceChangeAlertDialog("余额提现");
            }
        });

        // FIXME: 1/8/22 测试功能：清空所有控件
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout resetComponent;
                resetComponent = findViewById(R.id.template_table);
                resetComponent.removeAllViews();
                initMoney();

            }
        });

    }

    // TODO: 1/8/22 初始化视图
    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        appbar = (RelativeLayout) findViewById(R.id.appbar);
        topUp = (Button) findViewById(R.id.topUp);
        view = (View) findViewById(R.id.view);
        card1 = (CardView) findViewById(R.id.card1);
        card2 = (CardView) findViewById(R.id.card2);
        templateTable = (LinearLayout) findViewById(R.id.template_table);
        withdrawal = (Button) findViewById(R.id.withdrawal);
        tickerView = findViewById(R.id.tickerView);
        more = (ImageView) findViewById(R.id.more);
    }

    // TODO: 1/8/22 "无中生有"大法：生成余额，作为默认余额
    private void initMoney() {
        // 随机生成数字作为可用余额，范围0-9999
        Random random = new Random();
        tickerView.setCharacterLists(TickerUtils.provideNumberList());
        tickerView.setText(String.valueOf(random.nextInt(9999)));
    }

    // TODO: 1/8/22 "充值"或"提现"AlertDialog封装
    private void balanceChangeAlertDialog(String inputParameter) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(inputParameter);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setPadding(50, 50, 50, 50);
        TextView textView = new TextView(getContext());
        textView.setText("¥");
        textView.setTextSize(30);
        textView.setTextColor(Color.parseColor("#000000"));
        textView.setPadding(10, 0, 10, 0);
        EditText editText = new EditText(getContext());
        Random random = new Random();// 随机生成数字作为充值或提现的金额，范围0-9999
        editText.setText(String.valueOf(random.nextInt(9999)));
        editText.setTextSize(40);
        editText.setMaxLines(1);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        editText.setEms(10);
        editText.setBackground(null);
        linearLayout.addView(textView);
        linearLayout.addView(editText);

        builder.setView(linearLayout);

        builder.setPositiveButton(

                "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 充值功能
                        Date today = new Date();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                        Float change = (float) Float.valueOf(String.valueOf(editText.getText()));
                        // 充值和提现执行不同的⌚事件
                        switch (inputParameter) {
                            case "余额充值":
                                balance += change;
                                showToast("充值成功～");
                                break;
                            case "余额提现":
                                balance -= change;
                                showToast("提现成功～");
                        }
                        tickerView.setText(String.valueOf(balance));
                        // 充值结果添加到表单
                        // TODO: 1/8/22  setLayout(Str 交易类型,  + Str 变动金额, Str 交易时间, Str 可用余额)
                        templateTable.addView(setLayout(inputParameter, "+" + change, simpleDateFormat.format(today), String.valueOf(balance)));
                    }
                });
        builder.show();

    }

}
