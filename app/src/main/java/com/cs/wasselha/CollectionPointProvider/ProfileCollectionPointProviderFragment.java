package com.cs.wasselha.CollectionPointProvider;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cs.wasselha.R;


public class ProfileCollectionPointProviderFragment extends Fragment {


    private ImageView settingCollectionPointProviderImg, ReviewsCollectionPointProviderImg, logoutCollectionPointProviderImg;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try
        {
            View view = inflater.inflate(R.layout.fragment_profile_transporter, container, false);
            setupReference(view);

            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }

            }, 1000);

            //Calls
            settingCollectionPointProviderSetup();
            reviewsCollectionPointProviderSetup();

            return view;

        }
        catch (Exception e)
        {
            Log.e("error:",e.toString());
            View view = inflater.inflate(R.layout.fragment_profile_transporter, container, false);
            return view;
        }
    }


    //-----------Methods-------------------------------------------------------
    private void setupReference(View view)
    {
        settingCollectionPointProviderImg = view.findViewById(R.id.settingImageInCollectionPointProviderProfile);
        ReviewsCollectionPointProviderImg = view.findViewById(R.id.ReviewsImageInCollectionPointProviderProfile);
        logoutCollectionPointProviderImg = view.findViewById(R.id.logoutImageInCollectionPointProviderProfile);
    }

    private void settingCollectionPointProviderSetup()
    {
        settingCollectionPointProviderImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getContext(), CollectionPointProviderSettingsActivity.class);
                startActivity(intent);
            }
        });
    }


    private void reviewsCollectionPointProviderSetup()
    {
        ReviewsCollectionPointProviderImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getContext(), CollectionPointProviderReviewsActivity.class);
                startActivity(intent);
            }
        });
    }
}