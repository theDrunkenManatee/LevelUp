package com.example.levelup.dataParser;

public class VectorParser {

    private double maxValueX;
    private double maxValueY;
    private Vector3 vectorToParse = new Vector3(0,0,0);
    private Vector3 calibrationVector = new Vector3(0,0,0);

    public VectorParser() {
        maxValueX = 8;
        maxValueY = 8;
    }

    public VectorParser(double maxX, double maxY) {
        this.maxValueX = maxX;
        this.maxValueY = maxY;
    }

    public double getLevelX() {
        return clampTo(vectorToParse.getX()/maxValueX, 1);
    }

    public double getLevelY() {
        return clampTo(vectorToParse.getY()/maxValueY, 1f);
    }

    public void setVectorToParse(Vector3 newVector) {
        this.vectorToParse = newVector;
    }

    public void setCalibrationVector(Vector3 calibrationVector) {
        this.calibrationVector = calibrationVector;
    }

    private double clampTo(double toClamp, double minMax) {
        return Math.min(Math.abs(toClamp), minMax) * (Math.abs(toClamp)/toClamp);
    }
}
