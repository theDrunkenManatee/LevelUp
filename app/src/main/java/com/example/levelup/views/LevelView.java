package com.example.levelup.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.levelup.displayObjects.Ball;
import com.example.levelup.displayObjects.BallController;
import com.example.levelup.displayObjects.Dimensions;
import com.example.levelup.displayObjects.LevelType;
import com.example.levelup.displayObjects.levels.CircleLevel;
import com.example.levelup.displayObjects.levels.HorizontalLevel;
import com.example.levelup.displayObjects.levels.Levels;
import com.example.levelup.displayObjects.levels.VerticalLevel;
import com.example.levelup.views.BottomView;

import java.util.HashMap;

public class LevelView extends SurfaceView{

    Canvas mCanvas;
    // The size of the screen in pixels
    Dimensions dimensions;
    Ball mBall;
    HashMap<LevelType, Levels> levels;
    SurfaceHolder mOurHolder;
    BottomView bottomView;
    BallController ballController;
    Context context;

    public LevelView(Context c, Dimensions d) {
        super(c);
        context = c;
        dimensions = d;
        mOurHolder = getHolder();
        initObjects();
    }

    public void initObjects(){
        ballController = new BallController(dimensions);
        levels = new HashMap<>();
        levels.put(LevelType.VERTICAL, new VerticalLevel(dimensions));
        levels.put(LevelType.HORIZONTAL, new HorizontalLevel(dimensions));
        levels.put(LevelType.CIRCLE, new CircleLevel(levels.get(LevelType.VERTICAL), levels.get(LevelType.HORIZONTAL)));
    }

    public Paint makePaint(int color){
        Paint paint = new Paint();
        paint.setColor(color);
        return paint;
    }

    public void setLevelPaint(){
        setShapePaint();
        setLinePaint();
    }

    private void setLinePaint() {
        Paint linePaint = makePaint(Color.LTGRAY);
        for(Levels level: levels.values()){
            level.setLinePaint(linePaint);
        }
    }

    private void setShapePaint() {
        Paint shapePaint = makePaint(Color.parseColor("#5D737E"));
        for(Levels level: levels.values()){
            level.setShapePaint(shapePaint);
        }
    }

    public void drawLevels(Canvas canvas){
        setLevelPaint();
        drawLevelShapes(canvas);
        ballController.drawBalls(canvas);
    }

    private void drawLevelShapes(Canvas canvas) {
        for(Levels level: levels.values()){
            level.drawSelf(canvas);
        }
    }

    private int adjustLengthByBallRadius(int length){
        return length * 2*ballController.getBallRadius();
    }

    private Point getRelativeCoordinates(LevelType type, double x, double y){
        Levels level = levels.get(type);
        int adjustedWidth = adjustLengthByBallRadius(level.getDimensions().getWidth());
        int adjustedHeight = adjustLengthByBallRadius(level.getDimensions().getHeight());
        float levelStartX =  level.getShape().left + ballController.getBallRadius();
        float levelStartY = level.getShape().top + ballController.getBallRadius();
        int relativeX = (int)(adjustedWidth * x + levelStartX);
        int relativeY = (int)(adjustedHeight * x + levelStartY);
        return new Point(relativeX, relativeY);
    }


    public void update(double x, double y) {
        HashMap<LevelType, Point> coordinates = new HashMap<LevelType, Point>();
        for (LevelType type: LevelType.values()){
            coordinates.put(type, getRelativeCoordinates(type, x, y));
        }
        ballController.setPositions(coordinates);
    }
}
