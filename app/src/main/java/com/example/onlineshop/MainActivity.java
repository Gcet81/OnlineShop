package com.example.onlineshop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 5000; // Duration in milliseconds (5000ms = 5s)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the TextView by finding it by its ID
        TextView welcomeTextView = findViewById(R.id.welcomeText);
        welcomeTextView.setText("Welcome to Shoe Shop!");

        // Using a Handler to delay the transition to LoginActivity
        new Handler().postDelayed(() -> {

            Intent mainIntent = new Intent(MainActivity.this, LoginActivity.class);
            MainActivity.this.startActivity(mainIntent);
            MainActivity.this.finish();
        }, SPLASH_DISPLAY_LENGTH);
    }
}
