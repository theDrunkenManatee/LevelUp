package com.example.levelup.displayObjects.rows;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.SurfaceView;

import com.example.levelup.R;

public class Row extends SurfaceView implements CoordinateRow {

    String textX, textY;
    int verticalPosition, buttonSize;
    float textX_Position, textY_Position;
    Paint textPaint;

    public Row(Context context, int screenX, int verticalPlacement){
        super(context);
        buttonSize = 50;
        verticalPosition = verticalPlacement;
        textX_Position = screenX/4;
        textY_Position = screenX/2;
    }

    @Override
    public void buttonListener() {

    }

    @Override
    public void setText_Paint(Paint paint) {
        textPaint = paint;
    }

    @Override
    public void setText_X(String x) {
        textX = x;
    }

    @Override
    public void setText_Y(String y) {
        textY = y;
    }

    @Override
    public void drawCoordinates(Canvas canvas) {
        drawTextX(canvas);
        drawTextY(canvas);
    }

    @Override
    public void drawTextX(Canvas canvas) {
        canvas.drawText(textX, textX_Position, verticalPosition, textPaint);
    }

    @Override
    public void drawTextY(Canvas canvas) {
        canvas.drawText(textY, textY_Position, verticalPosition, textPaint);
    }

    @Override
    public void drawButton(Canvas canvas, Drawable button) {
        button.setBounds(10, verticalPosition - 3*buttonSize/4,
                10 + buttonSize, verticalPosition + buttonSize/4);
        button.draw(canvas);
    }
}
