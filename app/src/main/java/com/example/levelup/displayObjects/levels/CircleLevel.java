package com.example.levelup.displayObjects.levels;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

import com.example.levelup.displayObjects.Dimensions;
import com.example.levelup.displayObjects.levels.Levels;

public class CircleLevel implements Levels {

    private RectF mRect;
    private float[] linePoints;
    private Paint shapePaint, linePaint;
    private Dimensions dimensions;
    private Point center;

    public CircleLevel(Levels vLevel, Levels hLevel){
        dimensions = new Dimensions(hLevel.getDimensions().getWidth(),
                vLevel.getDimensions().getHeight());
        mRect = new RectF(hLevel.getShape().left, vLevel.getShape().top,
                hLevel.getShape().right, vLevel.getShape().bottom);
        center = new Point((int) hLevel.getShape().left + (dimensions.getWidth() / 2),
                (int) vLevel.getShape().top + (dimensions.getHeight() / 2));
        linePoints = new float[] {
                mRect.left + hLevel.getCenter().x, mRect.top,  mRect.left +  hLevel.getCenter().x, mRect.bottom,
                mRect.left, mRect.top + vLevel.getCenter().y,  mRect.right, mRect.top +  vLevel.getCenter().y
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
    public Point getCenter() {
        return center;
    }

    @Override
    public RectF getShape() {
        return mRect;
    }

    @Override
    public Dimensions getDimensions() {
        return dimensions;
    }

    @Override
    public void setShapePaint(Paint paint) {
        this.shapePaint = paint;
    }

    @Override
    public void drawSelf(Canvas canvas) {
        canvas.drawOval(getShape(), shapePaint);
        canvas.drawLines(getLines(), linePaint);
    }
}
