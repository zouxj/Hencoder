package com.zxj.hencoder06.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.zxj.hencoder06.Utils;

/**
 * function:
 *
 * <p>
 * Created by Zxj on 2019/9/12.
 */
public class DashViewZou extends View {

    private  Paint mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private  final float RADIUS = Utils.dp2px(150);
    private  final int ANGLE = 120;
    private static final float LENGTH = Utils.dp2px(100);
    private Path dash = new Path();
    private PathDashPathEffect effect;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DashViewZou(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(Utils.dp2px(2));
        dash.addRect(0, 0, Utils.dp2px(2), Utils.dp2px(10), Path.Direction.CW);
        Path arc = new Path();
        arc.addArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS , getHeight() / 2 + RADIUS, 90 + ANGLE / 2, 360 - ANGLE);
        PathMeasure pathMeasure = new PathMeasure(arc, false);
        effect=new PathDashPathEffect(dash,(pathMeasure.getLength()-Utils.dp2px(2))/20,0, PathDashPathEffect.Style.ROTATE);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //划线
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS , getHeight() / 2 + RADIUS, 90 + ANGLE / 2, 360 - ANGLE, false, mPaint);
        //画刻度
        mPaint.setPathEffect(effect);
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS , getHeight() / 2 + RADIUS, 90 + ANGLE / 2, 360 - ANGLE, false, mPaint);
        mPaint.setPathEffect(null);
        // 画指针
        canvas.drawLine(getWidth() / 2, getHeight() / 2,
                (float) Math.cos(Math.toRadians(getAngleFromMark(5))) * LENGTH + getWidth() / 2,
                (float) Math.sin(Math.toRadians(getAngleFromMark(5))) * LENGTH + getHeight() / 2,
                mPaint);

    }

    int getAngleFromMark(int mark) {
        return (int) (90 + (float) ANGLE / 2 + (360 - (float) ANGLE) / 20 * mark);
    }

}
