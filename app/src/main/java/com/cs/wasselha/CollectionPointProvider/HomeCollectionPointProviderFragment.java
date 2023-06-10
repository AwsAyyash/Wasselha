package com.cs.wasselha.CollectionPointProvider;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.cs.wasselha.Adapters.CollectionPointsAdapter;
import com.cs.wasselha.R;

import java.util.ArrayList;

public class HomeCollectionPointProviderFragment extends Fragment {

    ListView listView;
    Button btn;
    private ArrayList<CollectionPoints> collectionPointsData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_home_collection_point_provider, container, false);
        listView = view.findViewById(R.id.listViewInMainPageCollectionPointProvider);

        populateServicesData();

        CollectionPointsAdapter collectionPointsAdapter = new CollectionPointsAdapter(requireContext(), R.layout.home_page_collection_point_provider_list_view, collectionPointsData);
        listView.setAdapter(collectionPointsAdapter);

        return view;
    }

    private void populateServicesData()
    {
        collectionPointsData = new ArrayList<>();



        collectionPointsData.add(new CollectionPoints(R.drawable.collection_point_img, "Ramallah", btn));
        collectionPointsData.add(new CollectionPoints(R.drawable.collection_point_img, "Nablus", btn));
        collectionPointsData.add(new CollectionPoints(R.drawable.collection_point_img, "Birzeit", btn));

    }
}