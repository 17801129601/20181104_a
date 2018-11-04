package com.bwie.a20181104_a;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

/**
 * date:2018/11/4
 * author:别来无恙(别来无恙)
 * function:
 */
public class Haha extends View implements View.OnClickListener {

    //转盘颜色
    private int[] color = new int[]{Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW,Color.GRAY,Color.WHITE};
    //转盘内容
    private String[] textColor = new String[]{"11111","22222","33333","44444","55555","66666"};
    //屏幕的中心宽高
    private int mWidth;
    private int mHeight;
    private Paint mPaint;
    private Animation mAnimation;
    private boolean isRote;

    public Haha(Context context) {
        this(context,null);
    }

    public Haha(Context context,AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public Haha(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取屏幕
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        //获取屏幕的宽和高
        int widthPixels = displayMetrics.widthPixels;
        int heightPixels = displayMetrics.heightPixels;
        //获取屏幕宽高的一半(以便获取屏幕的中心点)
        mWidth = widthPixels / 2;
        mHeight = heightPixels / 2;
        //初始化画笔Paint
        initPaint();
        //旋转动画
        initAnimation();
        //点击事件
        setOnClickListener(this);
    }



    private void initPaint() {
        //创建画笔
        mPaint = new Paint();
        //给画笔设置颜色
        mPaint.setColor(Color.RED);
        //设置宽度
        mPaint.setStrokeWidth(2);
        //去除毛边
        mPaint.setAntiAlias(true);
        //设置类型  填充
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //首先平移到中间
        canvas.translate(mWidth,mHeight);
        //外边的大圆
        RectF rectF = new RectF(-260,-260,260,260);
        //定义每个的大小
        float start = 60;
        //循环  分为6个
        for (int i = 0; i < 6; i++) {
            //循环 为每个设置不同的颜色
            mPaint.setColor(color[i]);
            //弄好画笔  开始画圆弧
            canvas.drawArc(rectF,start*i,60,true,mPaint);
        }
        //画笔设置颜色
        mPaint.setColor(Color.RED);
        //开始画圆
        canvas.drawCircle(0,0,100,mPaint);
        //设置中间字体颜色和大小
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(25);
        //创建
        Rect rect = new Rect();
        //输入字  中间的字
        mPaint.getTextBounds("srart",0,5,rect);
        //定义rect宽和高
        int width = rect.width();
        int height = rect.height();
        //把字固定上去
        canvas.drawText("start",-width/2,height/2,mPaint);

        //文字
        RectF rectF1 = new RectF(-200,-200,200,200);
        //文字的循环
        for (int i = 0; i < 6; i++) {
            //设置文字的颜色
            mPaint.setColor(Color.BLACK);
            //设置颜色的大小
            mPaint.setTextSize(25);
            //创建一个Path
            Path path = new Path();
            //把文字设置进去
            path.addArc(rectF1,start*i+20,60);
            //放着圆盘里边
            canvas.drawTextOnPath(textColor[i],path,0,0,mPaint);
        }
    }

    /**
     * 动画
     */
    private void initAnimation() {
        //创建旋转动画
        mAnimation = new RotateAnimation(0,360,mWidth,mHeight);
        //设置重复的次数
        mAnimation.setRepeatCount(-1);
        //设置终止填充
        mAnimation.setFillAfter(true);
        //设置时间
        mAnimation.setDuration(400);
        //是Animation的xml一个属性
        mAnimation.setInterpolator(new LinearInterpolator());
        //设置重复的模式
        mAnimation.setRepeatMode(Animation.RESTART);
    }
    private void start(){
        isRote = true;
        //开始旋转
        startAnimation(mAnimation);
    }
    private void stop(){
        isRote = false;
        //停止旋转
        clearAnimation();
    }

    //点击事件
    @Override
    public void onClick(View v) {
        if (isRote){
            //如果为true
            stop();
        }else {
            start();
        }
    }

}
