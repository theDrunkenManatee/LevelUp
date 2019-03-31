package com.example.levelup.displayObjects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class BallController {

    private Ball hBall, vBall, cBall;
    private Ball[] balls;
    private final static int ballColor = Color.WHITE;
    Dimensions screenDimensions;

    public BallController(Dimensions d){
        screenDimensions = d;
        setupBalls();
    }

    private void setupBalls() {
        createBalls();
        colorBalls();
    }

    private void createBalls(){
        hBall = new Ball(screenDimensions);
        vBall = new Ball(screenDimensions);
        cBall = new Ball(screenDimensions);
        balls = new Ball[] {hBall, vBall, cBall};
    }

    private void colorBalls(){
        Paint ballPaint = new Paint();
        ballPaint.setColor(ballColor);
        for(Ball ball: balls){
            ball.setShapePaint(ballPaint);
        }
    }

    public void drawBalls(Canvas canvas){
        for(Ball ball: balls){
            ball.drawSelf(canvas);
        }
    }

    public void setPositions(double x, double y){

    }

}
