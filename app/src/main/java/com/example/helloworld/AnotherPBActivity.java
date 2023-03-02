package com.example.helloworld;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.PathInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AnotherPBActivity extends AppCompatActivity {

    private ConstraintLayout layout;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another_pb_layout);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        layout = findViewById(R.id.root_view);
        relativeLayout = findViewById(R.id.container);
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView();
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addviewTwo();
            }
        });
    }
    /**
     * 按钮一的点击事件
     */
    private void addView() {
        TextView textView = new TextView(this);
        textView.setText("测试一...");
        //relativeLayout.addView(textView,0);
        ImageView img = new ImageView(this);
        img.setImageDrawable(Drawable.createFromPath("@drawable/bluedisc"));
        relativeLayout.addView(img,0);
    }
    /**
     * 按钮二的点击事件
     */
    private void addviewTwo() {
        TextView textView = new TextView(this);
        textView.setText("测试二...");

        relativeLayout.addView(textView,1);
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
}
