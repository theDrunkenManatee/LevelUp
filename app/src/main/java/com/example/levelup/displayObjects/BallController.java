package com.example.levelup.displayObjects;

import android.graphics.Color;
import android.graphics.Paint;

import com.example.levelup.displayObjects.Ball;

public class BallController {

    private Ball hBall, vBall, cBall;
    private Ball[] balls;
    final static int ballColor = Color.WHITE;
    Dimensions dimensions;

    public BallController(Dimensions d){
        dimensions = d;
        setupBalls();
    }

    private void setupBalls() {
        createBalls();
        colorBalls();
    }

    private void createBalls(){
        hBall = new Ball(dimensions);
        vBall = new Ball(dimensions);
        cBall = new Ball(dimensions);
        balls = new Ball[] {hBall, vBall, cBall};
    }

    private void colorBalls(){
        Paint ballPaint = new Paint();
        ballPaint.setColor(ballColor);
        for(Ball ball: balls){
            ball.setShapePaint(ballPaint);
        }
    }

    private void setPositions(double x, double y){

    }

}
