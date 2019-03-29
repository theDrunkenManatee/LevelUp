package com.example.levelup.displayObjects.levels;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.levelup.displayObjects.levels.Levels;

public class CircleLevel implements Levels {

    private RectF mRect;
    private float[] linePoints;
    private Paint shapePaint, linePaint;

    public CircleLevel(RectF vLevel, RectF hLevel){
        float mDiameter = vLevel.bottom - vLevel.top;
        mRect = new RectF(hLevel.left, vLevel.top, hLevel.right, vLevel.bottom);
        linePoints = new float[] {
                hLevel.left + mDiameter/2, vLevel.top, hLevel.left + mDiameter/2, vLevel.bottom,
                hLevel.left, vLevel.top + mDiameter/2, hLevel.right, vLevel.top + mDiameter/2
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
        canvas.drawOval(getShape(), shapePaint);
        canvas.drawLines(getLines(), linePaint);
        // I can make an "inner circle" for the circle level with arcs later
        // but that seems like more time than it's worth right now.
    }

}
