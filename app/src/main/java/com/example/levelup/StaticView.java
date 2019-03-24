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

    public void drawLevels(){
        drawLevelShapes();
        drawLevelLines();
    }

    private void drawLevelShapes() {
        mPaint.setColor(Color.parseColor("#5D737E"));
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
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(40);
        mCanvas.drawText("X", mScreenX/4, 7*mScreenY/10, mPaint);
        mCanvas.drawText("Y", mScreenX/2, 7*mScreenY/10, mPaint);
    }
}
