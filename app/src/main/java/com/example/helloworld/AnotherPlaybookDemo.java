package com.example.helloworld;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.PathInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AnotherPlaybookDemo extends AppCompatActivity implements View.OnTouchListener{

    private ImageView imageView;

    private ImageView[] discGroup = new ImageView[7];
    private int index = 0;

    private RelativeLayout relativeLayout;

    private int itemID=0;

    private int lastX, lastY;    //保存手指点下的点的坐标

    private int[] recentX = new int[7];
    private int[] recentY = new int[7];
    private int[] initX = new int[7];
    private int[] initY = new int[7];
    private int[] destX = new int[7];
    private int[] destY = new int[7];

    private int startX, startY;
    private int endX, endY;
    final static int IMAGE_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_playbook_demo);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //onAddClick(findViewById(R.id.addbutton));
    }

    public boolean onTouch(View view, MotionEvent event) {
        int currentTag = (int) view.getTag();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                //将点下的点的坐标保存
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                recentX[currentTag] = (int) event.getRawX();
                recentY[currentTag] = (int) event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                //计算出需要移动的距离
                int dx = (int) event.getRawX() - lastX;
                int dy = (int) event.getRawY() - lastY;
                //将移动距离加上，现在本身距离边框的位置
                int left = view.getLeft() + dx;
                int top = view.getTop() + dy;
                //获取到layoutParams然后改变属性，在设置回去
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();
                layoutParams.height = IMAGE_SIZE;
                layoutParams.width = IMAGE_SIZE;
                layoutParams.leftMargin = left;
                layoutParams.topMargin = top;
                view.setLayoutParams(layoutParams);
                //记录最后一次移动的位置
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
        }
        //刷新界面
        relativeLayout.invalidate();
        return true;
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
        for(int i=0;i<index;i++){
            initX[i] = (int) discGroup[i].getX();
            initY[i] = (int) discGroup[i].getY();
        }
        startX = lastX;
        startY = lastY;

    }
    public void onEndClick (View view) {
        for(int i=0;i<index;i++){
            destX[i] = (int) discGroup[i].getX();
            destY[i] = (int) discGroup[i].getY();
        }
        endX = lastX;
        endY = lastY;
        //generate animation of all views in discGroup from its init position to its dest position
        for(int i=0;i<index;i++){
            Path path = new Path();
            path.moveTo(initX[i],initY[i]);
            path.lineTo(destX[i],destY[i]);
            ObjectAnimator animation = ObjectAnimator.ofFloat(discGroup[i], "x", "y", path);
            animation.setDuration(2000);
            animation.setInterpolator(new AnticipateInterpolator());
            animation.start();
        }
//        Path path = new Path();
//        path.moveTo(startX,startY);
//        path.lineTo(endX-150f,endY-150f);
//        ObjectAnimator animation = ObjectAnimator.ofFloat(imageView, "x", "y", path);
//        animation.setDuration(2000);
//        animation.setInterpolator(new AnticipateInterpolator());
//        animation.start();
    }
    public void onAddClick (View view) {
        //imageView = (ImageView) findViewById(R.id.aDisc);
        ImageView discInstance = new ImageView(this);
        relativeLayout = (RelativeLayout) findViewById(R.id.playBookBgLayout);
        //初始设置一个layoutParams
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(IMAGE_SIZE,IMAGE_SIZE);
        layoutParams.topMargin = relativeLayout.getHeight()-200;
        layoutParams.leftMargin = 100;
        discInstance.setLayoutParams(layoutParams);
        int id = getResources().getIdentifier("com.example.helloworld:drawable/bluedisc", null, null);
        discInstance.setImageResource(id);
        discInstance.setTag(index);
        //discInstance.setTag(itemID);
        relativeLayout.addView(discInstance);
        discGroup[index] = discInstance;index++;

        //设置屏幕触摸事件
        discInstance.setOnTouchListener(this);
    }
    public void onPlayClick (View view) {
//        Path path = new Path();
//        path.moveTo(startX,startY);
//        path.lineTo(endX-150f,endY-150f);
//        ObjectAnimator animation = ObjectAnimator.ofFloat(imageView, "x", "y", path);
//        animation.setDuration(2000);
//        animation.setInterpolator(new AnticipateInterpolator());
//        animation.start();
        relativeLayout.removeView(relativeLayout.findViewWithTag(index-1));
        index--;

    }
//generate a list of ImageView inside relativelayout
//    public ArrayList<ImageView> getDiscGroup(){
//        ArrayList<ImageView> discGroup = new ArrayList<ImageView>();
//        for(int i=0;i<relativeLayout.getChildCount();i++){
//            if(relativeLayout.getChildAt(i) instanceof ImageView){
//                discGroup.add((ImageView) relativeLayout.getChildAt(i));
//            }
//        }
//        return discGroup;
//    }

    public boolean onClick(View view) {
        return false;
    }



}