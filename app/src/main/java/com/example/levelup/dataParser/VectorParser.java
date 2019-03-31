package com.example.levelup.dataParser;

public class VectorParser {

    private double maxValueX;
    private double maxValueY;
    private Vector3 vectorToParse = new Vector3(0,0,0);
    private Vector3 calibrationVector = new Vector3(0,0,0);

    public VectorParser() {
        maxValueX = 5;
        maxValueY = 5;
    }

    public VectorParser(double maxX, double maxY) {
        this.maxValueX = maxX;
        this.maxValueY = maxY;
    }

    public double getLevelX() {
        return Math.max(Math.min(vectorToParse.getX()/(2*maxValueX)+.5, 1),0);
    }

    public double getLevelY() {
        return Math.max(Math.min(vectorToParse.getY()/(2*maxValueY)+.5, 1),0);
    }

    public void setVectorToParse(Vector3 newVector) {
        this.vectorToParse = newVector;
    }

    public void setCalibrationVector(Vector3 calibrationVector) {
        this.calibrationVector = calibrationVector;
    }

}
