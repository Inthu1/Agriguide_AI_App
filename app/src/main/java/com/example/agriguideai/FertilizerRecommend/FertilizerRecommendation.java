package com.example.agriguideai.FertilizerRecommend;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FertilizerRecommendation extends AsyncTask<Void,Void,Void> {
        private String url;
        private com.example.agriguideai.FertilizerRecommend.fertilizerrecommendationresponse fertilizerrecommendationresponse;

        public FertilizerRecommendation(String url, com.example.agriguideai.FertilizerRecommend.fertilizerrecommendationresponse fertilizerrecommendationresponse) {
            this.url = url;
            this.fertilizerrecommendationresponse= fertilizerrecommendationresponse;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("Nitrogen","20")
                    .addFormDataPart("Potassium", "0")
                    .addFormDataPart("Phosphorous", "10")
                    .build();
            Request request = new Request.Builder()

                    .url(this.url)
                    .method("GET", null).build();
            try {
                Response response = client.newCall(request).execute();
                //Log.d("Response Body", response.body().string());
                fertilizerrecommendationresponse.onResponse(response.body().string());

            } catch (IOException e) {

                e.printStackTrace();
            }
            Log.d("TAG", "function end");

            return null;
        }
    }
