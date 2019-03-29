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
        circleLevel = new CircleLevel(verticalLevel.getShape(), horizontalLevel.getShape());
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

    public void setLevelPaint(){
        setShapePaint();
        setLinePaint();
    }

    private void setLinePaint() {
        Paint linePaint = makePaint(Color.LTGRAY);
        circleLevel.setLinePaint(linePaint);
        verticalLevel.setLinePaint(linePaint);
        horizontalLevel.setLinePaint(linePaint);
    }

    private void setShapePaint() {
        Paint shapePaint = makePaint(Color.parseColor("#5D737E"));
        circleLevel.setShapePaint(shapePaint);
        verticalLevel.setShapePaint(shapePaint);
        horizontalLevel.setShapePaint(shapePaint);
    }

    public void drawLevels(){
        setLevelPaint();
        circleLevel.drawSelf(mCanvas);
        verticalLevel.drawSelf(mCanvas);
        horizontalLevel.drawSelf(mCanvas);
    }


}
