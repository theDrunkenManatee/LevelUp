package com.example.levelup;

import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

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
        setUpDataParser();
    }

    private void setUpDataParser() {

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