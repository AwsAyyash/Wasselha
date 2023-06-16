package com.cs.wasselha.Transporter;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.cs.wasselha.Adapters.ClaimsTransporterAdapter;
import com.cs.wasselha.Claims.Claims;
import com.cs.wasselha.R;

import java.util.ArrayList;

public class TransporterClaimsActivity extends AppCompatActivity {

    ListView claimsListView;
    private ArrayList<Claims> claimsTransporterData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claims_transporter);
        getSupportActionBar().hide();

        //Calls
        setupReference();
        populateClaimsData();
        ClaimsTransporterAdapter claimsTransporterAdapter = new ClaimsTransporterAdapter(getApplicationContext(), R.layout.claims_list_view, claimsTransporterData);
        claimsListView.setAdapter(claimsTransporterAdapter);

    }


    //--------------Methods---------------------------------------------------------
    //References
    private void setupReference()
    {
        claimsListView = findViewById(R.id.claimsTransporterListView);
    }

    private void populateClaimsData()
    {
        claimsTransporterData = new ArrayList<>();

        claimsTransporterData.add(new Claims(R.drawable.ic_claim, "Not available review", "Not available message!", "Not available Date!"));
        claimsTransporterData.add(new Claims(R.drawable.ic_claim, "Not available review", "Not available message!", "Not available Date!"));
        claimsTransporterData.add(new Claims(R.drawable.ic_claim, "Not available review", "Not available message!", "Not available Date!"));
    }
}
