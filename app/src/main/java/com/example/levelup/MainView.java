package com.example.levelup;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.example.levelup.dataParser.DataParser;
import com.example.levelup.dataParser.Vector3;
import com.example.levelup.displayObjects.balls.Ball;
import com.example.levelup.displayObjects.Dimensions;
import com.example.levelup.views.BottomView;
import com.example.levelup.views.LevelView;

// http://androidgameprogramming.com/programming-a-pong-game-part-4/

class MainView extends SurfaceView implements Runnable, View.OnTouchListener {

    // This is our thread
    Thread mGameThread = null;
    // We need a SurfaceHolder object
    // We will see it in action in the draw method soon.
    SurfaceHolder mOurHolder;
    // A boolean which we will set and unset
    // when the game is running- or not
    // It is volatile because it is accessed from inside and outside the thread
    volatile boolean mPlaying;
    float xTouch, yTouch;
    boolean mPaused = false;
    Canvas mCanvas;
    Paint mPaint;
    // This variable tracks the game frame rate
    long mFPS;
    // The size of the screen in pixels
    Dimensions dimensions;
    Ball mBall;
    LevelView levelView;
    BottomView bottomView;
    private Context context;

    DataParser parser;

    public MainView(Context c, Dimensions d) {
        super(c);
        context = c;
        dimensions = d;
        this.setOnTouchListener(this);
        initObjects();
        setupAndRestart();
    }

    public void initObjects(){
        mOurHolder = getHolder();
        mPaint = new Paint();
        levelView = new LevelView(context, dimensions);
        bottomView = new BottomView(context, dimensions);
        parser = new DataParser();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // get masked (not specific to a pointer) action
        int maskedAction = event.getActionMasked();
        switch (maskedAction) {
            case MotionEvent.ACTION_DOWN: {
                xTouch = event.getX();
                yTouch = event.getY();
                Log.e("DDD", "This worked");
                buttonAction(xTouch, yTouch);
            };
            case MotionEvent.ACTION_UP: {
            };
        }
        return true;//false = finished dont loop through. true = loop through
    }

    private void buttonAction(float x,float y){
        if (bottomView.isLockButtonTouched(x, y)){
            Log.e("DDD", "This Also worked");
            onLockButtonPress();
        }
        else if(bottomView.isCalibrateButtonTouched(x, y)){
            onCalibrateButtonPress();
        }
    }

    public void setBackgroundColor(){
        mCanvas.drawColor(Color.parseColor("#333232"));
    }

    public void setupAndRestart(){
        //mBall.reset(mScreenX, mScreenY);
    }

    @Override
    public void run() {
        while (mPlaying) {
            long startFrameTime = System.currentTimeMillis();
            if(!mPaused){
                update();
            }
            draw();
        /*
            Calculate the FPS this frame
            We can then use the result to
            time animations in the update methods.
        */
            long timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame >= 1) {
                mFPS = 1000 / timeThisFrame;
            }
        }
    }

    private void update() {
        updateLevels();
        updateBottomView();
        updateLockedText();
    }

    // Draw the newly updated scene
    private void draw() {
        if (mOurHolder.getSurface().isValid()) {
            mCanvas = mOurHolder.lockCanvas();
            setBackgroundColor();
            levelView.drawLevels(mCanvas);
            bottomView.drawRows(mCanvas);
            mOurHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    private void onCalibrateButtonPress() {
        parser.calibrate();
    }

    private void onLockButtonPress(){
        bottomView.flipLock();
        parser.lockLevel();

    }

    public void handleVector(Vector3 vector) {
        parser.parseAccelData(vector);
    }
    public void pause() {
        mPlaying = false;
        try {
            mGameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }
    }
    public void resume() {
        mPlaying = true;
        mGameThread = new Thread(this);
        mGameThread.start();
    }

    private String getText_X(){
        return String.format("%5.2f", parser.getHorizLevel());
    }

    private String getText_Y(){
        return String.format("%5.2f", parser.getVertLevel());
    }

    //update() helper functions
    private void updateLockedText() {
        String shownX = String.format("%5.2f", parser.getLockedX());
        String shownY = String.format("%5.2f", parser.getLockedX());
        bottomView.setLockedText(shownX, shownY);
    }
    private void updateBottomView() {
        String shownX = String.format("%5.2f", parser.getShownX());
        String shownY = String.format("%5.2f", parser.getShownY());
        bottomView.update(shownX, shownY);
    }
    private void updateLevels() {
        Log.e("BBB", String.valueOf(parser.getVertLevel()));
        levelView.update(parser.getHorizLevel(), parser.getVertLevel());
    }

}