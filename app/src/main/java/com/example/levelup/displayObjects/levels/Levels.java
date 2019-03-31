package com.example.levelup.displayObjects.levels;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

import com.example.levelup.displayObjects.DisplayObject;

public interface Levels extends DisplayObject {
    public void setLinePaint(Paint paint);
    public float[] getLines();
    public Point getCenter();
}
