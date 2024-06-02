package com.example.agriguideai.Agriconnect;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agriguideai.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class JobAdapter extends FirebaseRecyclerAdapter<HelperClass, JobAdapter.myViewHolder> {
    String name, phoneno, plant_name, location, job_type;
    FirebaseDatabase database;
    DatabaseReference reference;

    public JobAdapter(@NonNull FirebaseRecyclerOptions<HelperClass> options, @NonNull Context context){
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

        holder.accept_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //holder.accept_button.setText("Job Accepted");
                //holder.textLast.setText(name+" "+phoneno+" "+plant_name+" "+location+" "+job_type);

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("acceptedworkers");

                name = model.getFname();
                phoneno = model.getPhoneno();
                plant_name = model.getPlant_name();
                location = model.getLocation();
                job_type = model.getJob_type();

                try {
                    HelperClass helperClass = new HelperClass(name, phoneno, plant_name, job_type, location);
                    reference.child(phoneno).setValue(helperClass);
                }
                catch (Exception  e){
                    Toast.makeText(v.getContext(), "You have accepted a job post successfully!", Toast.LENGTH_SHORT).show();
                }
                if(plant_name.length()==0 || job_type.length()==0 || location.length()==0){
                    Toast.makeText(v.getContext(), "Please fill all the details!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(v.getContext(), "You have accepted a job post successfully!", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(v.getContext(), MainActivity.class);
//                    startActivity(intent);
                }

                //Toast.makeText(JobAdapter.this, "Wrong Username or Password!", Toast.LENGTH_SHORT).show();
//                if (context != null) {
//                    Intent intent = new Intent(context, Steps.class);
//                    context.startActivity(intent);
//                    //cropName = (String) holder.name.getText();
//                    name = model.getName();
//                    phoneno = model.getPhoneno();
//                    plant_name = model.getPlant_name();
//                    location = model.getLocation();
//                    job_type = model.getJob_type();
//                }
            }
        });
//        holder.name.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (context != null) {
//                    Intent intent = new Intent(context, Steps.class);
//                    context.startActivity(intent);
//                    cropSeeding = model.getSowing();
//                    cropSoilPrep = model.getSoilprep();
//                    cropHarvesting = model.getHarvesting();
//                    cropIrrigation = model.getIrrigation();
//                    cropFertilization = model.getFertilization();
//                }
//            }
//        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_card_item, parent, false);
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


