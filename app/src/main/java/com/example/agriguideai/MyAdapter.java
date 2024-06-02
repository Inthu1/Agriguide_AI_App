package com.example.agriguideai;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MyAdapter extends FirebaseRecyclerAdapter<Database, MyAdapter.myViewHolder> {
    private Context context;
    public static String cropSoilPrep = "";
    public static String cropIrrigation = "";
    public static String cropFertilization = "";
    public static String cropHarvesting = "";
    public static String cropSeeding = "";
    public MyAdapter(@NonNull FirebaseRecyclerOptions<Database> options, @NonNull Context context){
        super(options);
        this.context = context;
    }


    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Database model){
        holder.name.setText(model.getName());
        new ImageLoadTask(model.getImg(), holder.img).execute();

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context != null) {
                    Intent intent = new Intent(context, Steps.class);
                    context.startActivity(intent);
                    //cropName = (String) holder.name.getText();
                    cropSeeding = model.getSowing();
                    cropSoilPrep = model.getSoilprep();
                    cropHarvesting = model.getHarvesting();
                    cropIrrigation = model.getIrrigation();
                    cropFertilization = model.getFertilization();
                }
            }
        });
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context != null) {
                    Intent intent = new Intent(context, Steps.class);
                    context.startActivity(intent);
                    cropSeeding = model.getSowing();
                    cropSoilPrep = model.getSoilprep();
                    cropHarvesting = model.getHarvesting();
                    cropIrrigation = model.getIrrigation();
                    cropFertilization = model.getFertilization();
                }
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new myViewHolder(view);
    }

    static class myViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView name;
        CardView cardView;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.imageView);
            name = (TextView) itemView.findViewById(R.id.nameText);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }
    }
}