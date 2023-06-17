package com.cs.wasselha.Transporter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cs.wasselha.Adapters.RequestsAdapter;
import com.cs.wasselha.R;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonElement;
import org.json.JSONException;
import org.json.JSONObject;



public class RequestsTransporterFragment extends Fragment {

    private static final String ID_KEY = "id";
    private static final String LOGIN_TYPE_KEY = "loginType";
    private static final String PREFERENCES_NAME = "MyPreferences";
    private static String BASE_URL="http://176.119.254.198:8000/wasselha";
    ListView listView;
    public static ArrayList<Requests> requestsData;
    private RequestQueue requestQueue;
    private Gson gson = new Gson();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_requests_transporter, container, false);
        listView = view.findViewById(R.id.listViewInRequestsTransporterFragment);
        SharedPreferences preferences = getActivity().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        String id = preferences.getString(ID_KEY, null);
        int transporterID=Integer.parseInt(id.trim());
        new AsyncTask<String, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(String... params) {
                try {
                    populateServicesData(transporterID,getContext());
                    return true;
                }catch (Exception e){
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean success) {
                if(success){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("claimmm","size:"+ requestsData.size());
                            RequestsAdapter requestsAdapter = new RequestsAdapter(requireContext(), R.layout.requests_list_view, requestsData);
                            listView.setAdapter(requestsAdapter);
                        }

                    }, 2000);
                }
            }
        }.execute();

        return view;
    }


    private void populateServicesData()
    {
        requestsData = new ArrayList<>();

        requestsData.add(new Requests(1,"Not available!", "Not available!","Not available!", "Not available!", "Not available!", "Not available!"));
        requestsData.add(new Requests(1,"Not available!", "Not available!","Not available!", "Not available!", "Not available!", "Not available!"));
        requestsData.add(new Requests(1,"Not available!", "Not available!","Not available!", "Not available!", "Not available!", "Not available!"));

    }
    private void populateServicesData(int transporterID, Context context) {
        requestQueue = Volley.newRequestQueue(context);
        requestsData = new ArrayList<>();

        String url = BASE_URL + "/services/?transporter=" + transporterID + "&time=upper";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject service = response.getJSONObject(i);
                        int serviceId = service.getInt("id");
                        String serviceDate = service.getString("service_date");

                        String deliveryUrl = BASE_URL + "/delivery-service-details/?service=" + serviceId ;

                        // Nested JsonArrayRequest
                        JsonArrayRequest deliveryDetailsRequest = new JsonArrayRequest(Request.Method.GET, deliveryUrl, null, new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray deliveryResponse) {
                                try {
                                    JSONObject deliveryDetail = deliveryResponse.getJSONObject(0);
                                    boolean responsed = deliveryDetail.getBoolean("responsed");
                                    String price = deliveryDetail.getString("price");
                                    int deliveryDetailsId=deliveryDetail.getInt("id");

                                    final String[] customerName = {"Not available!"};
                                    final String[] customerReview = {"Not available!"};

                                    if (!responsed) {
                                        int customerId = deliveryDetail.getInt("customer");
                                        String customerUrl = BASE_URL + "/customers/" + customerId + "/";
                                        // Nested JsonObjectRequest
                                        JsonObjectRequest customerRequest = new JsonObjectRequest(Request.Method.GET, customerUrl, null, new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject customerResponse) {
                                                try {
                                                    customerName[0] = customerResponse.getString("first_name") + " " + customerResponse.getString("last_name");
                                                    customerReview[0] = String.valueOf(customerResponse.getInt("review"));
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                // Handle error
                                            }
                                        });

                                        requestQueue.add(customerRequest);
                                    }

                                    int sourcePlace = deliveryDetail.getInt("source_place");
                                    JsonElement collectionPoint = gson.fromJson(deliveryDetail.toString(), JsonObject.class).get("source_collection_point");
                                    getCityName(sourcePlace, collectionPoint, new CityNameCallback() {
                                        @Override
                                        public void onCityNameReceived(String sourceCity) throws JSONException {
                                            // This is called when the source city name is retrieved
                                            getCityName(deliveryDetail.getInt("destination_place"), collectionPoint, new CityNameCallback() {
                                                @Override
                                                public void onCityNameReceived(String destinationCity) {
                                                    // This is called when the destination city name is retrieved

                                                    // Now you have both sourceCity and destinationCity
                                                    // You can use them here to add to your requestsData list
                                                    String dateTime = formatDate(serviceDate);
                                                    Log.e("claimmm","add");
                                                    requestsData.add(new Requests(deliveryDetailsId,customerName[0], customerReview[0], sourceCity, destinationCity, price, dateTime));
                                                }
                                            });
                                        }
                                    });
//                                    String sourceCity = getCityName(sourcePlace, collectionPoint);
//                                    String destinationCity = getCityName(deliveryDetail.getInt("destination_place"), collectionPoint);
//                                    String dateTime = formatDate(serviceDate);
//
//                                    requestsData.add(new Requests(customerName[0], customerReview[0], sourceCity, destinationCity, price, dateTime));

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle error
                            }
                        });

                        requestQueue.add(deliveryDetailsRequest);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
            }
        });

        requestQueue.add(jsonArrayRequest);
    }
    private void getCityName(int place, JsonElement collectionPoint, CityNameCallback callback) throws JSONException {
        if (place != 0) {
            String url = BASE_URL + "/locations/" + place + "/";

            JsonObjectRequest locationRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String title = response.getString("title");
                        callback.onCityNameReceived(title);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        try {
                            callback.onCityNameReceived("Not available!");
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    try {
                        callback.onCityNameReceived("Not available!");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            requestQueue.add(locationRequest);

        } else if (collectionPoint != null) {
            int collectionPointId = collectionPoint.getAsInt();
            String url = BASE_URL + "/collection-points/" + collectionPointId + "/";

            JsonObjectRequest collectionPointRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String title = response.getString("title");
                        callback.onCityNameReceived(title);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        try {
                            callback.onCityNameReceived("Not available!");
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    try {
                        callback.onCityNameReceived("Not available!");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            requestQueue.add(collectionPointRequest);

        } else {
            callback.onCityNameReceived("Not available!");
        }
    }

    private String formatDate(String serviceDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        LocalDateTime serviceDateTime = LocalDateTime.parse(serviceDate, formatter);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        String date = serviceDateTime.format(dateFormatter);
        String time = serviceDateTime.format(timeFormatter);
        // You can implement this function to format the date and time as per your requirement
        // serviceDate is in the format: "2023-06-18T10:00:00+03:00"
        // You need to split it and concatenate as "yyyy/mm/dd, hh:mm:ss"
        return date+", "+time;
    }
    public interface CityNameCallback {
        void onCityNameReceived(String cityName) throws JSONException;
    }

}