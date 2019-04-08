package com.example.levelup.dataParser;

public class VectorParser {

    private  Vector3 vectorToParse = new Vector3(0,0,0);
    private Vector3 calibrationVector = new Vector3(0,0,0);
    private Vector3 calibratedVector = new Vector3(0,0,0);
    private Vector3 lockVector =  new Vector3(0,0,0);

    VectorCalculator vectorCalculator = new VectorCalculator();

    public VectorParser() {

    }


    public void setVectorToParse(Vector3 newVector) {
        this.vectorToParse = newVector;
    }
    public Vector3 getVectorToParse() {
        return vectorToParse;
    }
    public void setCalibratedVector(Vector3 calibratedVector) {
        this.calibratedVector = calibratedVector;
    }
    public Vector3 getCalibratedVector() {
        return vectorCalculator.makeDifferenceVector(getVectorToParse(), getCalibrationVector());
    }
    public void setCalibrationVector(Vector3 calibrationVector) {
        this.calibrationVector = calibrationVector;
    }
    public Vector3 getCalibrationVector() {
        return calibrationVector;
    }
    public void setLockVector(Vector3 lockVector) {
        this.lockVector = lockVector;
    }
    public Vector3 getLockVector() {
        return lockVector;
    }
}
