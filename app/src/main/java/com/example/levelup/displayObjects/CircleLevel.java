package com.example.levelup.displayObjects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

public class CircleLevel implements Levels {

    private RectF mRect;
    private float[] linePoints;

    public CircleLevel(RectF vLevel, RectF hLevel){
        float mDiameter = vLevel.bottom - vLevel.top;
        mRect = new RectF(hLevel.left, vLevel.top, hLevel.right, vLevel.bottom);
        linePoints = new float[] {
                hLevel.left + mDiameter/2, vLevel.top, hLevel.left + mDiameter/2, vLevel.bottom,
                hLevel.left, vLevel.top + mDiameter/2, hLevel.right, vLevel.top + mDiameter/2
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
        canvas.drawOval(getLevelShape(), shapePaint);
        canvas.drawLines(getLines(), linePaint);
        // I can make an "inner circle" for the circle level with arcs later
        // but that seems like more time than it's worth right now.
    }
}
