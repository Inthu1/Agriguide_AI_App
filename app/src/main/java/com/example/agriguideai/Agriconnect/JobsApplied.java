package com.example.agriguideai.Agriconnect;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agriguideai.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class JobsApplied extends AppCompatActivity {
    TextView textName, textPhoneNo, textJob, textLocation, textPlantName;
    RecyclerView rView;
    JobAdapter1 mainAdapter;
    //Context conext;
    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);

//        Context context = this;
//        View Card_item = LayoutInflater.from(context).inflate(R.layout.job_card_item, null, false);
////        TextView textV = cardViewLayout.findViewById(R.id.textView);
//        Button accept = Card_item.findViewById(R.id.button);
//        accept.setVisibility(View.INVISIBLE);
//        //accept.setVisible(false);

        rView = (RecyclerView) findViewById(R.id.recyclerView);
        rView.setLayoutManager(new LinearLayoutManager(this));

        String phoneNo = LoginActivity.activeUserName;

//        FirebaseRecyclerOptions<HelperClass> options =
//                new FirebaseRecyclerOptions.Builder<HelperClass>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("acceptedworker")
//                                .orderByChild("phoneno").equalTo(phoneNo), HelperClass.class)
//                        .build();
        FirebaseRecyclerOptions<HelperClass> options =
                new FirebaseRecyclerOptions.Builder<HelperClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("acceptedworkers"), HelperClass.class)
                        .build();

        //Toast.makeText(JobsApplied.this, phoneNo, Toast.LENGTH_SHORT).show();
        mainAdapter = new JobAdapter1(options, this);
        rView.setAdapter(mainAdapter);
    }
    @Override
    protected void onStart(){
        super.onStart();
        mainAdapter.startListening();
    }
}
