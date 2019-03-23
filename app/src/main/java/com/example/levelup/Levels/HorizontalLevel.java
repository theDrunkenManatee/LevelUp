package com.example.levelup;

import android.graphics.RectF;

import com.example.levelup.coordinates.LevelBackground;

public class HorizontalLevel implements LevelBackground {

    private RectF mRect;

    public HorizontalLevel(int x, int y){
        int mScreenX = x;
        int mScreenY = y;
        float mLength = 4*(mScreenX / 5);
        float mHeight = mScreenY / 8;
        float mXCoord = (mScreenX/10);
        float mYCoord = (10) ;
        mRect = new RectF(mXCoord, mYCoord, mXCoord + mLength, mYCoord + mHeight);
    }

    @Override
    public RectF getLevel() {
        return mRect;
    }
}
