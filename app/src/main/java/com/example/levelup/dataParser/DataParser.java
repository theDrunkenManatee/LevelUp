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

    public DataParser() {
        currentVector = new Vector3(0,0,0);
    }


    public void parseAccelData(Vector3 vector) {
        updateValues();
        shownX = vector.getX();
        shownY = vector.getY();

        vectorParser.setVectorToParse(vector);
        currentVector = vector;
    }

    public void calibrate() {
        vectorParser.setCalibrationVector(currentVector);
    }

    public void lockLevel() { vectorParser.setLockVector(currentVector); }

    private void updateValues() {
        horizLevel = vectorCalculator.clampToMax(vectorParser.getVectorToParse().getX(), maxX);
        vertLevel = vectorCalculator.clampToMax(vectorParser.getVectorToParse().getY(), maxY);
        lockedX = vectorParser.getLockVector().getX();
        lockedY = vectorParser.getLockVector().getY();
    }

    private double compressToMax(double toCompress, double maxValue) {
        return Math.max(Math.min(toCompress/(2*maxValue)+.5, 1),0);
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