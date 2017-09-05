package com.github.wheroj.cloneevent.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import com.github.wheroj.cloneevent.R;
import com.github.wheroj.cloneevent.common.Utils;

/**
 * Created by shopping on 2017/8/16 17:16.
 *
 * @description
 */

public class BorderImageView extends android.support.v7.widget.AppCompatImageView {

    private Paint paint;
    private float borderWidth = Utils.getPx(3);
    private int borderColorResId ;

    public BorderImageView(Context context) {
        this(context, null);
    }

    public BorderImageView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public BorderImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BorderImageView);
        borderWidth = typedArray.getDimension(R.styleable.BorderImageView_borderWidth, borderWidth);
        borderColorResId = typedArray.getColor(R.styleable.BorderImageView_borderColor, getResources().getColor(R.color.main_color));
        typedArray.recycle();
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(borderWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(borderColorResId);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        Path path = new Path();
        path.moveTo(borderWidth/2, borderWidth/2);
        path.lineTo(width - borderWidth/2, borderWidth/2);
        path.lineTo(width - borderWidth/2, height - borderWidth/2);
        path.lineTo(borderWidth/2, height - borderWidth/2);
        path.close();

        canvas.drawPath(path, paint);
    }
}
