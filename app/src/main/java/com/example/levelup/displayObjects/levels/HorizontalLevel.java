package com.example.levelup.displayObjects.levels;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.levelup.displayObjects.levels.Levels;

public class HorizontalLevel implements Levels {

    private RectF mRect;
    private float[] linePoints;

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
    public RectF getLevelShape() {
        return mRect;
    }

    @Override
    public float[] getLines() {
        return linePoints;
    }

    @Override
    public void drawSelf(Canvas canvas, Paint shapePaint, Paint linePaint) {
        canvas.drawRect(getLevelShape(), shapePaint);
        canvas.drawLines(getLines(), linePaint);
    }
}
