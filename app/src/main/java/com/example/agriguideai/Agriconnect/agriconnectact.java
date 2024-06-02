package com.example.agriguideai.Agriconnect;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agriguideai.R;

public class agriconnectact extends AppCompatActivity {
    TextView textWelcome, textEditProfile;
    Button buttonPostJob, buttonJobPosted, buttonJobApplied;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agriconnectact);
//FarmeRActivity
        textWelcome = findViewById(R.id.textWelcome);
        textEditProfile = findViewById(R.id.textEditProfile);
        buttonPostJob = findViewById(R.id.buttonPostJob);
        buttonJobPosted = findViewById(R.id.buttonJobPosted);
        buttonJobApplied = findViewById(R.id.buttonJobApplied);
        textWelcome.setText("Welcome "+LoginActivity.activeUser+" !");

        textEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(agriconnectact.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });
        // Set "Post Job" button click listener
        buttonPostJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(agriconnectact.this, JobPost.class);
                startActivity(intent);
            }
        });
        buttonJobPosted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(agriconnectact.this, JobsPosted.class);
                startActivity(intent);
            }
        });
        buttonJobApplied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(agriconnectact.this, JobsApplied.class);
                startActivity(intent);
            }
        });
    }
}

