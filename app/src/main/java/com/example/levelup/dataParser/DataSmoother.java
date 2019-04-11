package com.example.levelup.dataParser;

import java.util.ArrayDeque;

public class DataSmoother {

    ArrayDeque<Double> valueDeque;
    private static int dequeLength;

    public DataSmoother(int length) {
        dequeLength  = length;
        valueDeque = new ArrayDeque<>();
    }

    public void addData(double data) {
        valueDeque.add(data);
        if(valueDeque.size()>dequeLength) {
            valueDeque.pop();
        }
    }

    public double getAverage() {
        double sum=0;
        for(double i : valueDeque)
            sum += i;
        return sum/valueDeque.size();
    }

}
