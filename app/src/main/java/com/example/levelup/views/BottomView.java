package com.example.levelup.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

import com.example.levelup.R;
import com.example.levelup.displayObjects.Dimensions;
import com.example.levelup.displayObjects.rows.Row;

public class BottomView extends SurfaceView {

    Row labelRow, activeRow, lockedRow;
    Context context;
    Dimensions dimensions;
    Paint textPaint;
    Canvas mCanvas;

    public BottomView(Context c, Dimensions d) {
        super(c);
        context = c;
        dimensions = d;
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
        labelRow = rowText(27*dimensions.getHeight()/40, "X", "Y");
        activeRow = rowText(15*dimensions.getHeight()/20, "0.0", "0.0");
        lockedRow = rowText(17*dimensions.getHeight()/20, "", "");
    }

    public Row rowText(int verticalPlacement, String xText, String yText){
        Row row = new Row(context, dimensions.getWidth(), verticalPlacement);
        row.setText_Paint(textPaint);
        row.setText_X(xText);
        row.setText_Y(yText);
        return row;
    }

    public void update(String x, String y){
        setRowText(activeRow, x, y);
    }

    public void setRowText(Row row, String xText, String yText){
        row.setText_X(xText);
        row.setText_Y(yText);
    }
}
