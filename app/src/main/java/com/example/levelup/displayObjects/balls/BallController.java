package com.example.levelup.displayObjects.balls;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import com.example.levelup.displayObjects.Dimensions;
import com.example.levelup.displayObjects.LevelType;
import com.example.levelup.displayObjects.balls.Ball;

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
    }

    public void setBallColor(int color){
        colorBalls(color);
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

    private void colorBalls(int color) {
        Paint ballPaint = new Paint();
        ballPaint.setColor(color);
        for (Ball ball : balls.values()) {
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

    public int getBallRadius(){
        return balls.get(LevelType.CIRCLE).getDimensions().getHeight()/2;
    }
}
