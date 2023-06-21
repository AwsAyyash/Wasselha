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

    TextView transporterReviewTXT;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);
        getSupportActionBar().hide();

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

            String transporterId = intent.getStringExtra("transporterId");
            String  transporterReview= intent.getStringExtra("reviewT");
            String  srcCityName= intent.getStringExtra("srcCity");
            String  destCityName= intent.getStringExtra("destCity");

            transporterReviewTXT.setText(transporterReview);
            transporterNameInServiceDet.setText(transporterName);
            timeInCustomer.setText(service.getService_date().toString());


                srcCity.setText(srcCityName);

                destCity.setText(destCityName);

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

                Log.d("before1212","before go to the reservetion det");
                Intent intent=new Intent(ServiceDetailsActivity.this,ReservationDetailsActivity.class);
                startActivityForResult(intent, 2);// Activity is started with requestCode 2
                Log.d("after1212","after go to the reservetion det");

                // todo: here i should route it to the reserveAService activity Page


                // todo: then issue a notification and store it in the database

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
        transporterReviewTXT = findViewById(R.id.transporterReviewInInServiceDetailsPage);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {
            String message=data.getStringExtra("MESSAGE");
           // textView1.setText(message);
        }
    }


}