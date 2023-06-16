package com.cs.wasselha.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cs.wasselha.Customer.Notifications;
import com.cs.wasselha.R;

import java.util.ArrayList;

public class NotificationsTransporterAdapter extends ArrayAdapter<Notifications>
{
    private Context context;
    private int cResource;

    public NotificationsTransporterAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Notifications> objects)
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

        ImageView imageView = convertView.findViewById(R.id.imageInClaimsListView);
        TextView titleNotification = convertView.findViewById(R.id.titleNotificationTransporter);
        TextView descriptionNotification = convertView.findViewById(R.id.messageInClaimListView);
        TextView timeNotification = convertView.findViewById(R.id.timeNotificationTransporter);

        imageView.setImageResource(R.drawable.notification);
        titleNotification.setText(getItem(position).getTitleNotification());
        descriptionNotification.setText(getItem(position).getDescriptionNotification());
        timeNotification.setText(getItem(position).getNotificationDate()+" ,\t"+getItem(position).getNotificationTime());


        return convertView;
    }
}
