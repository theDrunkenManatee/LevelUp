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

    // In every event handler in the top level do a try-catch(exception)

    private static final int textSize = 40, textColor = Color.WHITE;

    private Row labelRow, activeRow, lockedRow;
    private Context context;
    private Dimensions screenDimensions;
    private Paint textPaint;
    private Canvas mCanvas;
    private boolean locked = false;

    public BottomView(Context c, Dimensions d) {
        super(c);
        context = c;
        screenDimensions = d;
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
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
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
        }
        else{
            lockedRow.setButton(getResources().getDrawable(R.drawable.ic_lock_open_black_24dp, null));
        }
    }

    public void flipLock(){
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
        labelRow = rowText(27 * screenDimensions.getHeight() / 40, "X", "Y");
        activeRow = rowText(15 * screenDimensions.getHeight() / 20, "0.0", "0.0");
        lockedRow = rowText(17 * screenDimensions.getHeight() / 20, "", "");
    }

    private Row rowText(int verticalPlacement, String xText, String yText){
        Row row = new Row(context, screenDimensions.getWidth(), verticalPlacement);
        row.setText_Paint(textPaint);
        row.setText_X(xText);
        row.setText_Y(yText);
        return row;
    }

    public boolean isCalibrateButtonTouched(float x, float y){
        return isButtonTouched(activeRow, x, y);
    }

    public boolean isLockButtonTouched(float x, float y){
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
