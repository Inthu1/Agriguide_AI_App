package com.example.agriguideai.Agriconnect;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agriguideai.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
public class JobsPosted extends AppCompatActivity {
        JobAdapter1 mainAdapter;
        RecyclerView rView;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_worker);

            rView = (RecyclerView) findViewById(R.id.recyclerView);
            rView.setLayoutManager(new LinearLayoutManager(this));

            String phoneNo = LoginActivity.activeUserName;

            FirebaseRecyclerOptions<HelperClass> options =
                    new FirebaseRecyclerOptions.Builder<HelperClass>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("jobs")
                                    .orderByChild("phoneno").equalTo(phoneNo), HelperClass.class)
                            .build();

            mainAdapter = new JobAdapter1(options, this);
            rView.setAdapter(mainAdapter);
        }
        @Override
        protected void onStart(){
            super.onStart();
            mainAdapter.startListening();
        }
}