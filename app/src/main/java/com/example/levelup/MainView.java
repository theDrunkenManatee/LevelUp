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
import android.widget.Toast;

import com.example.levelup.dataParser.DataParser;
import com.example.levelup.dataParser.Vector3;
import com.example.levelup.displayObjects.Dimensions;
import com.example.levelup.views.BottomView;
import com.example.levelup.views.LevelView;

// http://androidgameprogramming.com/programming-a-pong-game-part-4/

class MainView extends SurfaceView implements Runnable, View.OnTouchListener {

    private final static int backgroundColor = Color.parseColor("#333232");
    private Thread mGameThread = null;
    private SurfaceHolder mOurHolder;
    private volatile boolean mPlaying;
    private float xTouch, yTouch;
    private boolean mPaused = false;
    private Canvas mCanvas;
    private Paint mPaint;
    private Dimensions screenDimensions;
    private LevelView levelView;
    private BottomView bottomView;
    private Context context;
    private DataParser parser;

    public MainView(Context c, Dimensions d) {
        super(c);
        context = c;
        screenDimensions = d;
        this.setOnTouchListener(this);
        initObjects();
    }

    public void initObjects(){
        mOurHolder = getHolder();
        mPaint = new Paint();
        levelView = new LevelView(context, screenDimensions);
        bottomView = new BottomView(context, screenDimensions);
        parser = new DataParser();
    }

    //put name of the exception in here.
    private void catchHelper(Exception e) {
        Toast.makeText(context, "An error has occurred",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        try {
            int maskedAction = event.getActionMasked();
            switch (maskedAction) {
                case MotionEvent.ACTION_DOWN: {
                    xTouch = event.getX();
                    yTouch = event.getY();
                    buttonAction(xTouch, yTouch);
                }
                ;
                case MotionEvent.ACTION_UP: {
                }
                ;
            }
            return true;
        }catch (Exception e){
            catchHelper(e);
            return false;
        }
    }

    private void buttonAction(float x,float y){
        if (bottomView.isLockButtonTouched(x, y)){
            onLockButtonPress();
        }
        else if(bottomView.isCalibrateButtonTouched(x, y)){
            onCalibrateButtonPress();
        }
    }

    public void setBackgroundColor(){
        mCanvas.drawColor(backgroundColor);
    }

    @Override
    public void run() {
        try {
            while (mPlaying) {
                long startFrameTime = System.currentTimeMillis();
                if (!mPaused) {
                    update();
                }
                draw();
            }
        }
        catch (Exception e){
            catchHelper(e);
            throw e;
        }
    }

    private void update() {
        updateLevels();
        updateBottomView();
    }

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
        flipLocks();
        updateLockedText();
        levelView.setLockedBalls(parser.getHorizLevel(), parser.getVertLevel());
        parser.lockLevel();
    }

    private void flipLocks(){
        bottomView.flipLock();
        levelView.flipLock();
    }

    public void handleVector(Vector3 vector) {
        parser.parseAccelData(vector);
    }

    public void pause() {
        mPlaying = false;
        try {
            mGameThread.join();
        } catch (InterruptedException e) {
            catchHelper(e);
        }
    }

    public void resume() {
        mPlaying = true;
        mGameThread = new Thread(this);
        mGameThread.start();
    }

    private String formatNumber(double value){
        return String.format("%5.2f", value);
    }

    private void updateLockedText() {
        String shownX = formatNumber(parser.getShownX());
        String shownY = formatNumber(parser.getShownY());
        bottomView.setLockedText(shownX, shownY);
    }

    private void updateBottomView() {
        String shownX = formatNumber(parser.getShownX());
        String shownY = formatNumber(parser.getShownY());
        bottomView.update(shownX, shownY);
    }
    private void updateLevels() {
        levelView.update(parser.getHorizLevel(), parser.getVertLevel());
    }

}