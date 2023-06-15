package com.cs.wasselha.Transporter;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.JsonArrayRequest;
import com.cs.wasselha.MapLocaterDemoExample;
import com.cs.wasselha.R;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

import com.cs.wasselha.R;
import com.cs.wasselha.RegistrationActivity;
import com.cs.wasselha.TransporterTrackRoad;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileTransporterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileTransporterFragment extends Fragment {

    private static final String ID_KEY = "id";
    private static final String LOGIN_TYPE_KEY = "loginType";
    private static final String PREFERENCES_NAME = "MyPreferences";
    private static String apiURL="http://176.119.254.198:8000/wasselha";
    private ImageView image,settings,claims,cars,favoritos,inbox,logout;
    private TextView name;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileTransporterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileTransporterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileTransporterFragment newInstance(String param1, String param2) {
        ProfileTransporterFragment fragment = new ProfileTransporterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_transporter, container, false);
        setupReference(view);
        SharedPreferences preferences = getActivity().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        String id = preferences.getString(ID_KEY, null);
        int transporterID=Integer.parseInt(id.trim());
        setAndGetName(getContext(),transporterID);
        getVehicleImageURLAndSetImage(getContext(),transporterID);





        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete info from shared preferences
                SharedPreferences preferences = getActivity().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove(ID_KEY);
                editor.remove(LOGIN_TYPE_KEY);
                editor.apply();
                Intent intent = new Intent(getContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });
        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapLocaterDemoExample.class);
                startActivity(intent);
            }
        });

        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapLocaterDemoExample.class);
                startActivity(intent);
            }
        });
        cars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TransporterTrackRoad.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_profile_transporter, container, false);
        return view;
    }


    private void setupReference(View view) {
        image = view.findViewById(R.id.mainPhotoInProfileTransporterPage);
        name = view.findViewById(R.id.mainNameTransporterInProfilePage);
        settings = view.findViewById(R.id.imageView15);
        claims = view.findViewById(R.id.imageView9);
        cars = view.findViewById(R.id.imageView22);
        favoritos = view.findViewById(R.id.imageView20);
        inbox = view.findViewById(R.id.imageView23);
        logout = view.findViewById(R.id.imageView26);
    }
    public void setAndGetName(Context context, int transporterID) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = apiURL + "/transporters/" + transporterID + "/";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String firstName = response.getString("first_name");
                            String lastName = response.getString("last_name");
                            String fullName = firstName + " " + lastName;

                            // Assuming that 'name' is a TextView instance you want to set the name to.
                            name.setText(fullName);

                        } catch (Exception e) {
                            Log.e("profile","Error:"+e.toString());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("profile","Error:"+error.toString());
                // Handle error here
                error.printStackTrace();
            }
        });

        queue.add(jsonObjectRequest);
    }

    public void getVehicleImageURLAndSetImage(Context context, int transporterID) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = apiURL + "/vehicles/?transporter="+transporterID;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Directly loop through the JSON Array response
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject vehicle = response.getJSONObject(i);
                                if (vehicle.getInt("transporter") == transporterID) {
                                    setImage(apiURL + vehicle.getString("vehicle_image"));
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
                Log.e("profile", "Error: " + error.toString());
                if (error.networkResponse != null) {
                    Log.e("profile", "Status code: " + error.networkResponse.statusCode);
                }
                Log.e("profile","failed loading image(Network Issue )");
            }
        });

        queue.add(jsonArrayRequest);
    }
    void setImage(String imageUrl){
        Glide.with(this)
                .load(imageUrl)
                .into(image);
    }

}