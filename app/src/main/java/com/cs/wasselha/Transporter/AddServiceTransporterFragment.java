package com.cs.wasselha.Transporter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cs.wasselha.Customer.CustomerSignupActivity;
import com.cs.wasselha.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddServiceTransporterFragment extends Fragment {

    private static final String ID_KEY = "id";
    private static final String LOGIN_TYPE_KEY = "loginType";
    private static final String PREFERENCES_NAME = "MyPreferences";
    EditText price;
    NumberPicker hoursPicker, minutesPicker, amPmPicker;
    DatePickerDialog.OnDateSetListener setListener;
    Button create;
    TextView dateService;
    int sourceLocationID,destanitionLocationID;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_service_transporter, container, false);
        SharedPreferences preferences = getActivity().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        String id = preferences.getString(ID_KEY, null);
        int transporterID=Integer.parseInt(id.trim());

        //References
        setUpReferences(view);
        dateServiceSetup();




        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(verifyInputsAndSubmit())
                {
                    createLocation(1.2,1.2,"ramallah","kufer nema",true);
                    createLocation(1.2,1.2,"ramallah","kufer nema",false);
                    createService(transporterID,1,1);
                }
            }
        });

        return view;
    }

    private void dateServiceSetup()
    {
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        dateService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener,year, month, day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = day + "/" + month + "/" + year;
                price.setText(date);
            }
        };

    }


    private void createLocation(double latitude, double longitude, String title, String description,boolean isSource) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = "http://176.119.254.198:8000/wasselha/locations/";

        JSONObject jsonObject = new JSONObject();

        try
        {
            jsonObject.put("title", title);
            jsonObject.put("description", description);
            jsonObject.put("latitude", latitude);
            jsonObject.put("longitude", longitude);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        // Create the POST request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            int id = response.getInt("id");

                            if (id > 0 )
                            {
                                if(isSource){
                                    sourceLocationID=id;
                                }else{
                                    destanitionLocationID=id;
                                }
                                Toast.makeText(getContext(), "Created Successfully", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(getContext(), "The information is not correct, try again!", Toast.LENGTH_LONG).show();
                            }
                        }
                        catch (JSONException e)
                        {

                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getContext(), "Network error, please try again later!", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        // Add the request to the RequestQueue
        requestQueue.add(jsonObjectRequest);
    }


    private void createService(int transporterId,int sourcePlace,int destanitionPlace) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = "http://176.119.254.198:8000/wasselha/services/";

        JSONObject jsonObject = new JSONObject();

        try
        {
            jsonObject.put("service_date", price.getText().toString());
            jsonObject.put("price", Double.parseDouble(price.getText().toString()));
            jsonObject.put("transporter", transporterId);
            jsonObject.put("source_place", sourcePlace);
            jsonObject.put("destination_place", destanitionPlace);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        // Create the POST request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            int id = response.getInt("id");

                            if (id > 0 )
                            {
                                Toast.makeText(getContext(), "Created Successfully", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(getContext(), "The information is not correct, try again!", Toast.LENGTH_LONG).show();
                            }
                        }
                        catch (JSONException e)
                        {

                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getContext(), "Network error, please try again later!", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        // Add the request to the RequestQueue
        requestQueue.add(jsonObjectRequest);
    }

    private boolean verifyInputsAndSubmit() {

        return false;
    }


    //References
    private void setUpReferences(View view)
    {
        dateService = view.findViewById(R.id.dateService);

        create = view.findViewById(R.id.addServiceBtnInAddNewServicePage);
        price = view.findViewById(R.id.priceInAddNewService);
        hoursPicker = view.findViewById(R.id.numPickerHourInAddNewService);
        hoursPicker.setTextColor(Color.WHITE);
        hoursPicker.setMinValue(1);
        hoursPicker.setMaxValue(12);

        minutesPicker = view.findViewById(R.id.numPickerMinInAddNewService);
        minutesPicker.setTextColor(Color.WHITE);
        minutesPicker.setMinValue(0);
        minutesPicker.setMaxValue(59);

        amPmPicker = view.findViewById(R.id.numPickerAMPMInAddNewService);
        amPmPicker.setTextColor(Color.WHITE);
        amPmPicker.setMinValue(0);
        amPmPicker.setMaxValue(1);
        amPmPicker.setDisplayedValues(new String[] {"AM", "PM"});

    }


}