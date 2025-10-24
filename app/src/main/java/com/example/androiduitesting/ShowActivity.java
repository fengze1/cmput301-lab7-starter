package com.example.androiduitesting;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {

    private TextView cityNameTextView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        cityNameTextView = findViewById(R.id.textView_city_name);
        backButton = findViewById(R.id.button_back);

        // Get city name from intent
        Intent intent = getIntent();
        String cityName = intent.getStringExtra("CITY_NAME");

        // Display city name
        if (cityName != null) {
            cityNameTextView.setText(cityName);
        }

        // Set up back button
        backButton.setOnClickListener(v -> {
            finish(); // Go back to MainActivity
        });
    }
}
