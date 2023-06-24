package com.cs.wasselha.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

import com.cs.wasselha.Adapters.DeliveryStatusAdapter;
import com.cs.wasselha.R;
import com.cs.wasselha.interfaces.implementation.DeliveryStatusDA;
import com.cs.wasselha.model.DeliveryServiceDetails;
import com.cs.wasselha.model.DeliveryStatus;

import java.io.IOException;
import java.util.ArrayList;

public class DeliveryStatusActivity extends AppCompatActivity {


    ListView detailsListView;
    private ArrayList<DeliveryStatus> delivaryDetailsReservationsForCustomerData;


    private static final String ID_KEY = "id";
    private static final String LOGIN_TYPE_KEY = "loginType";
    private static final String PREFERENCES_NAME = "MyPreferences";


    private int customerId;
    private String userType;
    private int delSerDetId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_status);
        getSupportActionBar().hide();


        Intent intent = getIntent();
        if (intent != null)
            delSerDetId = Integer.parseInt(intent.getStringExtra("delSerDetId"));

        detailsListView = findViewById(R.id.deliveryStatusCustomerListView);

        getFromSharedPref();
        try {
            populateNotificationsData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    void getFromSharedPref() {
        SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        userType = preferences.getString(LOGIN_TYPE_KEY, null);
        customerId = Integer.parseInt(preferences.getString(ID_KEY, ""));


    }

    private void populateNotificationsData() throws IOException {


        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                try {
                    delivaryDetailsReservationsForCustomerData = new DeliveryStatusDA().getDelStatuses(delSerDetId);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                DeliveryStatusAdapter deliveryStatusAdapter =
                        new DeliveryStatusAdapter(DeliveryStatusActivity.this, R.layout.delivery_status_customer_list_view, delivaryDetailsReservationsForCustomerData);
                detailsListView.setAdapter(deliveryStatusAdapter);
            }
        });

    }

}