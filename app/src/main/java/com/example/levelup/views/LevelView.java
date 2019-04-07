package com.example.levelup.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.levelup.displayObjects.balls.Ball;
import com.example.levelup.displayObjects.balls.BallController;
import com.example.levelup.displayObjects.Dimensions;
import com.example.levelup.displayObjects.LevelType;
import com.example.levelup.displayObjects.levels.CircleLevel;
import com.example.levelup.displayObjects.levels.HorizontalLevel;
import com.example.levelup.displayObjects.levels.Levels;
import com.example.levelup.displayObjects.levels.VerticalLevel;

import java.util.HashMap;
import java.util.logging.Level;

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
    boolean locked = false;
    BallController lockedBallController;

    public LevelView(Context c, Dimensions d) {
        super(c);
        context = c;
        dimensions = d;
        mOurHolder = getHolder();
        initObjects();
    }

    private void initObjects(){
        initBallControllers();
        initLevels();
    }

    private void initBallControllers(){
        ballController = new BallController(dimensions);
        ballController.setBallColor(Color.GREEN);
        lockedBallController = new BallController(dimensions);
        lockedBallController.setBallColor(Color.RED);
    }

    private void initLevels(){
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
        if(locked){
            lockedBallController.drawBalls(canvas);
        }
        ballController.drawBalls(canvas);
    }

    private void drawLevelShapes(Canvas canvas) {
        for(Levels level: levels.values()){
            level.drawSelf(canvas);
        }
    }

    private int adjustLengthByBallRadius(int length){
        return length - 2*ballController.getBallRadius();
    }

    private int getRectangleX(Levels level, double x){
        int adjustedWidth = adjustLengthByBallRadius(level.getDimensions().getWidth());
        float levelStartX =  level.getShape().left + ballController.getBallRadius();
        int relativeX = (int)(levelStartX + adjustedWidth * x);
        return relativeX;
    }

    private int getRectangleY(Levels level, double y){
        int adjustedHeight = adjustLengthByBallRadius(level.getDimensions().getHeight());
        float levelStartY = level.getShape().bottom - ballController.getBallRadius();
        int relativeY = (int)(levelStartY - adjustedHeight * y);
        return relativeY;
    }


    private int getAdjustedPointHelper(double distanceRatio, int center, int current){
        return (int) ((1 - distanceRatio)*center + distanceRatio* current);
    }

    private Point getAdjustedPoint(double distance, Point point){
        Point center = levels.get(LevelType.CIRCLE).getCenter();
        int adjustedRadius = adjustLengthByBallRadius(levels.get(LevelType.CIRCLE).getDimensions().getWidth())/2;
        double distanceRatio = adjustedRadius/distance;
        int yCoord = getAdjustedPointHelper(distanceRatio, center.y, point.y);
        int xCoord = getAdjustedPointHelper(distanceRatio, center.x, point.x);
        Log.e("FFF", String.valueOf(xCoord ));
        return new Point(xCoord, yCoord);
    }

    // This is not quite working
    private Point adjustRelativeCoordinatesCircle(Point point){
        int adjustedRadius = adjustLengthByBallRadius(levels.get(LevelType.CIRCLE).getDimensions().getWidth())/2;
        //int adjustedRadius = levels.get(LevelType.CIRCLE).getDimensions().getHeight()/2;
        double distanceFromCenter = getDistanceFromCenter(point);
        if(distanceFromCenter > adjustedRadius){
            return getAdjustedPoint(distanceFromCenter, point);
        }
        return point;
    }

    private double getDistanceFromCenter(Point point){
        Point center = levels.get(LevelType.CIRCLE).getCenter();
        double distance = Math.sqrt(Math.pow(center.x - point.x, 2) + Math.pow(center.y - point.y, 2));
        return distance;
    }

    public void setLockedBalls(double x, double y){
        lockedBallController.setPositions(getBallCoordinates(x,y));
    }

    private Point getRelativeCoordinates(LevelType type, double x, double y){
        Levels level = levels.get(type);
        Point relativeCoordinates = new Point(getRectangleX(level, x), getRectangleY(level, y));
        if(type == LevelType.CIRCLE){
            relativeCoordinates = adjustRelativeCoordinatesCircle(relativeCoordinates);
        }
        return relativeCoordinates;
    }

    private HashMap<LevelType, Point> getBallCoordinates(double x, double y){
        HashMap<LevelType, Point> coordinates = new HashMap<LevelType, Point>();
        for (LevelType type: LevelType.values()){
            coordinates.put(type, getRelativeCoordinates(type, x, y));
        }
        return coordinates;
    }

    public void update(double x, double y) {
        ballController.setPositions(getBallCoordinates(x,y));
    }

    public void flipLock() {
        locked  = !locked;
    }
}
