package com.github.wheroj.cloneevent.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.github.wheroj.cloneevent.R;
import com.github.wheroj.cloneevent.common.Utils;

/**
 * Created by shopping on 2017/8/15 16:26.
 *
 * @description
 */

public class DrawBoard extends SurfaceView implements SurfaceHolder.Callback{

    private Paint mPaint;

    private Path mPath = new Path();

    private int MIN_DISTANCE = 5;
    private final int INVALIDATE = 100;

    private boolean startDraw = false;
    private SurfaceHolder surfaceHolder;

    private Bitmap bitmap;

    public DrawBoard(Context context) {
        this(context, null);
    }

    public DrawBoard(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public DrawBoard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.red_f6));
        mPaint.setStrokeWidth(Utils.getPx(3));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);//保持屏幕不熄灭
        setBackgroundColor(getResources().getColor(R.color.white));
        setBackgroundResource(R.drawable.transparent);
        setZOrderOnTop(true);
        surfaceHolder.setFormat(PixelFormat.TRANSPARENT);

        bitmap = Bitmap.createBitmap(Utils.getPx(300), Utils.getPx(300), Bitmap.Config.ARGB_8888);
    }

    private float lastX,lastY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mPath != null) {
                    mPath.reset();
                } else {
                    mPath = new Path();
                }
                lastX = event.getX();
                lastY = event.getY();
                mPath.moveTo(lastX, lastY);
                break;
            case MotionEvent.ACTION_MOVE:
                float nowY = event.getY();
                float nowX = event.getX();
                if (Math.abs(nowX - lastX) > MIN_DISTANCE
                        || Math.abs(nowY - lastY) > MIN_DISTANCE) {
                    lastX = nowX;
                    lastY = nowY;
                    mPath.lineTo(lastX, lastY);
                    Log.e("View", "----------draw----------" + nowX + "画图, " + nowY);
                }
                Log.e("View", nowX + "画图, " + nowY);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                Log.e("View", "----画图-----");
                break;
        }
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        startDraw = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (startDraw) {
                    draw();
                }
            }
        }).start();
    }

    private void draw() {
        if (clearAll) {
            int i = 4;
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            while (i > 0) {//为什么需要循环四次才能彻底清楚canvas呢？
                Canvas canvas = surfaceHolder.lockCanvas();
                canvas.drawPaint(mPaint);
                if (canvas != null) surfaceHolder.unlockCanvasAndPost(canvas);
                i--;
            }
            if (mPath != null) mPath.reset();
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
            clearAll = false;
        } else if (mPath != null && !mPath.isEmpty()) {
            Canvas localCanvas = new Canvas(bitmap);
            Canvas canvas = surfaceHolder.lockCanvas();
            canvas.drawPath(mPath, mPaint);
            localCanvas.drawPath(mPath, mPaint);

            //)结束锁定画图，并提交改变，将图形显示
            if (canvas != null) surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        startDraw = false;
    }

    private boolean clearAll = false;
    /**
     * 清楚画布内容
     */
    public void reset(){
        clearAll = true;
    }
}
