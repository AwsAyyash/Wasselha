package com.cs.wasselha.Transporter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cs.wasselha.Adapters.RequestsAdapter;
import com.cs.wasselha.Adapters.ReservationsAdapter;
import com.cs.wasselha.R;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TransporterReservationActivity extends AppCompatActivity {

    private static final String ID_KEY = "id";
    private static final String LOGIN_TYPE_KEY = "loginType";
    private static final String PREFERENCES_NAME = "MyPreferences";
    private static String BASE_URL="http://176.119.254.198:8000/wasselha";
    private RequestQueue requestQueue;
    private Gson gson = new Gson();
    ListView listView;
    private ArrayList<Reservations> reservationsData;
    //reservationsData

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_transporter_reservation);
            getSupportActionBar().hide();
            SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
            String id = preferences.getString(ID_KEY, null);
            int transporterID = Integer.parseInt(id.trim());

            //Calls
            setupRefernces();

            new AsyncTask<String, Void, Boolean>() {
                private ProgressDialog progressDialog;
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    progressDialog = new ProgressDialog(TransporterReservationActivity.this);
                    progressDialog.setMessage("Loading...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                }
                @Override
                protected Boolean doInBackground(String... params) {
                    try {
                        populateReservationsData(transporterID);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }

                @Override
                protected void onPostExecute(Boolean success) {
                    if (success) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                try {

                                    Log.e("claimmmmmmm22", "size:" + reservationsData.size());
                                    ReservationsAdapter requestsAdapter = new ReservationsAdapter(getApplicationContext(), R.layout.transporter_reservation_list_view, reservationsData);
                                    listView.setAdapter(requestsAdapter);
                                } catch (Exception e) {
                                    Log.e("RequestsTransporterFragment", e.toString());
                                }
                            }

                        }, 2000);
                    }
                }
            }.execute();
        }catch (Exception e){
            Log.e("TransporterReservationsListView",e.toString());
            Toast.makeText(this, "Networking error, please try later", Toast.LENGTH_SHORT).show();
        }


    }

    private void setupRefernces()
    {
        listView = findViewById(R.id.transporterReservationListView);
    }


    private void populateReservationsData(int transporterID)
    {
        reservationsData = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        reservationsData = new ArrayList<>();

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
                                    Log.e("claimmm",deliveryDetail.toString());
                                    boolean responsed = deliveryDetail.getBoolean("responsed");
                                    boolean accepted = deliveryDetail.getBoolean("responsed");
                                    int deliveryDetailsId=deliveryDetail.getInt("id");

                                    final String[] customerName = {"Not available!"};
                                    final String[] packageType = {"Not available!"};
                                    if (responsed & accepted) {
                                        int customerId = deliveryDetail.getInt("customer");
                                        String customerUrl = BASE_URL + "/customers/" + customerId + "/";
                                        // Nested JsonObjectRequest
                                        JsonObjectRequest customerRequest = new JsonObjectRequest(Request.Method.GET, customerUrl, null, new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject customerResponse) {
                                                try {
                                                    Log.e("claimmm","get name");
                                                    customerName[0] = customerResponse.getString("first_name") + " " + customerResponse.getString("last_name");
                                                } catch (JSONException e) {
                                                    Log.e("claimmm","error in get name (exception)");
                                                    e.printStackTrace();
                                                }
                                            }
                                        }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                Log.e("claimmm","error in get name and review");
                                                // Handle error
                                            }
                                        });

                                        requestQueue.add(customerRequest);
                                        String packageUrl = BASE_URL + "/packages/?deliveryservicedetails="+deliveryDetailsId;
                                        // Nested JsonObjectRequest
                                        JsonArrayRequest packageRequest = new JsonArrayRequest(Request.Method.GET, customerUrl, null, new Response.Listener<JSONArray>() {
                                            @Override
                                            public void onResponse(JSONArray packageResponse) {
                                                try {
                                                    JSONObject package_resource = packageResponse.getJSONObject(0);
                                                    Log.e("claimmm","get package type");
                                                    packageType[0] = package_resource.getString("type");
                                                } catch (JSONException e) {
                                                    Log.e("claimmm","error in get package type (exception)");
                                                    e.printStackTrace();
                                                }
                                            }
                                        }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                Log.e("claimmm","error in get name and review");
                                                // Handle error
                                            }
                                        });

                                        int sourcePlace = deliveryDetail.getInt("source_place");
                                        JsonElement collectionPoint = gson.fromJson(deliveryDetail.toString(), JsonObject.class).get("source_collection_point");
                                        getCityName(sourcePlace, collectionPoint, new RequestsTransporterFragment.CityNameCallback() {
                                            @Override
                                            public void onCityNameReceived(String sourceCity) throws JSONException {
                                                // This is called when the source city name is retrieved
                                                getCityName(deliveryDetail.getInt("destination_place"), collectionPoint, new RequestsTransporterFragment.CityNameCallback() {
                                                    @Override
                                                    public void onCityNameReceived(String destinationCity) {
                                                        // This is called when the destination city name is retrieved

                                                        // Now you have both sourceCity and destinationCity
                                                        // You can use them here to add to your requestsData list
                                                        String dateTime = formatDate(serviceDate);
                                                        Log.e("claimmm","add");
//                                                        Requests request=new Requests(deliveryDetailsId,customerName[0], packageType[0], sourceCity, destinationCity, "price", dateTime);
                                                        Reservations reservation=new Reservations(deliveryDetailsId,customerName[0], packageType[0],sourceCity, destinationCity, dateTime);
                                                        Log.e("request-value",reservation.toString());
                                                        reservationsData.add(reservation);
                                                    }
                                                });
                                            }
                                        });

                                    }
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
    private void getCityName(int place, JsonElement collectionPoint, RequestsTransporterFragment.CityNameCallback callback) throws JSONException {
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

//        reservationsData.add(new Reservations("Not available!", "Not available!", "Not available!", "Not available!", "Not available!"));
//        reservationsData.add(new Reservations("Not available!", "Not available!","Not available!", "Not available!", "Not available!"));
//        reservationsData.add(new Reservations("Not available!", "Not available!","Not available!", "Not available!", "Not available!"));
//
//    }
}