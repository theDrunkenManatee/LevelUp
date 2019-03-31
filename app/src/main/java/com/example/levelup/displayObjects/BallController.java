package com.example.levelup.displayObjects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import com.example.levelup.displayObjects.levels.Levels;

import java.util.HashMap;

public class BallController {

    private HashMap<LevelType, Ball> balls;
    private final static int ballColor = Color.WHITE;
    private Dimensions screenDimensions;

    public BallController(Dimensions d){
        screenDimensions = d;
        setupBalls();
    }

    private void setupBalls() {
        createBalls();
        colorBalls();
    }

    private void createBalls(){
        try {
            balls = new HashMap<LevelType, Ball>();
            for (LevelType key: LevelType.values()){
                balls.put(key, new Ball(screenDimensions));
            }
        }
        catch (IllegalArgumentException e){
            Log.e("AAA", "Null value passed as center");
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

    public void setPositions(HashMap<LevelType, Point> coordinates){
        for(LevelType key: LevelType.values()){
            balls.get(key).setBallPosition(coordinates.get(key));
        }
    }
}
