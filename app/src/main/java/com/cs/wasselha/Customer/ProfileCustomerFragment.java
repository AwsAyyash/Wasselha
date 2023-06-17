package com.cs.wasselha.Customer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cs.wasselha.R;

public class ProfileCustomerFragment extends Fragment
{
    ImageView settingImg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_customer, container, false);
        setupReferences(view);

        //Calls
        settingImgSetup();

        return view;
    }


    //-----------Methods---------------------------------------------------------------

    //References
    private void setupReferences(View view)
    {
        settingImg = view.findViewById(R.id.settingImageInTransporterProfile);
    }


    private void settingImgSetup()
    {
        settingImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CustomerSettingActivity.class);
                startActivity(intent);
            }
        });
    }
}