package com.example.levelup.displayObjects.levels;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.levelup.displayObjects.levels.Levels;

public class HorizontalLevel implements Levels {

    private RectF mRect;
    private float[] linePoints;
    private Paint shapePaint, linePaint;

    public HorizontalLevel(int x, int y){
        float mLength = 21*y/44;
        float mHeight = x/6;
        float mXCoord = 10;
        float mYCoord = (10) ;
        mRect = new RectF(mXCoord, mYCoord, mXCoord + mLength, mYCoord + mHeight);
        linePoints = new float[]{
                mXCoord + mLength/3, mYCoord, mXCoord + mLength/3, mYCoord + mHeight,
                mXCoord + 2*mLength/3, mYCoord, mXCoord + 2*mLength/3, mYCoord + mHeight,
                mXCoord + mLength/2, mYCoord, mXCoord + mLength/2, mYCoord + mHeight
        };
    }


    @Override
    public void setLinePaint(Paint paint) {
        this.linePaint = paint;
    }

    @Override
    public float[] getLines() {
        return linePoints;
    }

    @Override
    public RectF getShape() {
        return mRect;
    }

    @Override
    public void setShapePaint(Paint paint) {
        this.shapePaint = paint;
    }

    @Override
    public void drawSelf(Canvas canvas) {
        canvas.drawRect(getShape(), shapePaint);
        canvas.drawLines(getLines(), linePaint);
    }
}
