package com.example.levelup;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.levelup.displayObjects.Ball;
import com.example.levelup.displayObjects.CircleLevel;
import com.example.levelup.displayObjects.HorizontalLevel;
import com.example.levelup.displayObjects.VerticalLevel;

public class StaticView extends SurfaceView{

    Canvas mCanvas;
    // The size of the screen in pixels
    int mScreenX, mScreenY;
    Ball mBall;
    VerticalLevel verticalLevel;
    HorizontalLevel horizontalLevel;
    CircleLevel circleLevel;
    SurfaceHolder mOurHolder;


    public StaticView(Context context, int x, int y) {
        super(context);
        mScreenX = x;
        mScreenY = y;
        mOurHolder = getHolder();
        initObjects();
    }

    public void initObjects(){
        mBall = new Ball(mScreenX, mScreenY);
        verticalLevel = new VerticalLevel(mScreenX, mScreenY);
        horizontalLevel = new HorizontalLevel(mScreenX, mScreenY);
        circleLevel = new CircleLevel(verticalLevel.getLevelShape(), horizontalLevel.getLevelShape());
    }

    // Draw the newly updated scene
    public void makeBackground(Canvas canvas) {
        mCanvas = canvas;
        setBackgroundColor();
        drawLevels();
        drawButtons();
        drawCoordinateLabels();
    }

    public void setBackgroundColor(){
        mCanvas.drawColor(Color.parseColor("#333232"));
    }

    public Paint makePaint(int color){
        Paint paint = new Paint();
        paint.setColor(color);
        return paint;
    }

    public void drawLevels(){
        Paint shapePaint = makePaint(Color.parseColor("#5D737E"));
        Paint linePaint = makePaint(Color.LTGRAY);
        circleLevel.drawSelf(mCanvas, shapePaint, linePaint);
        verticalLevel.drawSelf(mCanvas, shapePaint, linePaint);
        horizontalLevel.drawSelf(mCanvas, shapePaint, linePaint);
    }

    private void drawButtons(){
        drawCalibrateButton();
        drawLockButton();
    }

    private void drawCalibrateButton(){
        Drawable calibrateButton = getResources().getDrawable(R.drawable.focus, null);
        calibrateButton.setBounds(10, 3*mScreenY/4, 60, 3*mScreenY/4 + 50);
        calibrateButton.draw(mCanvas);
    }

    private void drawLockButton(){
        Drawable lockButton = getResources().getDrawable(R.drawable.ic_action_name, null);
        lockButton.setBounds(10, 3*mScreenY/4 + 90, 60, 3*mScreenY/4 + 140);
        lockButton.draw(mCanvas);
    }

    private void drawCoordinateLabels(){
        Paint textPaint = makePaint(Color.WHITE);
        textPaint.setTextSize(40);
        mCanvas.drawText("X", mScreenX/4, 7*mScreenY/10, textPaint);
        mCanvas.drawText("Y", mScreenX/2, 7*mScreenY/10, textPaint);
    }
}
