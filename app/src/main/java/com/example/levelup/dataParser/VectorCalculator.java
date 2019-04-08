package com.example.levelup.dataParser;

public class VectorCalculator {

    public double clampToMax(double toCompress, double maxValue) {
        return Math.max(Math.min(toCompress/(2*maxValue)+.5, 1),0);
    }

    public Vector3 makeDifferenceVector(Vector3 vector1, Vector3 vector2) {
        return new Vector3(vector1.getX()-vector2.getX(), vector1.getY()-vector2.getY(), vector1.getZ()-vector2.getZ());
    }

    public Vector3 makeAbsDifferenceVector(Vector3 vector1, Vector3 vector2) {
        return new Vector3(Math.abs(vector1.getX()-vector2.getX()), Math.abs(vector1.getY()-vector2.getY()), Math.abs(vector1.getZ()-vector2.getZ()));
    }

    public Vector3 makeSumVector(Vector3 vector1, Vector3 vector2) {
        return new Vector3(vector1.getX()+vector2.getX(), vector1.getY()+vector2.getY(), vector1.getZ()+vector2.getZ());
    }
    public Vector3 makeProductVector(Vector3 vector1, Vector3 vector2) {
        return new Vector3(vector1.getX()*vector2.getX(), vector1.getY()*vector2.getY(), vector1.getZ()*vector2.getZ());
    }
}
