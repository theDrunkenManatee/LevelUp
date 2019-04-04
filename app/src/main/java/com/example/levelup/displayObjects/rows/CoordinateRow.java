package com.example.levelup.displayObjects.rows;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import org.w3c.dom.Text;

public interface CoordinateRow {
    public void setText_Paint(Paint paint);
    public void setText_X(String x);
    public void setText_Y(String y);
    public void setButton(Drawable b);
    public Rect getButtonBounds();
    public void drawCoordinates(Canvas canvas);
    public void drawTextX(Canvas canvas);
    public void drawTextY(Canvas canvas);
    public void drawButton(Canvas canvas);
}
