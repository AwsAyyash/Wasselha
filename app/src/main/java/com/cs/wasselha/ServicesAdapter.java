package com.cs.wasselha;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ServicesAdapter extends ArrayAdapter<Services>
{
    private Context mContext;
    private int mResource;

    public ServicesAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Services> objects)
    {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater layoutInFlater = LayoutInflater.from(mContext);
        convertView = layoutInFlater.inflate(mResource, parent, false);

        ImageView imageView = convertView.findViewById(R.id.imageInListViewMainPageTransporter);
        TextView sourceCity = convertView.findViewById(R.id.sourceCityInListViewMainPageTransporter);
        TextView destinationCity = convertView.findViewById(R.id.destinationCityInListViewMainPageTransporter);
        TextView time = convertView.findViewById(R.id.timeOfServiceInListViewMainPageTransporter);

        imageView.setImageResource(getItem(position).getImage());
        sourceCity.setText(getItem(position).getSourceCity());
        destinationCity.setText(getItem(position).getDestinationCity());
        time.setText(getItem(position).getTime());
        return convertView;
    }
}
