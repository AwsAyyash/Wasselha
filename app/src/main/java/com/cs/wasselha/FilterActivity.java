package com.cs.wasselha;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.NumberPicker;

public class FilterActivity extends AppCompatActivity {

    NumberPicker hoursPicker, minutesPicker, amPmPicker;

//    int hours = hoursPicker.getValue();
//    int minutes = minutesPicker.getValue();
//    String amPm = amPmPicker.getDisplayedValues()[amPmPicker.getValue()];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        getSupportActionBar().hide();

        //References
        setUpReferences();

    }


    //References
    private void setUpReferences()
    {
        hoursPicker = findViewById(R.id.numPickerHour);
        hoursPicker.setTextColor(Color.WHITE);
        hoursPicker.setMinValue(1);
        hoursPicker.setMaxValue(12);

        minutesPicker = findViewById(R.id.numPickerMin);
        minutesPicker.setTextColor(Color.WHITE);
        minutesPicker.setMinValue(0);
        minutesPicker.setMaxValue(59);

        amPmPicker = findViewById(R.id.numPickerAMPM);
        amPmPicker.setTextColor(Color.WHITE);
        amPmPicker.setMinValue(0);
        amPmPicker.setMaxValue(1);
        amPmPicker.setDisplayedValues(new String[] {"AM", "PM"});

    }
}