package com.example.agriguideai;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Cultivation extends AppCompatActivity {
    RecyclerView rView;
    MyAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultivation);

        rView = (RecyclerView) findViewById(R.id.recyclerView);
        rView.setLayoutManager(new GridLayoutManager(this, 3));

        FirebaseRecyclerOptions<Database> options =
                new FirebaseRecyclerOptions.Builder<Database>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("crops"), Database.class)
                        .build();

        mainAdapter = new MyAdapter(options, this);
        rView.setAdapter(mainAdapter);
    }

    @Override
    protected void onStart(){
        super.onStart();
        mainAdapter.startListening();
    }

}
