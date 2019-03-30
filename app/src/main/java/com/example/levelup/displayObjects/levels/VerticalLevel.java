package com.example.levelup.displayObjects.levels;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.levelup.displayObjects.Dimensions;
import com.example.levelup.displayObjects.levels.Levels;

public class VerticalLevel implements Levels {

    private RectF mRect;
    private float[] linePoints;
    private Paint shapePaint, linePaint;

    public VerticalLevel(Dimensions d){
        int mLength = d.getWidth() / 6;
        int mHeight = 21*d.getHeight()/44;
        int mXCoord = d.getWidth()- mLength - 10;
        int mYCoord = (d.getHeight()/8) ;
        mRect = new RectF(mXCoord, mYCoord, mXCoord + mLength, mYCoord + mHeight);
        linePoints = new float[]{
                mXCoord, mYCoord + mHeight/3, mXCoord + mLength, mYCoord + mHeight/3,
                mXCoord, mYCoord + 2*mHeight/3, mXCoord + mLength, mYCoord + 2*mHeight/3,
                mXCoord, mYCoord + mHeight/2, mXCoord + mLength, mYCoord + mHeight/2
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
