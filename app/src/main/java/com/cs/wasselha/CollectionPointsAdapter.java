package com.cs.wasselha;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CollectionPointsAdapter extends ArrayAdapter<CollectionPoints> {

    private Context mContext;
    private int mResource;

    public CollectionPointsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<CollectionPoints> objects)
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

        ImageView imageView = convertView.findViewById(R.id.imageInListViewMainPageCollectionPointProvider);
        TextView nameCollectionPoint = convertView.findViewById(R.id.nameCollectionPointInListViewMainPageCollectionPointProvider);
        Button manageBtn = convertView.findViewById(R.id.manageBtnInListViewMainPageCollectionPointProvider);


        imageView.setImageResource(getItem(position).getImage());
        nameCollectionPoint.setText(getItem(position).getCollectionPointName());
//        manageBtn.setBackgroundResource(R.drawable.button_background);
        return convertView;
    }
}
