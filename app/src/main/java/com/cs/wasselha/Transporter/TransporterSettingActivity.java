package com.cs.wasselha.Transporter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cs.wasselha.R;

public class TransporterSettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transporter_setting);
        getSupportActionBar().hide();
    }
}