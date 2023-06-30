package com.cs.wasselha.interfaces.implementation;

import android.os.StrictMode;
import android.util.Log;

import com.cs.wasselha.model.Claim;
import com.cs.wasselha.model.DeliveryStatus;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ClaimsDA {



    OkHttpClient client = new OkHttpClient();
    ArrayList<DeliveryStatus> dsdListGlobal = new ArrayList<>();

    Gson gson = new Gson();


    public ClaimsDA() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

    }
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    String url = "http://176.119.254.198:8000/wasselha/claims/";
    // @Override
    public String saveClaim(Claim claim) throws IOException {


        RequestBody body = RequestBody.create(gson.toJson(claim), JSON);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            Log.d("claim",gson.toJson(claim));
            Log.d("claim res",response.peekBody(3048).string());
            return response.peekBody(3048).string();
        }

    }
}
