package com.example.levelup.dataParser;

public class VectorParser {

    private  Vector3 vectorToParse = new Vector3(0,0,0);
    private Vector3 calibrationVector = new Vector3(0,0,0);
    private Vector3 lockVector =  new Vector3(0,0,0);

    VectorCalculator vectorCalculator = new VectorCalculator();

    VectorParser() {

    }


    void setVectorToParse(Vector3 newVector) {
        this.vectorToParse = newVector;
    }
    Vector3 getVectorToParse() {
        return vectorToParse;
    }
    Vector3 getCalibratedVector() {
        return vectorCalculator.makeDifferenceVector(getVectorToParse(), getCalibrationVector());
    }
    void setCalibrationVector(Vector3 calibrationVector) {
        this.calibrationVector = calibrationVector;
    }
    Vector3 getCalibrationVector() {
        return calibrationVector;
    }
    void setLockVector(Vector3 lockVector) {
        this.lockVector = lockVector;
    }
    Vector3 getLockVector() {
        return lockVector;
    }
}