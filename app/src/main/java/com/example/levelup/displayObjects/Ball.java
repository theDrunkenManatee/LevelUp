package com.example.levelup.displayObjects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.Random;

// Adapted from http://androidgameprogramming.com/programming-a-pong-game/

public class Ball implements DisplayObject{
    private RectF mRect;
    private float mXVelocity;
    private float mYVelocity;
    private Dimensions dimensions;
    private float mRadius;
    private Paint shapePaint;

    public Ball(Dimensions screenSize){
        dimensions = new Dimensions(screenSize.getWidth() / 100, screenSize.getWidth()/100);
        mRadius = dimensions.getHeight()/2;
    /*
        Start the ball travelling straight up
        at a quarter of the screen height per second
    */
        mYVelocity = screenSize.getHeight() / 4;
        mXVelocity = mYVelocity;
        mRect = new RectF();

    }

    // Change the position each frame
    public void update(long fps){
        mRect.left = mRect.left + (mXVelocity / fps);
        mRect.top = mRect.top + (mYVelocity / fps);
        mRect.right = mRect.left + dimensions.getWidth();
        mRect.bottom = mRect.top - dimensions.getHeight();
    }

    // Reverse the vertical heading
    public void reverseYVelocity(){
        mYVelocity = -mYVelocity;
    }

    // Reverse the horizontal heading
    public void reverseXVelocity(){
        mXVelocity = -mXVelocity;
    }

    public void setRandomXVelocity(){

        // Generate a random number either 0 or 1
        Random generator = new Random();
        int answer = generator.nextInt(2);

        if(answer == 0){
            reverseXVelocity();
        }
    }

    public void clearObstacleY(float y){
        mRect.bottom = y;
        mRect.top = y - dimensions.getHeight();
    }

    public void clearObstacleX(float x){
        mRect.left = x;
        mRect.right = x + dimensions.getWidth();
    }

    public void reset(int x, int y){
        mRect.left = x / 2;
        mRect.top = y - 20;
        mRect.right = x / 2 + dimensions.getWidth();
        mRect.bottom = y - 20 - dimensions.getHeight();
    }

    public void setBallPosition(float xCenter, float yCenter){
        mRect.left = xCenter - mRadius;
        mRect.right = xCenter + mRadius;
        mRect.top = yCenter + mRadius;
        mRect.bottom = yCenter - mRadius;
    }

    @Override
    public RectF getShape() {
        return mRect;
    }

    @Override
    public Dimensions getDimensions() {
        return dimensions;
    }

    @Override
    public void setShapePaint(Paint paint) {
        shapePaint = paint;
    }

    @Override
    public void drawSelf(Canvas canvas) {
        canvas.drawOval(getShape(), shapePaint);
    }
}
