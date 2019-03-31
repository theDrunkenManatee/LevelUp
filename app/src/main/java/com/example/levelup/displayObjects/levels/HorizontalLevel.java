package com.example.levelup.displayObjects.levels;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

import com.example.levelup.displayObjects.Dimensions;
import com.example.levelup.displayObjects.levels.Levels;

public class HorizontalLevel implements Levels {

    private RectF mRect;
    private float[] linePoints;
    private Paint shapePaint, linePaint;
    private Dimensions dimensions;
    private Point center;

    public HorizontalLevel(Dimensions screenSize){
        setupShapeDimensions(screenSize);
        createLevel(screenSize);
    }

    private void createLevel(Dimensions screenSize) {
        int mXCoord = 10;
        int mYCoord = (10) ;
        mRect = new RectF(mXCoord, mYCoord, mXCoord + dimensions.getWidth(),
                mYCoord + dimensions.getHeight());
        center = new Point(dimensions.getWidth()/2, dimensions.getHeight()/2);
        float thirds = dimensions.getWidth()/3;
        linePoints = new float[]{
                mXCoord + thirds, mYCoord, mXCoord + thirds, mYCoord + dimensions.getHeight(),
                mXCoord + 2*thirds, mYCoord, mXCoord + 2*thirds, mYCoord + dimensions.getHeight(),
                mXCoord + center.x, mYCoord, mXCoord + center.x, mYCoord + dimensions.getHeight()
        };
    }

    private void setupShapeDimensions(Dimensions screenSize) {
        int mWidth = 21*screenSize.getHeight()/44;
        int mHeight = screenSize.getWidth()/6;
        dimensions = new Dimensions(mWidth, mHeight);
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
        canvas.drawRect(getShape(), shapePaint);
        canvas.drawLines(getLines(), linePaint);
    }
}
