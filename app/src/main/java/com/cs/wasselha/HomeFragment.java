package com.cs.wasselha;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView servicesAvailableRecyclerView;
    ArrayList<ServicesModel> servicesModelList = new ArrayList<>();
    int imageCard = R.drawable.car1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        servicesModelSetup();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        servicesAvailableRecyclerView = view.findViewById(R.id.servicesAvailableRecyclerView);
        ServicesModelRecyclerViewAdapter serviceAdapter = new ServicesModelRecyclerViewAdapter(getContext(), servicesModelList);
        servicesAvailableRecyclerView.setAdapter(serviceAdapter);
        servicesAvailableRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }


    private void servicesModelSetup()
    {
        String[] transportersNames = getResources().getStringArray(R.array.services);
        String[] times = getResources().getStringArray(R.array.times);
        String[] sourceCities = getResources().getStringArray(R.array.sourceCities);
        String[] destinationCities = getResources().getStringArray(R.array.destinationCities);

        for(int i = 0 ; i < transportersNames.length ; i++)
        {
            servicesModelList.add(new ServicesModel(transportersNames[i], times[i], sourceCities[i], destinationCities[i], imageCard));
        }

    }
}