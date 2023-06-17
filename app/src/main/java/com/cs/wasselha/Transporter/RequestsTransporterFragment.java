package com.cs.wasselha.Transporter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cs.wasselha.Adapters.RequestsAdapter;
import com.cs.wasselha.R;

import java.util.ArrayList;

public class RequestsTransporterFragment extends Fragment {

    ListView listView;
    private ArrayList<Requests> requestsData;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_requests_transporter, container, false);
        listView = view.findViewById(R.id.listViewInRequestsTransporterFragment);

        populateServicesData();

        RequestsAdapter requestsAdapter = new RequestsAdapter(requireContext(), R.layout.requests_list_view, requestsData);
        listView.setAdapter(requestsAdapter);

        return view;
    }


    private void populateServicesData()
    {
        requestsData = new ArrayList<>();

        requestsData.add(new Requests("Not available!", "Not available!","Not available!", "Not available!", "Not available!", "Not available!"));
        requestsData.add(new Requests("Not available!", "Not available!","Not available!", "Not available!", "Not available!", "Not available!"));
        requestsData.add(new Requests("Not available!", "Not available!","Not available!", "Not available!", "Not available!", "Not available!"));

    }
}