package com.example.levelup.displayObjects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public interface Levels {
    public RectF getLevelShape();
    public float[] getLines();
    public void drawSelf(Canvas canvas, Paint shapePaint, Paint linePaint);
}
