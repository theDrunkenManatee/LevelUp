package com.example.levelup;

import android.graphics.Point;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main extends AppCompatActivity {

    ImageButton lock, calibrate;
    TextView x, y, x_locked, y_locked;
    LevelView levelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get a Display object to access screen details
        Display display = getWindowManager().getDefaultDisplay();

        // Load the resolution into a Point object
        Point size = new Point();
        display.getSize(size);

        // Initialize pongView and set it as the view
        levelView = new LevelView(this, size.x, size.y);
        setContentView(levelView);

        //setContentView(R.layout.activity_main);
        setupScreenComponents();
        setupButtonListeners();

    }

    // This method executes when the player starts the game
    @Override
    protected void onResume() {
        super.onResume();

        // Tell the pongView resume method to execute
        levelView.resume();
    }

    // This method executes when the player quits the game
    @Override
    protected void onPause() {
        super.onPause();

        // Tell the pongView pause method to execute
        levelView.pause();
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
        //setupLockListener();
        //setupCalibrateListener();
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
