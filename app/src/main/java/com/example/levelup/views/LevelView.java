package com.example.levelup.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.levelup.displayObjects.Ball;
import com.example.levelup.displayObjects.Dimensions;
import com.example.levelup.displayObjects.LevelType;
import com.example.levelup.displayObjects.levels.CircleLevel;
import com.example.levelup.displayObjects.levels.HorizontalLevel;
import com.example.levelup.displayObjects.levels.Levels;
import com.example.levelup.displayObjects.levels.VerticalLevel;
import com.example.levelup.views.BottomView;

import java.util.HashMap;

public class LevelView extends SurfaceView{

    Canvas mCanvas;
    // The size of the screen in pixels
    Dimensions dimensions;
    Ball mBall;
    HashMap<LevelType, Levels> levels;
    SurfaceHolder mOurHolder;
    BottomView bottomView;
    Context context;

    public LevelView(Context c, Dimensions d) {
        super(c);
        context = c;
        dimensions = d;
        mOurHolder = getHolder();
        initObjects();
    }

    public void initObjects(){
        mBall = new Ball(dimensions);
        levels = new HashMap<>();
        levels.put(LevelType.VERTICAL, new VerticalLevel(dimensions));
        levels.put(LevelType.HORIZONTAL, new HorizontalLevel(dimensions));
        levels.put(LevelType.CIRCLE, new CircleLevel(levels.get(LevelType.VERTICAL), levels.get(LevelType.HORIZONTAL)));
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
        for(Levels level: levels.values()){
            level.setLinePaint(linePaint);
        }
    }

    private void setShapePaint() {
        Paint shapePaint = makePaint(Color.parseColor("#5D737E"));
        for(Levels level: levels.values()){
            level.setShapePaint(shapePaint);
        }
    }

    public void drawLevels(){
        setLevelPaint();
        for(Levels level: levels.values()){
            level.drawSelf(mCanvas);
        }
    }


}
