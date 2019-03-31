package com.example.levelup.displayObjects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.levelup.displayObjects.levels.Levels;

import java.util.HashMap;

public class BallController {

    private HashMap<LevelType, Ball> balls;
    private final static int ballColor = Color.WHITE;
    private Dimensions screenDimensions;
    HashMap<LevelType,Levels> levels;

    public BallController(Dimensions d, HashMap<LevelType,Levels> levels){
        screenDimensions = d;
        this.levels = levels;
        setupBalls();
    }

    private void setupBalls() {
        createBalls();
        colorBalls();
    }

    private void createBalls(){
        try {
            for (LevelType key: LevelType.values()){
                balls.put(key, new Ball(screenDimensions));
            }
        }
        catch (IllegalArgumentException e){
            Log.e("ERROR", "Null value passed as center");
        }
    }

    private void colorBalls(){
        Paint ballPaint = new Paint();
        ballPaint.setColor(ballColor);
        for(Ball ball: balls.values()){
            ball.setShapePaint(ballPaint);
        }
    }

    public void drawBalls(Canvas canvas){
        for(Ball ball: balls.values()){
            ball.drawSelf(canvas);
        }
    }

    public void setPositions(double x, double y){
        for(LevelType key: LevelType.values()){

        }
    }

}
