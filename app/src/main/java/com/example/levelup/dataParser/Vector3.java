package com.example.levelup.dataParser;

public class Vector3 {
    private double x;
    private double y;
    private double z;

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getZ() {
        return z;
    }

    public double getVectorSum() {
        return x + y + z;
    }
}
