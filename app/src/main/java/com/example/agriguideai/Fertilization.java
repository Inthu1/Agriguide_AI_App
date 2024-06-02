package com.example.agriguideai;

import android.annotation.SuppressLint;
import android.graphics.text.LineBreaker;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class Fertilization extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fertilization);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView text = findViewById(R.id.textView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            text.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
        }

        String paragraph = MyAdapter.cropFertilization;
        String[] sentences = paragraph.split("\\. \\s*");
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<ul>");
        for (String sentence : sentences) {
            htmlContent.append("<li>").append(sentence).append("</li>");
        }
        htmlContent.append("</ul>");

        text.setText(Html.fromHtml(htmlContent.toString()));
    }
}