package com.example.levelup.dataParser;

public class DataParser {
    private double shownX = 90.5f;
    private double shownY = 90.5f;
    private double lockedX = 91.5f;
    private double lockedY = 91.5f;
    static private double maxX = 5;
    static private double maxY = 5;

    //level val: 0 ----> 1
    private double horizLevel = 0f;
    private double vertLevel = 0f;

    private Vector3 currentVector;

    private VectorParser vectorParser = new VectorParser();
    private VectorCalculator vectorCalculator = new VectorCalculator();
    private DataSmoother xSmoother = new DataSmoother(10);
    private DataSmoother ySmoother = new DataSmoother(10);

    public DataParser() {
        currentVector = new Vector3(0,0,0);
    }


    public void parseAccelData(Vector3 vector) {
        updateSmoother();
        updateValues();
        vectorParser.setVectorToParse(vector);
        currentVector = vector;
    }

    public void calibrate() {
        vectorParser.setCalibrationVector(currentVector);
    }

    public void resetCalibration() {vectorParser.setCalibrationVector(new Vector3(0,0,0));}

    public void lockLevel() { vectorParser.setLockVector(currentVector); }

    private void updateSmoother() {
        xSmoother.addData(vectorParser.getCalibratedVector().getX());
        ySmoother.addData(vectorParser.getCalibratedVector().getY());
    }

    private void updateValues() {
        horizLevel = vectorCalculator.clampToMax(xSmoother.getAverage(), maxX);
        vertLevel = vectorCalculator.clampToMax(ySmoother.getAverage(), maxY);
        shownX = xSmoother.getAverage();
        shownY = ySmoother.getAverage();
        lockedX = vectorParser.getLockVector().getX();
        lockedY = vectorParser.getLockVector().getY();
    }


    //Getter Methods
    public double getShownX() {
        return shownX;
    }
    public double getShownY() {
        return shownY;
    }
    public double getLockedX() {
        return lockedX;
    }
    public double getLockedY() {
        return lockedY;
    }
    public double getHorizLevel() {
        return horizLevel;
    }
    public double getVertLevel() {
        return vertLevel;
    }
}