package com.example.agriguideai;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

public class Steps extends AppCompatActivity {
    //public static String stage= "";
    MaterialToolbar toolbar, toolbar2, toolbar3, toolbar4, toolbar5;
    //TextView stepText;
    //stepText = toolbar.findViewById(R.id.stage);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

            toolbar = findViewById(R.id.soilprepNavbar);
            toolbar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Steps.this, SoilPrep.class);
                    startActivity(intent);
                }
            });

            toolbar2 = findViewById(R.id.seedingNavbar);
            toolbar2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Steps.this, Seeding.class);
                    startActivity(intent);
                }
            });

            toolbar3 = findViewById(R.id.irrigationNavbar);
            toolbar3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Steps.this, Irrigation.class);
                    startActivity(intent);
                }
            });

            toolbar4 = findViewById(R.id.fertilizationNavbar);
            toolbar4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Steps.this, Fertilization.class);
                    startActivity(intent);
                }
            });

            toolbar5 = findViewById(R.id.harvestingNavbar);
            toolbar5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Steps.this, Harvesting.class);
                    startActivity(intent);
                }
            });
        }

//    public void onBackPressedDispatcher() {
//        //super.onBackPressed();
//        finish(); // Finish the current activity (second activity)
//    }
}