package com.example.levelup;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.levelup.dataParser.DataParser;
import com.example.levelup.dataParser.Vector3;
import com.example.levelup.displayObjects.Ball;
import com.example.levelup.displayObjects.Dimensions;
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
        //mBall.update(mFPS);
        updateBottomView();
        updateLevels();
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

    //update() helper functions
    private void updateBottomView() {
        String showX = String.format("%5.2f", parser.getShownX());
        String showY = String.format("%5.2f", parser.getShownY());
        bottomView.update(showX, showY);
    }
    private void updateLevels() {
        //TODO
        //levels.update(parser.getHorizLevel(), parser.getVertLevel())
    }
}