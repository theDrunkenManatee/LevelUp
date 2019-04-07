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

    private double convertToNegPosOneScale(double z){
        //Log.e("FFF", String.valueOf(z*4 - 1));
        return z*2 - 1;
    }

    private double convertToZeroOneScale(double z){
        return (z + 1)/2;
    }

    private double getCircleCoordinate(double x, double y){
        double coord =  convertToZeroOneScale(convertToNegPosOneScale(x) * Math.sqrt(1 - (Math.pow(convertToNegPosOneScale(y), 2.0) / 2)));
        Log.e("FFF", String.valueOf(coord));
        return coord;
    }

    private int getCircleX(double x, double y){
        float levelStartX =  levels.get(LevelType.CIRCLE).getShape().left + ballController.getBallRadius();
        return (int)(levelStartX + getCircleCoordinate(x, y) * x);
    }

    private int getCircleY(double x, double y){
        float levelStartX =  levels.get(LevelType.CIRCLE).getShape().bottom + ballController.getBallRadius();
        return (int)(levelStartX + getCircleCoordinate(y, x) * y);
    }

    // This is not quite working
    private Point adjustRelativeCoordinatesCircle(Point point){
        int adjustedRadius = levels.get(LevelType.CIRCLE).getDimensions().getHeight()/2 - ballController.getBallRadius();
        double distanceFromCenter = getDistanceFromCenter(point);
        if(distanceFromCenter > adjustedRadius){
            getAdjustedPoint(adjustedRadius - distanceFromCenter, point);
        }
        return point;
    }

    private void getAdjustedPoint(double distance, Point point){

    }

    private double getDistanceFromCenter(Point point){
        Point center = levels.get(LevelType.CIRCLE).getCenter();
        return Math.sqrt((center.x - point.x)^2 + (center.y - point.y)^2);
    }

    public void setLockedBalls(double x, double y){
        lockedBallController.setPositions(getBallCoordinates(x,y));
    }

    private Point getRelativeCoordinates(LevelType type, double x, double y){
        Levels level = levels.get(type);
        Point relativeCoordinates = new Point(getRectangleX(level, x), getRectangleY(level, y));
        if(type == LevelType.CIRCLE){
            relativeCoordinates = new Point(getCircleX(x, y), getCircleY(x, y));
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
