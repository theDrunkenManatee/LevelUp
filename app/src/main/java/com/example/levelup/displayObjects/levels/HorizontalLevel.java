package com.example.levelup.displayObjects.levels;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.levelup.displayObjects.Dimensions;
import com.example.levelup.displayObjects.levels.Levels;

public class HorizontalLevel implements Levels {

    private RectF mRect;
    private float[] linePoints;
    private Paint shapePaint, linePaint;

    public HorizontalLevel(Dimensions d){
        int mLength = 21*d.getHeight()/44;
        int mHeight = d.getWidth()/6;
        int mXCoord = 10;
        int mYCoord = (10) ;
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
