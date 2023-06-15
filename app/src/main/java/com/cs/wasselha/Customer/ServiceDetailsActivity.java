package com.cs.wasselha.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.cs.wasselha.R;
import com.cs.wasselha.model.Service;
import com.google.gson.Gson;

public class ServiceDetailsActivity extends AppCompatActivity {

    Service service;

    TextView transporterNameInServiceDet;
    TextView timeInCustomer;
    TextView srcCity;
    TextView destCity;
    TextView vehicleTypeInServiceDet;
    TextView priceInServiceDet;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);
        getSupportActionBar().hide();

        setUpViews();
        Intent intent = getIntent();
        //Log.d("inSDA",intent.toString());
        if (intent != null) {


            String strObj = intent.getStringExtra("serviceDet");
            String transporterName = intent.getStringExtra("transporterName");
            Gson gson = new Gson();
            service = gson.fromJson(strObj, Service.class);
            String vehicleType = intent.getStringExtra("vehicleType");

            transporterNameInServiceDet.setText(transporterName);
            timeInCustomer.setText(service.getService_date().toString());
            srcCity.setText(service.getSource_place());
            destCity.setText(service.getDestination_place());
            vehicleTypeInServiceDet.setText(vehicleType);
            priceInServiceDet.setText(service.getPrice() + "");
            Log.d("inSDASS",service.getPrice()+"");
        }


    }

    private void setUpViews() {

        transporterNameInServiceDet = findViewById(R.id.transporterNameInServiceDetailsPage);
        timeInCustomer = findViewById(R.id.timeInCustomerRecyclerView);
        srcCity = findViewById(R.id.sourceCity);

        destCity = findViewById(R.id.destinationCity);
        vehicleTypeInServiceDet = findViewById(R.id.vehicleTypeInServiceDetailsPage);

        priceInServiceDet = findViewById(R.id.priceInServiceDetailsPage);
    }
}