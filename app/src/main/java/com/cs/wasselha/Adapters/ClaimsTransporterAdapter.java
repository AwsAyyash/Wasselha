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

import com.cs.wasselha.Claims.Claims;
import com.cs.wasselha.R;

import java.util.ArrayList;

public class ClaimsTransporterAdapter extends ArrayAdapter<Claims> {

private Context context;
private int cResource;

public ClaimsTransporterAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Claims> objects)
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

        ImageView claimImg = convertView.findViewById(R.id.imageInClaimsListView);
        TextView review = convertView.findViewById(R.id.reviewInClaimListView);
        TextView massage = convertView.findViewById(R.id.messageInClaimListView);
        TextView date = convertView.findViewById(R.id.dateInClaimListView);

        claimImg.setImageResource(R.drawable.ic_claim);
        review.setText(getItem(position).getReview());
        massage.setText(getItem(position).getMessage());
        date.setText(getItem(position).getDate());

        return convertView;
        }
}