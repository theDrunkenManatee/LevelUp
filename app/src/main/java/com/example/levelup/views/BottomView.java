package com.example.levelup.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

import com.example.levelup.R;
import com.example.levelup.displayObjects.rows.Row;

public class BottomView extends SurfaceView {

    Row labelRow, activeRow, lockedRow;
    Context context;
    int mScreenX, mScreenY;
    Paint textPaint;
    Canvas mCanvas;

    public BottomView(Context c,  int screenX, int screenY) {
        super(c);
        context = c;
        mScreenX = screenX;
        mScreenY = screenY;
        setupPaint();
        setupTextRows();
    }

    public void drawRows(Canvas canvas){
        mCanvas = canvas;
        drawCoordinates();
        drawButtons();
    }

    public void setupPaint(){
        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(40);
    }

    public void drawCoordinates(){
        labelRow.drawCoordinates(mCanvas);
        activeRow.drawCoordinates(mCanvas);
        lockedRow.drawCoordinates(mCanvas);
    }

    public void drawButtons(){
        lockedRow.drawButton(mCanvas, getResources().getDrawable(R.drawable.ic_action_name, null));
        activeRow.drawButton(mCanvas, getResources().getDrawable(R.drawable.focus, null));
    }

    public void setupTextRows(){
        labelRow = rowText(27*mScreenY/40, "X", "Y");
        activeRow = rowText(15*mScreenY/20, "0.0", "0.0");
        lockedRow = rowText(17*mScreenY/20, "", "");
    }

    public Row rowText(int verticalPlacement, String xText, String yText){
        Row row = new Row(context, mScreenX, verticalPlacement);
        row.setText_Paint(textPaint);
        row.setText_X(xText);
        row.setText_Y(yText);
        return row;
    }

    public void update(double x, double y){
        setRowText(activeRow, String.valueOf(x), String.valueOf(y));
    }

    public void setRowText(Row row, String xText, String yText){
        row.setText_X(xText);
        row.setText_Y(yText);
    }
}
