package com.example.levelup.displayObjects.levels;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.levelup.displayObjects.Dimensions;

public class VerticalLevel implements Levels {

    private RectF mRect;
    private float[] linePoints;
    private Paint shapePaint, linePaint;
    private Dimensions dimensions;

    public VerticalLevel(Dimensions screenSize){
        setupShapeDimensions(screenSize);
        createLevel(screenSize);
    }

    private void createLevel(Dimensions screenSize) {
        int mXCoord = screenSize.getWidth()- dimensions.getWidth() - 10;
        int mYCoord = (screenSize.getHeight()/8) ;
        mRect = new RectF(mXCoord, mYCoord, mXCoord + dimensions.getWidth(),
                mYCoord + dimensions.getHeight());
        linePoints = new float[]{
                mXCoord, mYCoord + dimensions.getHeight()/3, mXCoord + dimensions.getWidth(),
                mYCoord + dimensions.getHeight()/3,
                mXCoord, mYCoord + 2*dimensions.getHeight()/3, mXCoord + dimensions.getWidth(),
                mYCoord + 2*dimensions.getHeight()/3,
                mXCoord, mYCoord + dimensions.getHeight()/2, mXCoord + dimensions.getWidth(),
                mYCoord + dimensions.getHeight()/2
        };
    }

    private void setupShapeDimensions(Dimensions screenSize) {
        int mWidth = screenSize.getWidth() / 6;
        int mHeight = 21*screenSize.getHeight()/44;
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
