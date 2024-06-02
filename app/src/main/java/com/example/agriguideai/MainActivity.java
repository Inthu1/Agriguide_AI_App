package com.example.agriguideai;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import com.example.agriguideai.FertilizerRecommend.fertilizeradvisor;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager);
        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.slider1);

        slideradapter  slideradapter = new slideradapter(imageList);
        viewPager.setAdapter(slideradapter);

        CardView cropadvisor=findViewById(R.id.cropcard);
        cropadvisor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, com.example.agriguideai.CropRecommend.cropadvisor.class);
                startActivity(myintent);
            }
        });
        CardView cultivation=findViewById(R.id.culti);
        cultivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, Cultivation.class);
                startActivity(myintent);
            }
        });
        CardView Fertilizer=findViewById(R.id.ferti);
        Fertilizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, fertilizeradvisor.class);
                startActivity(myintent);
            }
        });
        CardView agriconnect=findViewById(R.id.agricon);
        agriconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, com.example.agriguideai.Agriconnect.SignUpActivity.class);
                startActivity(myintent);
            }
        });

        CardView Leaf=findViewById(R.id.leafdis);
        Leaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, com.example.agriguideai.DiseasePrediction.diseaseprediction.class);
                startActivity(myintent);
            }
        });
    }
}