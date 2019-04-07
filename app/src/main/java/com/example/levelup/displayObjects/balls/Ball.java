package com.example.levelup.displayObjects.balls;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

import com.example.levelup.displayObjects.Dimensions;
import com.example.levelup.displayObjects.DisplayObject;

import java.util.Random;
import java.util.logging.Level;

// Adapted from http://androidgameprogramming.com/programming-a-pong-game/

public class Ball implements DisplayObject {
    private RectF mRect;
    private Dimensions dimensions;
    private Paint shapePaint;

    public Ball(Dimensions screenSize){
        dimensions = new Dimensions(scaleSide(screenSize), scaleSide(screenSize));
        mRect = new RectF();
    }

    private int scaleSide(Dimensions screenSize){
        return screenSize.getWidth() / 20;
    };

    public void setBallPosition(Point center){
        float mRadius = dimensions.getHeight()/2;
        mRect.left = center.x - mRadius;
        mRect.right = center.x + mRadius;
        mRect.top = center.y + mRadius;
        mRect.bottom = center.y - mRadius;
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
