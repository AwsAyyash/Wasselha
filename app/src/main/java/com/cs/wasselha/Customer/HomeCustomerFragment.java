package com.cs.wasselha.Customer;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cs.wasselha.R;
import com.cs.wasselha.Models.ServicesModel;
import com.cs.wasselha.Adapters.ServicesModelRecyclerViewAdapter;
import com.cs.wasselha.interfaces.implementation.LocationDA;
import com.cs.wasselha.interfaces.implementation.ServiceDA;
import com.cs.wasselha.interfaces.implementation.TransporterDA;
import com.cs.wasselha.model.Service;

import java.io.IOException;
import java.util.ArrayList;

public class HomeCustomerFragment extends Fragment {

    RecyclerView servicesAvailableRecyclerView;
    ArrayList<Service> servicesModelDAList = new ArrayList<>();
    ArrayList<ServicesModel> servicesModelList = new ArrayList<>();

    int imageCard = R.drawable.car1;

    static Context context ;
    public HomeCustomerFragment(Context context2 ) {
         context = context2;
         //this.servicesModelList = servicesModelList;
    }
    public HomeCustomerFragment( ) {

        //this.servicesModelList = servicesModelList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_home_customer, container, false);
        servicesAvailableRecyclerView = view.findViewById(R.id.servicesAvailableRecyclerView);
        try {
            servicesModelDAList = getServiceFromDA();
            Log.d("list222",servicesModelDAList.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            servicesModelSetup();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ServicesModelRecyclerViewAdapter serviceAdapter = new ServicesModelRecyclerViewAdapter(getContext(), servicesModelList);
        servicesAvailableRecyclerView.setAdapter(serviceAdapter);
        servicesAvailableRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    private ArrayList<Service> getServiceFromDA() throws IOException {

        ServiceDA serviceDA = new ServiceDA();
        return serviceDA.getServices();
    }


    private void servicesModelSetup() throws IOException {
        String[] transportersNames = getResources().getStringArray(R.array.services);
        String[] times = getResources().getStringArray(R.array.times);
        String[] sourceCities = getResources().getStringArray(R.array.sourceCities);
        String[] destinationCities = getResources().getStringArray(R.array.destinationCities);


        for(int i = 0 ; i < servicesModelDAList.size() ; i++)
        {
            servicesModelList.add(new ServicesModel(new TransporterDA().getTransporter(servicesModelDAList.get(i).getTransporter()).getFirst_name(),
                    servicesModelDAList.get(i).getTransporter(),
                    servicesModelDAList.get(i).getService_date().toString(),
                    new LocationDA().getLocation(servicesModelDAList.get(i).getSource_place()).getTitle(),
                    new LocationDA().getLocation(servicesModelDAList.get(i).getDestination_place()).getTitle()));
        }

    }

    /*private String getImageUrl(Service service) {


        getVehicleImageURLAndSetImage(context,service.getTransporter().getId());

    }*/

    private static String apiURL="http://176.119.254.198:8000/wasselha";

   /* public void getVehicleImageURLAndSetImage(Context context, int transporterID) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = apiURL + "vehicles/";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Assume the vehicles are inside a JSONArray called "vehicles" in the response
                            JSONArray vehicles = response.getJSONArray("vehicles");
                            for (int i = 0; i < vehicles.length(); i++) {
                                JSONObject vehicle = vehicles.getJSONObject(i);
                                if (vehicle.getInt("transporter") == transporterID) {

                                    setImage(apiURL+vehicle.getString("vehicle_image"));
                                    return;
                                }
                            }
                        } catch (Exception e) {
                            Log.e("profile","Transporter not found");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("profile","failed loading image(Network Issue )");
            }
        });

        queue.add(jsonObjectRequest);
    }*/
    public static void setImage(String imageUrl, ImageView imageView){
        Glide.with(context)
                .load(imageUrl)
                .into(imageView);
    }
}