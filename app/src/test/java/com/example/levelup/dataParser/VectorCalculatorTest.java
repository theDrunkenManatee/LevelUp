package com.example.levelup.dataParser;

import org.junit.Test;

import static org.junit.Assert.*;

public class VectorCalculatorTest {

    private VectorCalculator vectorCalculator = new VectorCalculator();

    @Test
    public void clampToMax() {
        double smallerInt = -1f;
        for(double i = 0.1; i <=6; i++) {
            double clampedValue = vectorCalculator.clampToMax(i, 5);
            System.out.println("i = " + i + ", clampedValue = " + clampedValue + ", smallerInt = " + smallerInt);
            assertTrue(smallerInt<=clampedValue);
            smallerInt = clampedValue;
        }
    }

    @Test
    public void makeDifferenceVector() {
        for(int x = 0; x<10; x++) {
            for(int y = 0; y<10; y++) {
                for(int z = 0; z<10; z++) {
                    Vector3 vector1 = new Vector3(x,y,z);
                    for(int i = 0; i<10; i++) {
                        Vector3 vector2 = new Vector3(x+i,y+i,z+i);
                        Vector3 diffVector = vectorCalculator.makeDifferenceVector(vector1, vector2);
                        System.out.println("vector1:" + vector1.toString() + " vector2:" + vector2.toString() + " diffVector:" + diffVector.toString());
                        assertEquals(-i ,diffVector.getX(), 0);
                        assertEquals(-i ,diffVector.getY(), 0);
                        assertEquals(-i ,diffVector.getZ(), 0);
                    }
                }
            }
        }
    }

    @Test
    public void makeSumVector() {
        for(int x = 0; x<10; x++) {
            for(int y = 0; y<10; y++) {
                for(int z = 0; z<10; z++) {
                    Vector3 vector1 = new Vector3(x,y,z);
                    for(int i = 0; i<10; i++) {
                        Vector3 vector2 = new Vector3(x+i,y+i,z+i);
                        Vector3 sumVector = vectorCalculator.makeSumVector(vector1, vector2);
                        System.out.println("vector1:" + vector1.toString() + " vector2:" + vector2.toString() + " sumVector:" + sumVector.toString());
                        assertEquals(2*x+i ,sumVector.getX(), 0);
                        assertEquals(2*y+i ,sumVector.getY(), 0);
                        assertEquals(2*z+i ,sumVector.getZ(), 0);
                    }
                }
            }
        }
    }

    @Test
    public void makeProductVector() {
        for(int x = 0; x<10; x++) {
            for(int y = 0; y<10; y++) {
                for(int z = 0; z<10; z++) {
                    Vector3 vector1 = new Vector3(x,y,z);
                    for(int i = 0; i<10; i++) {
                        Vector3 vector2 = new Vector3(x+i,y+i,z+i);
                        Vector3 productVector = vectorCalculator.makeProductVector(vector1, vector2);
                        System.out.println("vector1:" + vector1.toString() + " vector2:" + vector2.toString() + " productVector:" + productVector.toString());
                        assertEquals(x*(x+i) ,productVector.getX(), 0);
                        assertEquals(y*(y+i) ,productVector.getY(), 0);
                        assertEquals(z*(z+i) ,productVector.getZ(), 0);
                    }
                }
            }
        }
    }
}