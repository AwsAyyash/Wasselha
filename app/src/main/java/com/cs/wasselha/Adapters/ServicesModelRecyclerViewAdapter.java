package com.cs.wasselha.Adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cs.wasselha.R;
import com.cs.wasselha.Customer.ServiceDetailsActivity;
import com.cs.wasselha.Models.ServicesModel;

import java.util.ArrayList;

public class ServicesModelRecyclerViewAdapter extends RecyclerView.Adapter<ServicesModelRecyclerViewAdapter.MyViewHolder>
{
    Context context;
    ArrayList<ServicesModel> servicesModelList;

    public ServicesModelRecyclerViewAdapter(Context context, ArrayList<ServicesModel> servicesModelList)
    {
        this.context = context;
        this.servicesModelList = servicesModelList;
    }

    @NonNull
    @Override
    public ServicesModelRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent,false);
        return new ServicesModelRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesModelRecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.transporterName.setText(servicesModelList.get(position).getTransporterName());
        holder.time.setText(servicesModelList.get(position).getTime());
        holder.sourceCity.setText(servicesModelList.get(position).getSourceCity());
        holder.destinationCity.setText(servicesModelList.get(position).getDestinationCity());
        holder.cardImageView.setImageResource(servicesModelList.get(position).getImage());

        Button detailsBtn = holder.itemView.findViewById(R.id.detailsBtnInCustomerCardRecyclerView);
        detailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ServiceDetailsActivity.class);

                // Pass any extra data if needed
//                intent.putExtra("key", value);

                // Start the activity
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return servicesModelList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView cardImageView;
        TextView transporterName, time, sourceCity, destinationCity;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cardImageView = itemView.findViewById(R.id.cardImageView);
            transporterName = itemView.findViewById(R.id.transporterNameInCustomerRecyclerView);
            time = itemView.findViewById(R.id.timeInCustomerRecyclerView);
            sourceCity = itemView.findViewById(R.id.sourceCity);
            destinationCity = itemView.findViewById(R.id.destinationCity);

        }
    }
}