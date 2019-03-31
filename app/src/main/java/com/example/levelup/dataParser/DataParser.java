package com.example.levelup.dataParser;

public class DataParser {
    private double shownX = 90.5f;
    private double shownY = 90.5f;

    //level val: 0 ----> 1
    private double horizLevel = 0f;
    private double vertLevel = 0f;

    private Vector3 currentVector;
    private VectorParser vectorParser = new VectorParser();

    public DataParser() {
        currentVector = new Vector3(0,0,0);
    }

    public void calibrate() {
        vectorParser.setCalibrationVector(currentVector);
    }

    public void parseAccelData(Vector3 vector) {
        shownX = vector.getX();
        shownY = vector.getY();
        vectorParser.setVectorToParse(vector);
        updateValues();
    }

    private void updateValues() {
        horizLevel = vectorParser.getLevelX();
        vertLevel = vectorParser.getLevelY();
    }

    //Getter Methods
    public double getShownX() {
        return shownX;
    }
    public double getShownY() {
        return shownY;
    }
    public double getHorizLevel() {
        return horizLevel;
    }
    public double getVertLevel() {
        return vertLevel;
    }
}