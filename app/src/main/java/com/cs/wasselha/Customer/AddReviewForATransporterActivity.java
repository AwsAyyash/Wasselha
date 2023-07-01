package com.cs.wasselha.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cs.wasselha.Customer.ServiceDetailsActivity;
import com.cs.wasselha.R;
import com.cs.wasselha.interfaces.implementation.ClaimsDA;
import com.cs.wasselha.interfaces.implementation.DeliveryServiceDetailsDA;
import com.cs.wasselha.interfaces.implementation.ServiceDA;
import com.cs.wasselha.model.Claim;

import java.io.IOException;
import java.util.Date;
import java.util.TimeZone;

public class AddReviewForATransporterActivity extends AppCompatActivity {


    int delSerDetId;
    int writer_id;

    EditText reviewAboutTransporterEditText;
    EditText noteAboutTransporterInReviewTransporter;
    Button addReviewAboutTransporterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review_for_atransporter);
        getSupportActionBar().hide();


        setRefs();
        btnSetup();
        Intent intent = getIntent();
        if (intent != null) {

            delSerDetId = Integer.parseInt(intent.getStringExtra("delSerDetId"));
            writer_id = Integer.parseInt(intent.getStringExtra("writer_id"));




        }

    }

    private void setRefs() {
        reviewAboutTransporterEditText = findViewById(R.id.reviewAboutTransporterEditText);
        noteAboutTransporterInReviewTransporter = findViewById(R.id.noteAboutTransporterInReviewTransporter);
        addReviewAboutTransporterBtn = findViewById(R.id.addReviewAboutTransporterBtn);

    }

    private void btnSetup() {
        addReviewAboutTransporterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        int serviceId;
                        int transId;
                        try {
                            serviceId=   new DeliveryServiceDetailsDA().getDSD(delSerDetId).getService();
                            transId = new ServiceDA().getService(serviceId).getTransporter();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        String msg = noteAboutTransporterInReviewTransporter.getText().toString();
                        String review  = reviewAboutTransporterEditText.getText().toString();
                        if (msg.equals("") || review.equals("")){

                            Toast.makeText(AddReviewForATransporterActivity.this, "Please add input, Thanks!", Toast.LENGTH_LONG).show();
                            return;
                        }


                        Date nowDate = new Date();
                        String dateTextNow = (nowDate.getYear()+1900) +"-"
                                +(nowDate.getMonth()+1) + "-" + (nowDate.getDate());
                        String dateTimeStringNow = String.format(
                                "%sT%02d:%02d:00", // Format as "yyyy-MM-ddTHH:mm:ss"
                                dateTextNow,
                                nowDate.getHours(),
                                nowDate.getMinutes()
                        );

                        TimeZone timeZone = TimeZone.getDefault();
                        long offsetInMillis = timeZone.getOffset(System.currentTimeMillis());
                        int offsetHours = (int) (offsetInMillis / 3600000); // Convert to hours
                        int offsetMinutes = (int) (offsetInMillis / 60000) % 60; // Convert to minutes
                        String serviceDateNow= String.format(
                                "%s%+03d:%02d", // Format as "yyyy-MM-ddTHH:mm:ss+HH:mm"
                                dateTimeStringNow,
                                offsetHours,
                                offsetMinutes
                        );


                        Claim claim = new Claim(0, delSerDetId, writer_id, transId,
                                "customer","transporter",msg,Integer.parseInt(review),serviceDateNow);

                        try {
                            new ClaimsDA().saveClaim(claim);
                            Toast.makeText(AddReviewForATransporterActivity.this, "Review added, Thanks!", Toast.LENGTH_LONG).show();
                            finish();
                        } catch (IOException e) {
                            Toast.makeText(AddReviewForATransporterActivity.this, "Please, try again!", Toast.LENGTH_LONG).show();

                            throw new RuntimeException(e);
                        }

                    }
                });
            }
        });
    }
}