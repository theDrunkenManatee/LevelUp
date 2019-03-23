package com.example.levelup;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.levelup.displayObjects.Ball;

// http://androidgameprogramming.com/programming-a-pong-game-part-4/

class LevelView extends SurfaceView implements Runnable{

    // This is our thread
    Thread mGameThread = null;
    // We need a SurfaceHolder object
    // We will see it in action in the draw method soon.
    SurfaceHolder mOurHolder;
    // A boolean which we will set and unset
    // when the game is running- or not
    // It is volatile because it is accessed from inside and outside the thread
    volatile boolean mPlaying;
    boolean mPaused = true;
    Canvas mCanvas;
    Paint mPaint;
    // This variable tracks the game frame rate
    long mFPS;
    // The size of the screen in pixels
    int mScreenX, mScreenY;
    Ball mBall;
    StaticView staticScreen;

    public LevelView(Context context, int x, int y) {
        super(context);
        mScreenX = x;
        mScreenY = y;
        mOurHolder = getHolder();
        mPaint = new Paint();
        initObjects();
        staticScreen = new StaticView(context, x, y);
        setupAndRestart();
    }

    public void initObjects(){
        mBall = new Ball(mScreenX, mScreenY);
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

    public void update() {
        //mBall.update(mFPS);
    }

    // Draw the newly updated scene
    public void draw() {
        if (mOurHolder.getSurface().isValid()) {
            mCanvas = mOurHolder.lockCanvas();
            staticScreen.makeBackground(mCanvas);
            mOurHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    private void drawButtons(){
        mPaint.setColor(Color.parseColor("#333232"));
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
}
