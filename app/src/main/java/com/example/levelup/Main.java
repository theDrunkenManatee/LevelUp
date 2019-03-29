package com.example.levelup;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.levelup.dataParser.DataParser;
import com.example.levelup.dataParser.Vector3;

public class Main extends AppCompatActivity {

    MainView levelView;
    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get a Display object to access screen details
        Display display = getWindowManager().getDefaultDisplay();
        // Load the resolution into a Point object
        Point size = new Point();
        display.getSize(size);
        // Initialize pongView and set it as the view
        levelView = new MainView(this, size.x, size.y);
        setContentView(levelView);
        setUpDataParsing();
    }

    private void setUpDataParsing() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                handleSensorChange(sensorEvent);
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        }, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void handleSensorChange(SensorEvent sensorEvent) {
        Vector3 rawVector = new Vector3(sensorEvent.values[0],sensorEvent.values[1],sensorEvent.values[2]);
        Log.e("TiltCheck", "VectorSum = " + rawVector.getVectorSum());
        levelView.handleVector(rawVector);

    }

    @Override
    protected void onResume() {
        super.onResume();
        levelView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        levelView.pause();
    }
}