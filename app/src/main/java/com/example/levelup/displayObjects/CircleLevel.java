package com.example.levelup.displayObjects;

import android.graphics.RectF;

public class CircleLevel implements Levels {

    private RectF mRect;
    private float[] linePoints;

    public CircleLevel(int x, int y){
        float mDiameter = 3*(x / 5);
        float mLeft = x / 10;
        float mTop = y / 4;
        mRect = new RectF(mLeft, mTop, mLeft + mDiameter, mTop + mDiameter);
        linePoints = new float[] {
                mLeft + mDiameter/2, mTop, mLeft + mDiameter/2, mTop + mDiameter,
                mLeft, mTop + mDiameter/2, mLeft + mDiameter, mTop + mDiameter/2
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
