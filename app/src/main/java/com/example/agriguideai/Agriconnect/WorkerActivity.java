package com.example.agriguideai.Agriconnect;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agriguideai.R;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class WorkerActivity extends AppCompatActivity {
    TextView textName, textPhoneNo, textJob, textLocation, textPlantName;
    RecyclerView rView;
    JobAdapter mainAdapter;
    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);
        rView = (RecyclerView) findViewById(R.id.recyclerView);
        rView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<HelperClass> options =
                new FirebaseRecyclerOptions.Builder<HelperClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("jobs"), HelperClass.class)
                        .build();
        mainAdapter = new JobAdapter(options, this);
        rView.setAdapter(mainAdapter);
    }
    @Override
    protected void onStart(){
        super.onStart();
        mainAdapter.startListening();
    }
}