package com.example.levelup.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceView;

import com.example.levelup.R;
import com.example.levelup.displayObjects.Dimensions;
import com.example.levelup.displayObjects.rows.Row;

public class BottomView extends SurfaceView {

    // In every event handler in the top level do a try-catch(exception)

    Row labelRow, activeRow, lockedRow;
    Context context;
    Dimensions dimensions;
    Paint textPaint;
    Canvas mCanvas;
    boolean locked = false; // Do stuff before you set the variable

    public BottomView(Context c, Dimensions d) {
        super(c);
        context = c;
        dimensions = d;
        setupPaint();
        setupTextRows();
        setButtons();
    }

    public void drawRows(Canvas canvas){
        mCanvas = canvas;
        drawCoordinates();
        drawButtons();
    }

    private void setupPaint(){
        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(40);
    }

    private void drawCoordinates(){
        labelRow.drawCoordinates(mCanvas);
        activeRow.drawCoordinates(mCanvas);
        lockedRow.drawCoordinates(mCanvas);
    }

    private void setButtons(){
        setLockedButton();
        activeRow.setButton(getResources().getDrawable(R.drawable.focus, null));
    }

    public void setLockedButton(){
        if(locked){
            lockedRow.setButton(getResources().getDrawable(R.drawable.ic_action_name, null));
            Log.e("DDD", String.valueOf(locked));
        }
        else{
            lockedRow.setButton(getResources().getDrawable(R.drawable.ic_lock_open_black_24dp, null));
        }
    }

    public void flipLock(){
        Log.e("DDD", "fliped");
        locked = !locked;
    }

    public void setLockedText(String x, String y) {
        if (locked) {
            lockedRow.setText_Y(y);
            lockedRow.setText_X(x);
        } else {
            lockedRow.setText_Y("");
            lockedRow.setText_X("");
        }
    }

    private void drawButtons(){
        lockedRow.drawButton(mCanvas);
        activeRow.drawButton(mCanvas);
    }

    private void setupTextRows(){
        labelRow = rowText(27*dimensions.getHeight()/40, "X", "Y");
        activeRow = rowText(15*dimensions.getHeight()/20, "0.0", "0.0");
        lockedRow = rowText(17*dimensions.getHeight()/20, "", "");
    }

    private Row rowText(int verticalPlacement, String xText, String yText){
        Row row = new Row(context, dimensions.getWidth(), verticalPlacement);
        row.setText_Paint(textPaint);
        row.setText_X(xText);
        row.setText_Y(yText);
        return row;
    }

    public boolean isCalibrateButtonTouched(float x, float y){
        return isButtonTouched(activeRow, x, y);
    }

    public boolean isLockButtonTouched(float x, float y){
        Log.e("CCC", String.valueOf(isButtonTouched(lockedRow, x, y)));
        return  isButtonTouched(lockedRow, x, y);
    }

    private boolean isButtonTouched(Row r, float x, float y){
        return (x >= r.getButtonBounds().left && x <= r.getButtonBounds().right
                && y >= r.getButtonBounds().top && y <= r.getButtonBounds().bottom);
    }

    public void update(String x, String y){
        setLockedButton();
        setRowText(activeRow, x, y);
    }

    private void setRowText(Row row, String xText, String yText){
        row.setText_X(xText);
        row.setText_Y(yText);
    }
}
