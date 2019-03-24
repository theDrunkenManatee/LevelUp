package com.example.levelup.displayObjects;

import android.graphics.RectF;

public class VerticalLevel implements Levels {

    private RectF mRect;
    private float[] linePoints;

    public VerticalLevel(int x, int y){
        float mLength = x / 6;
        float mHeight = 21*y/44;
        float mXCoord = x - mLength - 10;
        float mYCoord = (y/8) ;
        mRect = new RectF(mXCoord, mYCoord, mXCoord + mLength, mYCoord + mHeight);
        linePoints = new float[]{
                mXCoord, mYCoord + mHeight/3, mXCoord + mLength, mYCoord + mHeight/3,
                mXCoord, mYCoord + 2*mHeight/3, mXCoord + mLength, mYCoord + 2*mHeight/3,
                mXCoord, mYCoord + mHeight/2, mXCoord + mLength, mYCoord + mHeight/2
        };
    }

    @Override
    public RectF getLevelShape() {
        return mRect;
    }

    @Override
    public float[] getLines(){
        return linePoints;
    }
}
