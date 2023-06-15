package com.cs.wasselha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import android.Manifest;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.List;

public class TransporterTrackRoad extends AppCompatActivity implements OnMapReadyCallback {

    private static final String ID_KEY = "id";
    private static final String LOGIN_TYPE_KEY = "loginType";
    private static final String PREFERENCES_NAME = "MyPreferences";
    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final long UPDATE_INTERVAL = 5000; // 5 seconds
    private static final long FASTEST_INTERVAL = 2000; // 2 seconds
    private List<LatLng> customerLocations = new ArrayList<>();
    private List<LatLng> collectionPointsLocations = new ArrayList<>();
    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Marker transporterMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transporter_track_road);
        getSupportActionBar().hide();
        SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        String id = preferences.getString(ID_KEY, null);
        int transporterID=Integer.parseInt(id.trim());
        addCustomersLocations();
        addCollectionPointLocations();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }

    private void addCollectionPointLocations() {
        collectionPointsLocations.add(new LatLng(31.916177, 35.127968)); // deir ibze
        collectionPointsLocations.add(new LatLng(31.930065, 35.074651)); // billin
        collectionPointsLocations.add(new LatLng(31.907012, 35.061517)); // safa
    }

    private void addCustomersLocations() {
        customerLocations.add(new LatLng(31.927892, 35.093573)); // last of kufer nema
        customerLocations.add(new LatLng(31.920901, 35.107598)); // between kufer nema and deir ibze
        customerLocations.add(new LatLng(31.914855, 35.127968)); // first of deir ibze
        customerLocations.add(new LatLng(31.917217, 35.116280)); // last of deir ibze
    }

    private double calculateDistance(LatLng point1, LatLng point2) {
        Location location1 = new Location("");
        location1.setLatitude(point1.latitude);
        location1.setLongitude(point1.longitude);

        Location location2 = new Location("");
        location2.setLatitude(point2.latitude);
        location2.setLongitude(point2.longitude);

        return location1.distanceTo(location2);
    }
    private void addCustomerAndCollectionPointsMarkers() {
        float scalingFactor = 0.25f; // Adjust the scaling factor as needed
        float visibilityRadius = 5000; // Visibility radius in meters (5km)

        // Load smaller-sized icons for customers and collection points
        Bitmap customerBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_person);
        Bitmap collectionPointBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_collectionpoint);

        int scaledWidth = (int) (customerBitmap.getWidth() * scalingFactor);
        int scaledHeight = (int) (customerBitmap.getHeight() * scalingFactor);

        Bitmap scaledCustomerBitmap = Bitmap.createScaledBitmap(customerBitmap, scaledWidth, scaledHeight, false);
        Bitmap scaledCollectionPointBitmap = Bitmap.createScaledBitmap(collectionPointBitmap, scaledWidth, scaledHeight, false);

        // Recycle the original bitmaps to free up memory
        customerBitmap.recycle();
        collectionPointBitmap.recycle();

        LatLng transporterLocation = transporterMarker.getPosition();// Replace with actual transporter location

        for (LatLng customerLocation : customerLocations) {
            double distance = calculateDistance(transporterLocation, customerLocation);
            if (distance <= visibilityRadius) {
                mMap.addMarker(new MarkerOptions()
                        .position(customerLocation)
                        .icon(BitmapDescriptorFactory.fromBitmap(scaledCustomerBitmap))
                        .title("Customer"));
            }
        }

        for (LatLng collectionPointLocation : collectionPointsLocations) {
            double distance = calculateDistance(transporterLocation, collectionPointLocation);
            if (distance <= visibilityRadius) {
                mMap.addMarker(new MarkerOptions()
                        .position(collectionPointLocation)
                        .icon(BitmapDescriptorFactory.fromBitmap(scaledCollectionPointBitmap))
                        .title("CollectionPoint"));
            }
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Check location permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); // Show satellite layer
            if (isNetworkConnected()) {
                startLocationUpdates();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        addCustomerAndCollectionPointsMarkers();
                    }
                }, 2000);
            } else {
                Toast.makeText(TransporterTrackRoad.this, "No internet connection. Please check your network settings.", Toast.LENGTH_SHORT).show();
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                    startLocationUpdates();
                }
            } else {
                Toast.makeText(this, "Location permission denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(UPDATE_INTERVAL);
        locationRequest.setFastestInterval(FASTEST_INTERVAL);

        // Check location permission again before requesting updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    if (locationResult != null) {
                        Location location = locationResult.getLastLocation();
                        if (location != null) {
                            updateTransporterLocation(location);
                        }
                    }
                }
            }, null);
        }
    }

    private void updateTransporterLocation(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        if (transporterMarker == null) {
            RequestOptions requestOptions = new RequestOptions()
                    .override(100, 100) // Adjust the desired size here
                    .circleCrop();

            Glide.with(this)
                    .asBitmap()
                    .load(R.drawable.ic_car_map)
                    .apply(requestOptions)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            transporterMarker = mMap.addMarker(new MarkerOptions()
                                    .position(latLng)
                                    .icon(BitmapDescriptorFactory.fromBitmap(resource))
                                    .anchor(0.5f, 0.5f)
                                    .title("Transporter"));
                        }
                    });
        } else {
            transporterMarker.setPosition(latLng);
        }

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f));
    }
    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}