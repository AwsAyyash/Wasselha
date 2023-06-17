package com.cs.wasselha.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.cs.wasselha.Claims.Claims;
import com.cs.wasselha.R;
import com.cs.wasselha.Transporter.Requests;


import java.util.ArrayList;

public class RequestsAdapter extends ArrayAdapter<Requests>
{

    private Context context;
    private int cResource;
    ArrayList<Requests> requestsData ;

    public RequestsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Requests> objects)
    {
        super(context, resource, objects);
        this.context = context;
        this.cResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater layoutInFlater = LayoutInflater.from(context);
        convertView = layoutInFlater.inflate(cResource, parent, false);

        TextView customerName = convertView.findViewById(R.id.customerNameInRequestsListview);
        TextView customerReview = convertView.findViewById(R.id.customerReviewInRequestsListview);
        TextView sourceCity = convertView.findViewById(R.id.sourceCityInRequestsListView);
        TextView destinationCity = convertView.findViewById(R.id.destinationCityInInRequestsListView);
        TextView price = convertView.findViewById(R.id.priceInRequestsListview);
        TextView time = convertView.findViewById(R.id.timeOfServiceInListViewMainPageTransporter);

        customerName.setText(getItem(position).getCustomerName());
        customerReview.setText(getItem(position).getCustomerReview());
        sourceCity.setText(getItem(position).getSourceCity());
        destinationCity.setText(getItem(position).getDestinationCity());
        price.setText(getItem(position).getPrice());
        time.setText(getItem(position).getTime());

        return convertView;
    }
}
