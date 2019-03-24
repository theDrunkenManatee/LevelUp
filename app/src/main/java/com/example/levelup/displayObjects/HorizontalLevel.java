package com.example.levelup.displayObjects;

import android.graphics.RectF;

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
}
