package com.example.levelup.displayObjects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public interface DisplayObject {
    public RectF getShape();
    public Dimensions getDimensions();
    public void setShapePaint(Paint paint);
    public void drawSelf(Canvas canvas);
}
