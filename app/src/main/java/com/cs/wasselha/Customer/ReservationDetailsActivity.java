package com.cs.wasselha.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.cs.wasselha.R;
import com.cs.wasselha.interfaces.implementation.CollectionPointDA;
import com.cs.wasselha.interfaces.implementation.LocationDA;
import com.cs.wasselha.model.CollectionPoint;
import com.cs.wasselha.model.DeliveryServiceDetails;
import com.cs.wasselha.model.Location;

import java.io.IOException;
import java.util.ArrayList;

public class ReservationDetailsActivity extends AppCompatActivity {

    EditText  packageTypeInReservationsDetails;
    EditText  packageWeightInReservationsDetails;
    RadioButton collectFromLocationRadioButtonInReservationsDetails;
    RadioButton collectFromCollectionPointRadioButtonInReservationsDetails;

    Spinner collectFromCollectionPointSpinner;

    RadioButton handOverToLocationRadioButtonInReservationsDetails;
    RadioButton handOverToCollectionPointRadioButtonInReservationsDetails;

    Spinner handOverToCollectionPointSpinner;

    Button reserveBtnReservationDetailsPage;

    ArrayList<CollectionPoint> collectionPoints;
    ArrayList<String> spinnerStrings = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_details);
        getSupportActionBar().hide();

        setUpViews();


        String packType = packageTypeInReservationsDetails.getText().toString();
        String packWeight = packageWeightInReservationsDetails.getText().toString();

        collectFromCollectionPointRadioButtonInReservationsDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRadioCollectionPointForSpinners();
            }
        });
        handOverToCollectionPointRadioButtonInReservationsDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRadioCollectionPointForSpinners();
            }
        });

        int fromCPLocationId;
        int toCPLocationId;
        Intent intent=new Intent();
        if (collectFromLocationRadioButtonInReservationsDetails.isChecked()){

            // todo: here i have to handle the google map
        }else {
            // the collectionPoint is checked

            fromCPLocationId = Integer.parseInt(collectFromCollectionPointSpinner.getSelectedItem().toString().split(" ")[0]);

            intent.putExtra("fromCPLocationId",String.valueOf(fromCPLocationId));

        }


        if (handOverToLocationRadioButtonInReservationsDetails.isChecked()){

            // todo: here i have to handle the google map
        }else {
            // the collectionPoint is checked

            toCPLocationId = Integer.parseInt(collectFromCollectionPointSpinner.getSelectedItem().toString().split(" ")[0]);
            intent.putExtra("toCPLocationId",String.valueOf(toCPLocationId));


        }

        //DeliveryServiceDetails deliveryServiceDetails = new DeliveryServiceDetails();
        //deliveryServiceDetails


        intent.putExtra("packWeight",packWeight);
        intent.putExtra("packType",packType);
        intent.putExtra("packType",packType);
        setResult(2,intent);
        finish();//finishing activity


    }

    private void setUpViews() {

        packageTypeInReservationsDetails = findViewById(R.id.packageTypeInReservationsDetails);
        packageWeightInReservationsDetails = findViewById(R.id.packageWeightInReservationsDetails);
        collectFromLocationRadioButtonInReservationsDetails =
                findViewById(R.id.collectFromLocationRadioButtonInReservationsDetails);

        collectFromCollectionPointRadioButtonInReservationsDetails =
                findViewById(R.id.collectFromCollectionPointRadioButtonInReservationsDetails);

        collectFromCollectionPointSpinner = findViewById(R.id.collectFromCollectionPointSpinner);

        handOverToLocationRadioButtonInReservationsDetails =
                findViewById(R.id.handOverToLocationRadioButtonInReservationsDetails);

        handOverToCollectionPointRadioButtonInReservationsDetails =
                findViewById(R.id.handOverToCollectionPointRadioButtonInReservationsDetails);

        handOverToCollectionPointSpinner = findViewById(R.id.handOverToCollectionPointSpinner);

        reserveBtnReservationDetailsPage = findViewById(R.id.reserveBtnReservationDetailsPage);

    }

    private void onClickRadioCollectionPointForSpinners(){
        CollectionPointDA collectionPointDA = new CollectionPointDA();

        try {
            collectionPoints = collectionPointDA.getCollectionPs();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        LocationDA locationDA = new LocationDA();
        for (CollectionPoint c: collectionPoints) {


            try {
                Location location = locationDA.getLocation(c.getLocation());
                spinnerStrings.add( location.getId()+" "+location.getTitle());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerStrings);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        handOverToCollectionPointSpinner.setAdapter(arrayAdapter);
        collectFromCollectionPointSpinner.setAdapter(arrayAdapter);
    }
}