package com.cs.wasselha.Transporter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.cs.wasselha.Adapters.ReservationsAdapter;
import com.cs.wasselha.R;

import java.util.ArrayList;

public class TransporterReservationActivity extends AppCompatActivity {

    ListView listView;
    private ArrayList<Reservations> reservationsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transporter_reservation);
        getSupportActionBar().hide();

        //Calls
        setupRefernces();

        populateReservationsData();;

        ReservationsAdapter requestsAdapter = new ReservationsAdapter(getApplicationContext(), R.layout.transporter_reservation_list_view, reservationsData);
        listView.setAdapter(requestsAdapter);


    }

    private void setupRefernces()
    {
        listView = findViewById(R.id.transporterReservationListView);
    }


    private void populateReservationsData()
    {
        reservationsData = new ArrayList<>();

        reservationsData.add(new Reservations("Not available!", "Not available!", "Not available!", "Not available!", "Not available!"));
        reservationsData.add(new Reservations("Not available!", "Not available!","Not available!", "Not available!", "Not available!"));
        reservationsData.add(new Reservations("Not available!", "Not available!","Not available!", "Not available!", "Not available!"));

    }
}