package com.example.levelup;

import android.graphics.RectF;

public class VerticalLevel{

    // RectF is an object that holds four coordinates - just what we need
    private RectF mRect;

    // How long and high our mBat will be
    private float mLength;
    private float mHeight;

    // X is the far left of the rectangle
    private float mXCoord;

    // Y is the top coordinate
    private float mYCoord;

    // The screen length and width in pixels
    private int mScreenX;
    private int mScreenY;

    // This is the constructor method
// When we create an object from this class we will pass
// in the screen width and mHeight
    public VerticalLevel(int x, int y){

        mScreenX = x;
        mScreenY = y;

        // 1/8 screen width wide
        mLength = mScreenX / 5;

        // 1/25 screen mHeight high
        mHeight = mScreenY / 2;

        // Start mBat in roughly the sceen centre
        mXCoord = 4*(mScreenX/5) - 10;
        mYCoord = (mScreenY/5) ;

        mRect = new RectF(mXCoord, mYCoord, mXCoord + mLength, mYCoord + mHeight);
    }

    // This is a getter method to make the rectangle
    public RectF getRect(){
        return mRect;
    }

}
