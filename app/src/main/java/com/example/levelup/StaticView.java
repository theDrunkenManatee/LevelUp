package com.example.levelup;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.levelup.displayObjects.Ball;
import com.example.levelup.displayObjects.CircleLevel;
import com.example.levelup.displayObjects.HorizontalLevel;
import com.example.levelup.displayObjects.VerticalLevel;

public class StaticView extends SurfaceView{

    Canvas mCanvas;
    Paint mPaint;
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
        mPaint = new Paint();
        initObjects();
    }

    public void initObjects(){
        mBall = new Ball(mScreenX, mScreenY);
        verticalLevel = new VerticalLevel(mScreenX, mScreenY);
        horizontalLevel = new HorizontalLevel(mScreenX, mScreenY);
        circleLevel = new CircleLevel(mScreenX, mScreenY);
    }

    // Draw the newly updated scene
    public void makeBackground(Canvas canvas) {
        mCanvas = canvas;
        setBackgroundColor();
        drawLevels();
    }

    public void setBackgroundColor(){
        mCanvas.drawColor(Color.LTGRAY);
    }

    public void drawLevels(){
        drawLevelShapes();
        drawLevelLines();
    }

    private void drawLevelShapes() {
        mPaint.setColor(Color.parseColor("#3C6E71"));
        mCanvas.drawRect(verticalLevel.getLevelShape(), mPaint);
        mCanvas.drawRect(horizontalLevel.getLevelShape(), mPaint);
        mCanvas.drawOval(circleLevel.getLevelShape(), mPaint);
    }

    private void drawLevelLines() {
        mPaint.setColor(Color.LTGRAY);
        mCanvas.drawLines(verticalLevel.getLines(), mPaint);
        mCanvas.drawLines(horizontalLevel.getLines(), mPaint);
        mCanvas.drawLines(circleLevel.getLines(), mPaint);
        // I can make an "inner circle" for the circle level with arcs later
        // but that seems like more time than it's worth right now.
    }
}
