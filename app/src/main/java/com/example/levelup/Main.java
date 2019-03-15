package com.example.levelup;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main extends AppCompatActivity {

    ImageButton lock, calibrate;
    TextView x, y, x_locked, y_locked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupScreenComponents();
        setupButtonListeners();
    }

    private void setupScreenComponents() {
        lock = findViewById(R.id.lockButton);
        calibrate = findViewById(R.id.calibrateButton);
        x = findViewById(R.id.realX);
        y = findViewById(R.id.realY);
        x_locked = findViewById(R.id.lockX);
        y_locked = findViewById(R.id.lockY);
    }

    private void setupButtonListeners() {
        setupLockListener();
        setupCalibrateListener();
    }

    private void setupCalibrateListener() {
        calibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setupLockListener() {
        lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }






}
