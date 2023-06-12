package com.cs.wasselha.CollectionPointProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import com.cs.wasselha.ConfirmAddress;
import com.cs.wasselha.R;

public class AddCollectionPointFragment extends Fragment {

    NumberPicker hoursPickerOpenTime, minutesPickerOpenTime, amPmPickerOpenTime, hoursPickerCloseTime, minutesPickerCloseTime, amPmPickerCloseTime;
    Button addLocationBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_collection_point, container, false);


        //References
        setUpReferences(view);
        addLocationSetUp();

        return view;
    }



    //References
    private void setUpReferences(View view)
    {
        hoursPickerOpenTime = view.findViewById(R.id.numPickerHourInOpenTimeInAddNewCollectionPoint);
        hoursPickerOpenTime.setTextColor(Color.WHITE);
        hoursPickerOpenTime.setMinValue(1);
        hoursPickerOpenTime.setMaxValue(12);

        minutesPickerOpenTime = view.findViewById(R.id.numPickerMinInOpenTimeInAddNewCollectionPoint);
        minutesPickerOpenTime.setTextColor(Color.WHITE);
        minutesPickerOpenTime.setMinValue(0);
        minutesPickerOpenTime.setMaxValue(59);

        amPmPickerOpenTime = view.findViewById(R.id.numPickerAMPMInOpenTimeInAddNewCollectionPoint);
        amPmPickerOpenTime.setTextColor(Color.WHITE);
        amPmPickerOpenTime.setMinValue(0);
        amPmPickerOpenTime.setMaxValue(1);
        amPmPickerOpenTime.setDisplayedValues(new String[] {"AM", "PM"});

        hoursPickerCloseTime = view.findViewById(R.id.numPickerHourInCloseTimeInAddNewCollectionPoint);
        hoursPickerCloseTime.setTextColor(Color.WHITE);
        hoursPickerCloseTime.setMinValue(1);
        hoursPickerCloseTime.setMaxValue(12);

        minutesPickerCloseTime = view.findViewById(R.id.numPickerMinInCloseTimeInAddNewCollectionPoint);
        minutesPickerCloseTime.setTextColor(Color.WHITE);
        minutesPickerCloseTime.setMinValue(0);
        minutesPickerCloseTime.setMaxValue(59);

        amPmPickerCloseTime = view.findViewById(R.id.numPickerAMPMInCloseTimeInAddNewCollectionPoint);
        amPmPickerCloseTime.setTextColor(Color.WHITE);
        amPmPickerCloseTime.setMinValue(0);
        amPmPickerCloseTime.setMaxValue(1);
        amPmPickerCloseTime.setDisplayedValues(new String[] {"AM", "PM"});

        addLocationBtn = view.findViewById(R.id.locationOfCollectionPointBtn);

    }

    private void addLocationSetUp()
    {
        addLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = requireContext();

                Intent intent = new Intent(context, ConfirmAddress.class);

                // Pass any data to the target Activity if needed
                //intent.putExtra("key", "value");

                context.startActivity(intent);
            }
        });
    }


}