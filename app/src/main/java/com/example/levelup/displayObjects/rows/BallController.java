package com.example.levelup.displayObjects.rows;

import android.graphics.Paint;

import com.example.levelup.displayObjects.Ball;

public class BallController {

    Ball hBall, vBall, cBall;
    //final static Paint shapeColor = new Paint()

    public BallController(int screenX, int screenY){
        setupBalls(screenX, screenY);
    }

    private void setupBalls(int screenX, int screenY) {
        hBall = new Ball(screenX, screenY);
        vBall = new Ball(screenX, screenY);
        cBall = new Ball(screenX, screenY);

    }

}
