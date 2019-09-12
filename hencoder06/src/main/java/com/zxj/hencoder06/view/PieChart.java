package com.zxj.hencoder06.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.zxj.hencoder06.Utils;

/**
 * function:
 *
 * <p>
 * Created by Zxj on 2019/9/12.
 */
public class PieChart extends View {
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    RectF bounds=new RectF();
    private  final float RADIUS = Utils.dp2px(150);

    public PieChart(Context context,  AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bounds.set(getWidth()/2-RADIUS,getHeight()/2-RADIUS,getWidth()/2+RADIUS,getHeight()/2+RADIUS);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(bounds,0,60,true,mPaint);
    }
}
