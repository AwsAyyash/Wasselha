package com.cs.wasselha.interfaces.implementation;

import android.os.StrictMode;
import android.util.Log;

import com.cs.wasselha.model.Location;
<<<<<<< HEAD
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
=======
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
>>>>>>> 5fcdbe0afa329a10092af74c4d4646951c3d202b

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LocationDA {

    OkHttpClient client = new OkHttpClient();
    ArrayList<Location> locationListGlobal = new ArrayList<>();

    Gson gson = new Gson();


    public LocationDA() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

    }

    public ArrayList<Location> getLocationListGlobal() {
        return locationListGlobal;
    }


    //    @Override
    public ArrayList<Location> getLocations() throws IOException {


        String url = "http://176.119.254.198:8000/wasselha/locations/";
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            Gson gson = new Gson();
            Type transportersListType = new TypeToken<ArrayList<Location>>() {
            }.getType();
            locationListGlobal = new ArrayList<>();
            locationListGlobal = gson.fromJson(response.peekBody(2048).string(), transportersListType);
            // todo: here i should fill the data into the activity
            //Log.d("response.body().string()", response.body().string());

        }


        return locationListGlobal;


    }


    // @Override
    public Location getLocation(int id) throws IOException {

        String url = "http://176.119.254.198:8000/wasselha/locations/" + id + "/";
        Request request = new Request.Builder()
                .url(url)
                .build();
        Location transporter;
        try (Response response = client.newCall(request).execute()) {

            //Type customerListType = new TypeToken<ArrayList<Customer>>() {}.getType();
            //customerListGlobal = new ArrayList<>();
            transporter = gson.fromJson(response.peekBody(2048).string(), Location.class);
            // todo: here i should fill the data into the activity
            //Log.d("response.body().string()", response.body().string());

        }

        return transporter;
    }

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    // @Override
    public String saveLocation(Location location) throws IOException {

       // Gson gson = new GsonBuilder()
         //       .excludeFieldsWithoutExposeAnnotation()
           //     .create();
        String url = "http://176.119.254.198:8000/wasselha/locations/";
        RequestBody body = RequestBody.create(gson.toJson(location), JSON);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            Log.d("toJsonTransLocations2",gson.toJson(location));
            Log.d("res2",response.peekBody(2048).string());
            return response.peekBody(2048).string();
        }

    }
}