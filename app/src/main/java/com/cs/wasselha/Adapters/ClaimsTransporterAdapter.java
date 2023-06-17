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
import com.cs.wasselha.model.Claim;

import java.util.ArrayList;

public class ClaimsTransporterAdapter extends ArrayAdapter<Claims> {

private Context context;
private int cResource;
        ArrayList<Claim> claimsDACustomerData ;

public ClaimsTransporterAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Claims> objects,
                                ArrayList<Claim> claimsDACustomerData)
        {
        super(context, resource, objects);
        this.context = context;
        this.cResource = resource;
        this.claimsDACustomerData = claimsDACustomerData;
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

        TextView from = convertView.findViewById(R.id.fromInClaimListView);

        claimImg.setImageResource(R.drawable.ic_claim);
        review.setText(getItem(position).getReview());
        massage.setText(getItem(position).getMessage());
        date.setText(getItem(position).getDate());
        from.setText(getItem(position).getSentFrom());
        return convertView;
        }
}
