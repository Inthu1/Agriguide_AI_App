package com.example.agriguideai.Agriconnect;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agriguideai.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class JobAdapter1 extends FirebaseRecyclerAdapter<HelperClass, JobAdapter1.myViewHolder> {
    String name, phoneno, plant_name, location, job_type;
    FirebaseDatabase database;
    DatabaseReference reference;

    public JobAdapter1(@NonNull FirebaseRecyclerOptions<HelperClass> options, @NonNull Context context){
        super(options);
    }
    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull HelperClass model){
        holder.textName.setText("Name: " + model.getFname());
        holder.textPhoneNo.setText("Phone Number: "+model.getPhoneno());
        holder.textPlantName.setText("Plant Name: "+model.getPlant_name());
        holder.textLocation.setText("Location: "+model.getLocation());
        holder.textJob.setText("Job Category: "+model.getJob_type());
    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_card_item1, parent, false);
        return new myViewHolder(view);
    }
    static class myViewHolder extends RecyclerView.ViewHolder{
        public android.widget.Toast Toast;
        TextView textName, textPhoneNo, textJob, textLocation, textPlantName;
        Button accept_button;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            accept_button = itemView.findViewById(R.id.accept_button);
            textName = itemView.findViewById(R.id.textName);
            textPhoneNo = itemView.findViewById(R.id.textPhoneNo);
            textJob = itemView.findViewById(R.id.textJob);
            textLocation = itemView.findViewById(R.id.textLocation);
            textPlantName = itemView.findViewById(R.id.textPlantName);
            //textLast = itemView.findViewById(R.id.textLast);
        }
    }
}
