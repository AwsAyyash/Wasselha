package com.cs.wasselha;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

public class AddServiceTransporterFragment extends Fragment {

    NumberPicker hoursPicker, minutesPicker, amPmPicker;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_service_transporter, container, false);


        //References
        setUpReferences(view);

        return view;
    }



    //References
    private void setUpReferences(View view)
    {
        hoursPicker = view.findViewById(R.id.numPickerHourInAddNewService);
        hoursPicker.setTextColor(Color.WHITE);
        hoursPicker.setMinValue(1);
        hoursPicker.setMaxValue(12);

        minutesPicker = view.findViewById(R.id.numPickerMinInAddNewService);
        minutesPicker.setTextColor(Color.WHITE);
        minutesPicker.setMinValue(0);
        minutesPicker.setMaxValue(59);

        amPmPicker = view.findViewById(R.id.numPickerAMPMInAddNewService);
        amPmPicker.setTextColor(Color.WHITE);
        amPmPicker.setMinValue(0);
        amPmPicker.setMaxValue(1);
        amPmPicker.setDisplayedValues(new String[] {"AM", "PM"});

    }


}