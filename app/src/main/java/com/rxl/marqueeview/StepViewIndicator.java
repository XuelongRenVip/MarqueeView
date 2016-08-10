package com.rxl.marqueeview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * RenXL
 * 2016/7/6 0006
 */
public class StepViewIndicator extends View {

    private Context context;
    private float indicatorNum;
    private Paint paint, pathPaint;
    private int defaltColor;
    private DashPathEffect mEffect;
    private List<Integer> circleX;
    private float circleRadius;
    private float linePadding;
    private float completedLineHeight;
    private Drawable attention, completed, defalut;

    public StepViewIndicator(Context context) {
        this(context, null);
    }

    public StepViewIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StepViewIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        mEffect = new DashPathEffect(new float[]{8, 8, 8, 8}, 1);
        defaltColor = Color.parseColor("#01C7BD");
        paint = new Paint();
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);

        pathPaint = new Paint();
        pathPaint.setAntiAlias(true);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStrokeWidth(2);
        pathPaint.setColor(Color.WHITE);
        pathPaint.setPathEffect(mEffect);

        circleX = new ArrayList<>();


        indicatorNum = dip2px(context, 60);
        Log.i("MainActivity", getWidth() + "宽度");
        circleRadius = 0.28f * indicatorNum;
        linePadding = 0.85f * indicatorNum;
        completedLineHeight = 0.05f * indicatorNum;

        attention = ContextCompat.getDrawable(context, R.mipmap.attention);
        completed = ContextCompat.getDrawable(context, R.mipmap.complted);
        defalut = ContextCompat.getDrawable(context, R.mipmap.default_icon);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = (int) (indicatorNum * 2);
        if (widthMeasureSpec != MeasureSpec.UNSPECIFIED)
            width = MeasureSpec.getSize(widthMeasureSpec);
        int height = (int) indicatorNum;
        if (heightMeasureSpec != MeasureSpec.UNSPECIFIED)
            height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}
