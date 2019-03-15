package com.example.levelup.drawings;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class CircleLevelView extends View implements ValueAnimator.AnimatorUpdateListener, View.OnTouchListener {

    private int mRadius = 0;
    private int finalWidth = 0;
    private int finalHeight = 0;

    public CircleLevelView(Context context) {
        super(context);
        init();
    }

    private void init() {
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        finalWidth = getMeasuredWidth() / 3;
        finalHeight = getMeasuredWidth() / 3;
        mRadius = (finalWidth / 2);
        setMeasuredDimension(finalWidth, finalHeight );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
