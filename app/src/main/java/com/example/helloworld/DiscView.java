package com.example.helloworld;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;


public class DiscView extends View{
    private float bitmapX;//设置view的X坐标
    private float bitmapY;//设置view的Y坐标
    //自定义View需要实现一个显示的构造方法，并且重写onDraw方法，一切操作都将在这个方法上执行
    public DiscView(Context context)
    {
        super(context);
        bitmapX=200;//设置view的初始位置
        bitmapY=100;

    }
    //getters and setters
    public float getBitmapX() {
        return bitmapX;
    }

    public void setBitmapX(float bitmapX) {
        this.bitmapX = bitmapX;
    }

    public float getBitmapY() {
        return bitmapY;
    }

    public void setBitmapY(float bitmapY) {
        this.bitmapY = bitmapY;
    }

    public void onDraw(Canvas canvas) {//重写draw方法
        super.onDraw(canvas);
        Paint paint=new Paint();//画笔
        Bitmap bitmap= BitmapFactory.decodeResource(this.getResources(),R.drawable.bluedisc);
        canvas.drawBitmap(bitmap,bitmapX,bitmapY,paint);//canvas画布绘制图片
        if(bitmap.isRecycled())
            bitmap.recycle();
    }
}

