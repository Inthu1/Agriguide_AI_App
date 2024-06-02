package com.example.agriguideai.FertilizerRecommend;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agriguideai.CropRecommend.cropadvisor;
import com.example.agriguideai.R;

public class fertilizeradvisor extends AppCompatActivity {

    private EditText nitrovalue;
    private EditText potasvalue;
    private EditText phosvalue;
    private TextView cropname;
    private TextView label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fertilizeradvisor);
        Button predict=findViewById(R.id.predictBtn);

        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nitrovalue = findViewById(R.id.nitrovalue);
                potasvalue = findViewById(R.id.potasvalue);
                phosvalue = findViewById(R.id.phosvalue);
                cropname=findViewById(R.id.cropname);
                label = findViewById(R.id.label);

                String Nitrogen = nitrovalue.getText().toString();
                String Potassium = phosvalue.getText().toString();
                String Phosphorous = potasvalue.getText().toString();
                cropname.setText(cropadvisor.responseValue);

                new FertilizerRecommendation("http://192.168.137.1:3000/recommend_fertilizer?"+ "Nitrogen=" + Nitrogen + "&" + "Potassium=" + Potassium + "&" + "Phosphorous=" + Phosphorous, new fertilizerrecommendationresponse() {

                    @Override
                    public void onResponse(String response) {
                        Log.i("Response Body", response);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run(){
                                // Toast.makeText(cropadvisor.this, response, Toast.LENGTH_SHORT).show();
                                label.setText("Recommend Fertilizer : " + response);
                            }
                        });
                    }
                }).execute();
            }
        });
    }
}

