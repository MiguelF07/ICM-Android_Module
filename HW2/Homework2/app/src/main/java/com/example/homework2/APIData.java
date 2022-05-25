package com.example.homework2;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class APIData extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... urls) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(urls[0])
                .get()
                .build();

        String response = "";
        try {
            response = client.newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return response;
    }
}
