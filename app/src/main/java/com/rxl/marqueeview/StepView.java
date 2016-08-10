package com.rxl.marqueeview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * RenXL
 * 2016/7/5 0005
 */
public class StepView extends View {


    public StepView(Context context) {
        super(context);
    }

    public StepView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StepView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    Paint paint = new Paint();
    Paint pathPaint = new Paint();
    // DashPathEffect:DashPathEffect是PathEffect类的一个子类,可以使paint画出类似虚线的样子,
    // 并且可以任意指定虚实的排列方式. 好了，我们通过canvas.drawPath(path, pathPaint)绘制如下图
    // 代码中的float数组,必须是偶数长度,且>=2,指定了多少长度的实线之后再画多少长度的空白.
    // 如本代码中,绘制长度1的实线,再绘制长度2的空白,再绘制长度4的实线,再绘制长度8的空白,依次重复.1是偏移量,可以不用理会.
    DashPathEffect mEffect = new DashPathEffect(new float[]{8, 8, 8, 8}, 1);
    Path path = new Path();
    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        // 设置背景色
        setBackgroundColor(Color.parseColor("#01C7BD"));

        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(40);

        canvas.drawRect(200, 200, 800, 220, paint);
        canvas.drawCircle(350, 350, 100, paint);

        pathPaint.setAntiAlias(true);
        pathPaint.setColor(Color.WHITE);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStrokeWidth(2);

        path.moveTo(200,600);
        path.lineTo(800, 600);
        pathPaint.setPathEffect(mEffect);
        canvas.drawPath(path, pathPaint);
    }
}
