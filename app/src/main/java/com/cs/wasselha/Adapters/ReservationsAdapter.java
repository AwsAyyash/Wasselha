package com.cs.wasselha.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cs.wasselha.R;
import com.cs.wasselha.Transporter.Reservations;

import java.util.ArrayList;

public class ReservationsAdapter  extends ArrayAdapter<Reservations> {

    private Context context;
    private int cResource;
    ArrayList<Reservations> reservationsData ;

    public ReservationsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Reservations> objects)
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

        TextView customerName = convertView.findViewById(R.id.customerNameInTransporterReservationListview);
        TextView packageType = convertView.findViewById(R.id.PackageTypeInTransporterReservationListview);
        TextView sourceCity = convertView.findViewById(R.id.sourceCityInTransporterReservationListView);
        TextView destinationCity = convertView.findViewById(R.id.destinationCityInTransporterReservationListView);
        TextView time = convertView.findViewById(R.id.timeOfReservationsInTransporterReservationListView);

        customerName.setText(getItem(position).getCustomerName());
        packageType.setText(getItem(position).getPackageType());
        sourceCity.setText(getItem(position).getSourceCity());
        destinationCity.setText(getItem(position).getDestinationCity());
        time.setText(getItem(position).getTime());

        return convertView;
    }
}
