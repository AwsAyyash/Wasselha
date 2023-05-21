package com.cs.wasselha;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class HomeTransporterFragment extends Fragment {

    ListView listView;
    private ArrayList<Services> servicesData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_home_transporter, container, false);
        listView = view.findViewById(R.id.listViewInMainPageTransporter);

        populateServicesData();

        ServicesAdapter servicesAdapter = new ServicesAdapter(requireContext(), R.layout.home_page_transporter_list_view, servicesData);
        listView.setAdapter(servicesAdapter);

        return view;
    }

    private void populateServicesData()
    {
        servicesData = new ArrayList<>();
        servicesData.add(new Services(R.drawable.car1, "Ramallah", "Nablus", "9:00 AM"));
        servicesData.add(new Services(R.drawable.car1, "Nablus", "Jenin", "9:30 AM"));
        servicesData.add(new Services(R.drawable.car1, "Birzeit", "Ramallah", "10:00 AM"));
        servicesData.add(new Services(R.drawable.car1, "Jenin", "Nablus", "10:30 AM"));

    }
}