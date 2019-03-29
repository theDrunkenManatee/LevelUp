package com.example.levelup.dataParser;

public class DataParser {
    private double shownX = 90.5f;
    private double shownY = 90.5f;

    //level val: 0 ----> 1
    private double horizLevel = 0f;
    private double vertLevel = 0f;

    public DataParser() {

    }

    //TODO
    public void calibrate() {

    }

    public void parseAccelData(Vector3 vector) {
        shownX = vector.getX();
        shownY = vector.getY();
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