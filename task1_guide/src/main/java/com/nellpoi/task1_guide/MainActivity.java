package com.nellpoi.task1_guide;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.listener.OnPageChangeListener;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("欢迎回来");
        initBanner();
    }

    void initBanner() {
        /* todo 找到banner并实例化*/
        Banner banner;
        banner = findViewById(R.id.banner);
        // TODO: 12/28/21 定义一个集合用于存放banner里面的图片素材
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.banner1);
        list.add(R.drawable.banner2);
        list.add(R.drawable.banner3);
        // TODO: 12/28/21 通过适配器将刚刚的用于存放素材的集合添加到banner
        banner.setAdapter(new BannerImageAdapter<Integer>(list) {
            @Override
            public void onBindView(BannerImageHolder bannerImageHolder, Integer integer, int i, int i1) {
                bannerImageHolder.imageView.setImageResource(list.get((i)));
            }
            // TODO: 12/28/21 设置banner底下的小圆点
        }).setIndicator(new CircleIndicator(MainActivity.this));
        // TODO: 12/28/21 设置点击监听事件，用于点击banner时跳转到新闻详情页面
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object o, int i) {
                // TODO: 12/28/21 创建一个新闻模板，实现不同的banner点击跳转带对应的新闻
                String title[] = new String[]{"公交车违规下客乘客被过往小车撞飞 具体情况是怎样的", "云南核酸阳性学生曾翻墙离校游玩 具体是什么情况？", "男子撑伞被闪电击中 地面火花四溅 怎么回事？【图】"};
                String content[] = new String[]{"12月13日，湖南岳阳，一辆小车违反禁令标志指示行驶，同时一辆公交车违反规定停车上下客，导致一名女子下公交车时，被这辆小车撞飞。所幸伤者正在医院接受治疗，暂无生命危险。", "据消息，12月27日，云南安宁市一名人员确认核酸结果为阳性。接初筛结果报告后，安宁市应对新冠肺炎疫情防控工作指挥部第一时间启动应急预案，对该人员途经的宁湖九号香缇花园2幢、昆明冶金高等专科学校、金色时代广场家乐福超市、鼎立医院、安宁市", "近日，一篇“男子撑伞被闪电击中 地面火花四溅”的报道登上了各大搜索引擎的热搜榜，在网上引起了众多网民的关注和热议，深港在线小编也第一时间在网上查阅整理了一些相关资讯，下面一起来看下。"};
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("page_title", title[i]);
                intent.putExtra("page_content", content[i]);
                startActivityForResult(intent, 0x01);
            }
        });
        // TODO: 12/28/21 设置页面改变监听事件，用于自动进入登陆页面
        banner.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                // TODO: 12/28/21 判断是否在最后一页，如果是，则停止轮播并倒计时3秒跳转到登陆页面
                if (i == 2) {
                    System.out.println("滑动到结束页面");
                    banner.isAutoLoop(false);
                    System.out.println("停止轮播");
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                            finish();
                            startActivity(intent);
                        }
                    }, 3000);

                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    public void about(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("关于");
        builder.setMessage("GitHub：https://github.com/NellPoi/AndroidTask\n" +
                "创建时间：2021-12-27 T 06:00:56 Z\n");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("GitHub", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("https://github.com/NellPoi/AndroidTask");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        builder.show();
    }
}

