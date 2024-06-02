package com.example.agriguideai.CropRecommend;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agriguideai.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class cropadvisor extends AppCompatActivity {

    private EditText nitrovalue;
    private EditText potasvalue;
    private EditText phosvalue;
    private EditText tempvalue;
    private EditText humivalue;
    private EditText phvalue;
    private EditText rain;
    private TextView label;
    private TextView guide;
    public static String responseValue;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cropadvisor);

        Button predict = findViewById(R.id.predictBtn);

        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nitrovalue = findViewById(R.id.nitrovalue);
                potasvalue = findViewById(R.id.potasvalue);
                phosvalue = findViewById(R.id.phosvalue);
                tempvalue = findViewById(R.id.tempvalue);
                humivalue = findViewById(R.id.humivalue);
                phvalue = findViewById(R.id.phvalue);
                rain = findViewById(R.id.rainvalue);
                label = findViewById(R.id.label);
                guide=findViewById(R.id.guide);

                String N = nitrovalue.getText().toString();
                String P = phosvalue.getText().toString();
                String K = potasvalue.getText().toString();
                String temperature = tempvalue.getText().toString();
                String humidity = humivalue.getText().toString();
                String ph = phvalue.getText().toString();
                String rainfall = rain.getText().toString();

                new CropRecommendation("http://192.168.137.1:3000/recomend_crop?" + "N=" + N + "&" + "P=" + P + "&" + "K=" + K + "&" + "temperature=" + temperature + "&" + "humidity=" + humidity + "&" + "ph=" + ph + "&" + "rainfall=" + rainfall, new croprecommendationresponse() {

                    @Override
                    public void onResponse(String response) {
                        Log.i("Response Body", response);
                        responseValue=response;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Toast.makeText(cropadvisor.this, response, Toast.LENGTH_SHORT).show();

                                label.setText("Recommend Crop : " + response);
                            }
                        });
                    }
                }).execute();
            }
        });

        Button cultivation = findViewById(R.id.cultBtn);
        cultivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (responseValue != null) {
                    Log.i("respo",responseValue);

                    database = FirebaseDatabase.getInstance();
                    reference = database.getReference("crops");

                    reference.addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {

                                if (snapshot.hasChild(responseValue)) {

                                    DataSnapshot cropSnapshot = snapshot.child(responseValue);

                                    // Checking for child nodes exist
                                    if (cropSnapshot.hasChild("soilprep") && cropSnapshot.hasChild("sowing") && cropSnapshot.hasChild("irrigation")&& cropSnapshot.hasChild("fertilization") && cropSnapshot.hasChild("harvesting")) {
                                        // Get the values of the desired child nodes
                                        String soilprepValue = Objects.requireNonNull(cropSnapshot.child("soilprep").getValue()).toString();
                                        String sowingValue = Objects.requireNonNull(cropSnapshot.child("sowing").getValue()).toString();
                                        String irrigaValue = Objects.requireNonNull(cropSnapshot.child("irrigation").getValue()).toString();
                                        String fertilizationValue = Objects.requireNonNull(cropSnapshot.child("fertilization").getValue()).toString();
                                        String harvest = Objects.requireNonNull(cropSnapshot.child("harvesting").getValue()).toString();

                                        // Display
                                        String steps = "Soil Preparation :. " + soilprepValue +". Sowing :. " + sowingValue + ". Irrigation :. " + irrigaValue + ". Fertilization :. " + fertilizationValue + ". Harvesting :. " + harvest +"\n\n";

                                        String[] sentences= steps.split("\\. \\s*");
                                        StringBuilder htmlContent = new StringBuilder();
                                        htmlContent.append("<ul>");
                                        for (String sentence : sentences) {
                                            htmlContent.append("<li>").append(sentence).append("</li>");
                                        }
                                       htmlContent.append("</ul>");

                                        guide.setText(Html.fromHtml("Steps:"+ htmlContent));
                                      // guide.setText("Steps :\n\n" + steps);
                                    } else {

                                        Toast.makeText(cropadvisor.this, " Not found " + responseValue, Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    // If no data exists in the snapshot, display an error message
                                    Toast.makeText(cropadvisor.this, "No data available", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }
}

