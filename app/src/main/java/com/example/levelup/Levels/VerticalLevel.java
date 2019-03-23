package com.example.levelup.Levels;

import android.graphics.RectF;

import com.example.levelup.Levels.LevelBackground;

public class VerticalLevel implements LevelBackground {

    private RectF mRect;
    private float[] linePoints;

    public VerticalLevel(int x, int y){
        float mLength = x / 5;
        float mHeight = y / 2;
        float mXCoord = 4*(x/5) - 10;
        float mYCoord = (y/5) ;
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
