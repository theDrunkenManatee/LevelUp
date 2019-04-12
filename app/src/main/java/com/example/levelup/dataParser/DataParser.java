package com.example.levelup.dataParser;

public class DataParser {
    private double shownX = 90.5f;
    private double shownY = 90.5f;
    private double lockedX = 91.5f;
    private double lockedY = 91.5f;
    static private double maxX = 8;
    static private double maxY = 8;

    //level val: 0 ----> 1
    private double horizLevel = 0f;
    private double vertLevel = 0f;

    private Vector3 currentVector;

    private VectorParser vectorParser = new VectorParser();
    private VectorCalculator vectorCalculator = new VectorCalculator();
    private DataSmoother xSmoother = new DataSmoother(4);
    private DataSmoother ySmoother = new DataSmoother(4);

    public DataParser() {
        currentVector = new Vector3(0,0,0);
    }


    public void parseAccelData(Vector3 vector) {
        updateSmoothers();
        updateValues();
        vectorParser.setVectorToParse(vector);
        currentVector = vector;
    }

    public void calibrate() {
        vectorParser.setCalibrationVector(currentVector);
        clearSmoothers();
    }

    public void resetCalibration() {
        vectorParser.setCalibrationVector(new Vector3(0,0,0));
        clearSmoothers();
    }

    public void lockLevel() { vectorParser.setLockVector(currentVector); }

    private void updateSmoothers() {
        xSmoother.addData(vectorParser.getCalibratedVector().getX());
        ySmoother.addData(vectorParser.getCalibratedVector().getY());
    }

    private void clearSmoothers() {
        xSmoother.clearMemory();
        ySmoother.clearMemory();
    }

    private void updateValues() {
        horizLevel = vectorCalculator.clampToMax(xSmoother.getAverage(), maxX);
        vertLevel = vectorCalculator.clampToMax(ySmoother.getAverage(), maxY);
        //shownX = xSmoother.getAverage();  ALTERNATIVE RENDERING OPTION
        //shownY = ySmoother.getAverage();  ^^

        shownX = 2*horizLevel-1;
        shownY = 2*vertLevel-1;

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