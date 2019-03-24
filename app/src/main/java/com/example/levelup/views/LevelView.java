package com.example.levelup.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.levelup.displayObjects.Ball;
import com.example.levelup.displayObjects.levels.CircleLevel;
import com.example.levelup.displayObjects.levels.HorizontalLevel;
import com.example.levelup.displayObjects.levels.VerticalLevel;
import com.example.levelup.views.BottomView;

public class LevelView extends SurfaceView{

    Canvas mCanvas;
    // The size of the screen in pixels
    int mScreenX, mScreenY;
    Ball mBall;
    VerticalLevel verticalLevel;
    HorizontalLevel horizontalLevel;
    CircleLevel circleLevel;
    SurfaceHolder mOurHolder;
    BottomView bottomView;
    Context context;

    public LevelView(Context c, int x, int y) {
        super(c);
        context = c;
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
}