package com.example.levelup;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.levelup.dataParser.DataParser;
import com.example.levelup.dataParser.Vector3;
import com.example.levelup.displayObjects.Ball;
import com.example.levelup.views.BottomView;
import com.example.levelup.views.LevelView;

// http://androidgameprogramming.com/programming-a-pong-game-part-4/

class MainView extends SurfaceView implements Runnable{

    // This is our thread
    Thread mGameThread = null;
    // We need a SurfaceHolder object
    // We will see it in action in the draw method soon.
    SurfaceHolder mOurHolder;
    // A boolean which we will set and unset
    // when the game is running- or not
    // It is volatile because it is accessed from inside and outside the thread
    volatile boolean mPlaying;
    boolean mPaused = false;
    Canvas mCanvas;
    Paint mPaint;
    // This variable tracks the game frame rate
    long mFPS;
    // The size of the screen in pixels
    int mScreenX, mScreenY;
    Ball mBall;
    LevelView levelView;
    BottomView bottomView;

    DataParser parser;

    public MainView(Context context, int x, int y) {
        super(context);
        mScreenX = x;
        mScreenY = y;
        mOurHolder = getHolder();
        mPaint = new Paint();
        levelView = new LevelView(context, x, y);
        bottomView = new BottomView(context, mScreenX, mScreenY);
        parser = new DataParser();
        initObjects();
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

    private void update() {
        //mBall.update(mFPS);
        updateBottomView();
    }
    private void updateBottomView() {
        String showX = String.format("%5.2f", parser.getShownX());
        String showY = String.format("%5.2f", parser.getShownX());
        bottomView.update(showX,showY);
    }
    // Draw the newly updated scene
    public void draw() {
        if (mOurHolder.getSurface().isValid()) {
            mCanvas = mOurHolder.lockCanvas();
            levelView.makeBackground(mCanvas);
            bottomView.drawRows(mCanvas);
            mOurHolder.unlockCanvasAndPost(mCanvas);
        }
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
}