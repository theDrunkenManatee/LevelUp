package com.example.levelup;

import android.graphics.RectF;

import com.example.levelup.coordinates.LevelBackground;

public class VerticalLevel implements LevelBackground {

    private RectF mRect;

    public VerticalLevel(int x, int y){
        float mLength = x / 5;
        float mHeight = y / 2;
        float mXCoord = 4*(x/5) - 10;
        float mYCoord = (y/5) ;
        mRect = new RectF(mXCoord, mYCoord, mXCoord + mLength, mYCoord + mHeight);
    }

    @Override
    public RectF getLevel() {
        return mRect;
    }
}
