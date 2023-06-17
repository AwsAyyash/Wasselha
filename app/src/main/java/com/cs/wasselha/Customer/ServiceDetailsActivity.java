package com.cs.wasselha.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cs.wasselha.R;
import com.cs.wasselha.RegistrationActivity;
import com.cs.wasselha.interfaces.implementation.LocationDA;
import com.cs.wasselha.model.DeliveryServiceDetails;
import com.cs.wasselha.model.Service;
import com.google.gson.Gson;

import java.io.IOException;

public class ServiceDetailsActivity extends AppCompatActivity {

    Service service;

    TextView transporterNameInServiceDet;
    TextView timeInCustomer;
    TextView srcCity;
    TextView destCity;
    TextView vehicleTypeInServiceDet;
    TextView priceInServiceDet;
    Button reserveBtnServiceDetailsPage;
    ImageView imageViewCar ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);
       getSupportActionBar().hide();
        reserveBtnSetup();

        setUpViews();
        Intent intent = getIntent();
        Log.d("inSDA1",intent.toString());
        if (intent != null) {

            Log.d("inSDA2",intent.toString());
            String strObj = intent.getStringExtra("serviceDet");
            String transporterName = intent.getStringExtra("transporterName");
            Gson gson = new Gson();
            service = gson.fromJson(strObj, Service.class);
            String vehicleType = intent.getStringExtra("vehicleType");

            String imageUrl = intent.getStringExtra("imageUrl");

            int transpoterId = Integer.parseInt(intent.getStringExtra("transpoterId"));
            transporterNameInServiceDet.setText(transporterName);
            timeInCustomer.setText(service.getService_date().toString());
            try {
                srcCity.setText(new LocationDA().getLocation(service.getSource_place()).getTitle());

                destCity.setText(new LocationDA().getLocation(service.getDestination_place()).getTitle());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            vehicleTypeInServiceDet.setText(vehicleType);
            priceInServiceDet.setText(String.valueOf(service.getPrice()));
            Log.d("inSDA3",service.getPrice()+"");

            Glide.with(this)
                    .load(imageUrl)
                    .into(imageViewCar);
        }

        reserveBtnServiceDetailsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





            }
        });


    }

    private void setUpViews() {

        transporterNameInServiceDet = findViewById(R.id.transporterNameInServiceDetailsPage);
        timeInCustomer = findViewById(R.id.timeInInServiceDetailsPage);
        srcCity = findViewById(R.id.sourceCityInServiceDetailsPage);

        destCity = findViewById(R.id.destinationCityInServiceDetailsPage);
        vehicleTypeInServiceDet = findViewById(R.id.vehicleTypeInServiceDetailsPage);

        priceInServiceDet = findViewById(R.id.priceInServiceDetailsPage);
        reserveBtnServiceDetailsPage = findViewById(R.id.reserveBtnServiceDetailsPage);

        imageViewCar = findViewById(R.id.imageViewVehicleInServiceDetails);
    }

    private void reserveBtnSetup()
    {
        reserveBtnServiceDetailsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ServiceDetailsActivity.this, ReservationDetailsActivity.class);
                startActivity(intent);
            }
        });
    }


}