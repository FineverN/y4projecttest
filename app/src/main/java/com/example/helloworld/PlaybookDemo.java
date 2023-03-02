package com.example.helloworld;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class PlaybookDemo extends AppCompatActivity {
    final float[] currentX = new float[1];
    final float[] currentY = new float[1];
    float startX, startY;
    float endX, endY;
    FrameLayout playBookBack;

    //ImageView aDisc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setContentView(R.layout.activity_playbook_demo);

        FrameLayout fieldLayout = (FrameLayout)findViewById(R.id.playBookBack);
        final DiscView disc=new DiscView(this);//创建一个自定义的starView的View对象
        disc.setOnTouchListener(new View.OnTouchListener() {//给view对象创建一个触摸的监听事件
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                disc.setBitmapX(event.getX());//设置view的坐标为手指触碰的坐标
                disc.setBitmapY(event.getY());
                currentX[0] = event.getX();
                currentY[0] = event.getY();
                disc.invalidate();//使原来的整个窗口无效，对view进行刷新重绘
                return true;
            }
        });
        fieldLayout.addView(disc);//向布局中添加组件
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onStartClick (View view) {
        startX = currentX[0];
        startY = currentY[0];
    }
    public void onEndClick (View view) {
        endX = currentX[0];
        endY = currentY[0];
    }
    public void onPlayClick (View view) {
        //Animation animation = AnimationUtils.loadAnimation(this,R.anim.translateanim);
        //playBookBack = findViewById(R.id.playBookBack);
        //playBookBack.startAnimation(animation);


        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.ABSOLUTE, endX-startX, Animation.ABSOLUTE, 0, Animation.ABSOLUTE, endY-startY);
        //设置
        translateAnimation.setDuration(2000);
        //启动动画
        playBookBack.startAnimation(translateAnimation);

    }
}