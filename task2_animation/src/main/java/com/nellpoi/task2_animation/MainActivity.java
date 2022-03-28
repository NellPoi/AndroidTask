package com.nellpoi.task2_animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends BaseActivity {
    /* 定义全局宽和高 */
    private int width;
    private int height;
    private CoordinatorLayout root;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    int view = R.layout.activity_main;
    private ImageView imageView;
    private TextView textView;
    private boolean aBooleanFrameAnimation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        // TODO: 1/15/22 初始化视图
        initView();

        // TODO: 1/15/22 执行动画的入口，点击任一动画调用对应的动画事件
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSnackBar(view, "点击右上角选择动画", true, "", new ISnackBarClickEvent() {
                    @Override
                    public void clickEvent() {
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings_animator1:
                demoAboutFrameAnimation();
                break;
            case R.id.action_settings_animator2_1:
                demoAboutTweenAnimationOfAlpha();
                break;
            case R.id.action_settings_animator2_2:
                demoAboutTweenAnimationOfRotate();
                break;
            case R.id.action_settings_animator2_3:
                demoAboutTweenAnimationOfScale();
                break;
            case R.id.action_settings_animator2_4:
                demoAboutTweenAnimationOfTranslate();
                break;
            case R.id.action_settings_animator3_1:
                demoAboutValueAnimator();
                break;
            case R.id.action_settings_animator3_2:
                demoAboutValueAnimator_ObjectAnimator();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        root = (CoordinatorLayout) findViewById(R.id.root);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);
    }

    private void demoAboutFrameAnimation() {
        showSnackBar(root, "正在执行逐帧动画", true, "好的", null);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        if (aBooleanFrameAnimation == true) {
            animationDrawable.start();
            aBooleanFrameAnimation = false;
        } else {
            animationDrawable.stop();
            aBooleanFrameAnimation = true;
        }
    }

    private void demoAboutTweenAnimationOfAlpha() {
        showSnackBar(root, String.valueOf(R.string.action_settings_animator2_1), true, null, null);
        // 通过加载xml动画设置文件来创建一个Animation对象
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);
        imageView.startAnimation(animation);
    }

    private void demoAboutTweenAnimationOfRotate() {
        showSnackBar(root, String.valueOf(R.string.action_settings_animator2_2), true, null, null);
        // 通过加载xml动画设置文件来创建一个Animation对象
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate);
        imageView.startAnimation(animation);
    }

    private void demoAboutTweenAnimationOfScale() {
        showSnackBar(root, String.valueOf(R.string.action_settings_animator2_3), true, null, null);
        // 通过加载xml动画设置文件来创建一个Animation对象
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.scale);
        imageView.startAnimation(animation);
    }

    private void demoAboutTweenAnimationOfTranslate() {
        showSnackBar(root, String.valueOf(R.string.action_settings_animator2_3), true, null, null);
        // 通过加载xml动画设置文件来创建一个Animation对象
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.translate);
        imageView.startAnimation(animation);
    }

    private void demoAboutValueAnimator() {
        showSnackBar(root, String.valueOf(R.string.action_settings_animator3_1), true, null, null);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();// 获取当前的值
                System.out.println("NellPoi" + animatedValue);
            }
        });
        valueAnimator.start();
    }

    private void demoAboutValueAnimator_ObjectAnimator() {
        showSnackBar(root, String.valueOf(R.string.action_settings_animator3_2), true, null, null);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f);
        objectAnimator.setDuration(4000);
        objectAnimator.start();
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                // TODO: 1/16/22 动画开始的时候调用
                showSnackBar(root, "动画状态：开始" ,true, null, null);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // TODO: 1/16/22 动画结束的时候调用
                showSnackBar(root, "动画状态：结束" ,true, null, null);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // TODO: 1/16/22 动画被取消的时候调用
                showSnackBar(root, "动画状态：取消" ,true, null, null);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // TODO: 1/16/22 动画重复执行的时候调用
                showSnackBar(root, "动画状态：重复" ,true, null, null);
            }
        });
        // TODO: 1/16/22 使用适配器，将需要重写的功能进行勾选，就不再需要像上面一样臃肿了
//        objectAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                super.onAnimationStart(animation);
//            }
//        });
    }
}
