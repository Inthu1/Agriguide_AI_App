package com.example.agriguideai.CropRecommend;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class CropRecommendation extends AsyncTask<Void,Void,Void>{
    private String url;
    private com.example.agriguideai.CropRecommend.croprecommendationresponse croprecommendationresponse;

    public CropRecommendation(String url, com.example.agriguideai.CropRecommend.croprecommendationresponse croprecommendationresponse) {
        this.url = url;
        this.croprecommendationresponse= croprecommendationresponse;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("N", "94")
                .addFormDataPart("P", "53")
                .addFormDataPart("K", "40")
                .addFormDataPart("PH", "7")
                .addFormDataPart("Temperature", "23.05804872")
                .addFormDataPart("Humidity", "81.41753846")
                .addFormDataPart("rainfall", "262.7173405")
                .build();
        Request request = new Request.Builder()

                .url(this.url)
                .method("GET", null).build();
        try {
            Response response = client.newCall(request).execute();
            //Log.d("Response Body", response.body().string());
            croprecommendationresponse.onResponse(response.body().string());

        } catch (IOException e) {

            e.printStackTrace();
        }
        Log.d("TAG", "function end");

        return null;
    }
}
