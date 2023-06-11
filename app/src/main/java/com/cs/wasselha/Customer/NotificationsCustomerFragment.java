package com.cs.wasselha.Customer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cs.wasselha.Adapters.NotificationsCustomerAdapter;
import com.cs.wasselha.R;

import java.util.ArrayList;

public class NotificationsCustomerFragment extends Fragment {


    ListView notificationsListView;
    private ArrayList<Notifications> notificationsCustomerData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications_customer, container, false);
        notificationsListView = view.findViewById(R.id.listViewCustomerNotifications);

        populateNotificationsData();

        NotificationsCustomerAdapter notificationsCustomerAdapter = new NotificationsCustomerAdapter(requireContext(), R.layout.notifications_customer_list_view, notificationsCustomerData);
        notificationsListView.setAdapter(notificationsCustomerAdapter);

        return view;
    }


    private void populateNotificationsData()
    {
        notificationsCustomerData = new ArrayList<>();

        notificationsCustomerData.add(new Notifications(R.drawable.notification, "Notification", "Reservation has been accepted", "11:30 PM"));
        notificationsCustomerData.add(new Notifications(R.drawable.notification, "Notification", "Reservation has not been accepted", "10:30 PM"));
        notificationsCustomerData.add(new Notifications(R.drawable.notification, "Notification", "Reservation has been accepted", "9:00 PM"));
    }
}