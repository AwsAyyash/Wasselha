package com.cs.wasselha.Transporter;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cs.wasselha.Adapters.ReservationsAdapter;
import com.cs.wasselha.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TransporterReservationDetailsActivity extends AppCompatActivity {


    private static final String ID_KEY = "id";
    private static final String LOGIN_TYPE_KEY = "loginType";
    private static final String PREFERENCES_NAME = "MyPreferences";
    private static String BASE_URL="http://176.119.254.198:8000/wasselha";
    private static String transporterName="me";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transporter_reservation_details);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        final int deliveryServiceDetailsId = intent.getIntExtra("deliveryservicedetails", 0);

        final Spinner deliveryTypeSpinner = findViewById(R.id.deliveryTypeSpinner);
        final EditText personNameEditText = findViewById(R.id.personNameInReservationsDetailsPage);
        AppCompatButton reserveButton = findViewById(R.id.reserveBtnServiceDetailsPage);
        SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        String id = preferences.getString(ID_KEY, null);
        int transporterID=Integer.parseInt(id.trim());
        setAndGetName(this,transporterID);

        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String actionTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
                String collectionFrom = transporterName;
                String handoverTo = personNameEditText.getText().toString();

                if ("Collect from".equals(deliveryTypeSpinner.getSelectedItem().toString())) {
                    collectionFrom = personNameEditText.getText().toString();
                    handoverTo = transporterName;
                }
                personNameEditText.setText("");
            }
        });
    }
    public void setAndGetName(Context context, int transporterID) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/transporters/" + transporterID + "/";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String firstName = response.getString("first_name");
                            String lastName = response.getString("last_name");
                            String fullName = firstName + " " + lastName;

                            // Assuming that 'name' is a TextView instance you want to set the name to.
                            transporterName=fullName;

                        } catch (Exception e) {
                            Log.e("profile","Error:"+e.toString());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("profile","Error:"+error.toString());
                // Handle error here
                error.printStackTrace();
            }
        });

        queue.add(jsonObjectRequest);
    }

    private void sendPostRequest(final int deliveryServiceDetailsId, final String actionTime, final String collectionFrom, final String handoverTo) {
        String url = BASE_URL + "/delivery-status/";

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sending data...");
        progressDialog.show();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Data sent successfully!", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Error sending data", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("delivery_service_details", String.valueOf(deliveryServiceDetailsId));
                params.put("action_time", actionTime);
                params.put("collection_from", collectionFrom);
                params.put("handover_to", handoverTo);
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(postRequest);
    }
}