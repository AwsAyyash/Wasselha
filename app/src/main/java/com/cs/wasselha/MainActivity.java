package com.cs.wasselha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //BottomNavigationView customerBottomNavigationView;
    RecyclerView servicesAvailableRecyclerView;
    ArrayList<ServicesModel> servicesModelList = new ArrayList<>();
    int imageCard = R.drawable.car1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //References
        //setupReference();
        servicesModelSetup();
        //bottomBarSetup();
        servicesAvailableRecyclerView = findViewById(R.id.servicesAvailableRecyclerView);
        ServicesModelRecyclerViewAdapter serviceAdapter = new ServicesModelRecyclerViewAdapter(this, servicesModelList);
        servicesAvailableRecyclerView.setAdapter(serviceAdapter);
        servicesAvailableRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }


    //References
    private void setupReference()
    {

        //customerBottomNavigationView = findViewById(R.id.customer_bottom_bar);
        //getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new MainActivity()).commit();
        // customerBottomNavigationView.setSelectedItemId(R.id.nav_home);
    }

    private void servicesModelSetup()
    {
        String[] transportersNames = getResources().getStringArray(R.array.services);
        String[] times = getResources().getStringArray(R.array.times);
        String[] sourceCities = getResources().getStringArray(R.array.sourceCities);
        String[] destinationCities = getResources().getStringArray(R.array.destinationCities);

        for(int i = 0 ; i < transportersNames.length ; i++)
        {
            servicesModelList.add(new ServicesModel(transportersNames[i], times[i], sourceCities[i], destinationCities[i], imageCard));
        }

    }



//    private void bottomBarSetup()
//    {
//        customerBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment fragment = null;
//
//                switch(item.getItemId())
//                {
//                    case R.id.nav_home:
//                        //fragment = new MainActivity();
//                        break;
//
//                    case R.id.nav_history:
//                        //fragment = new ReservastionHistoryActivity();
//                        Toast.makeText(MainActivity.this, "Not available now!", Toast.LENGTH_SHORT).show();
//                        break;
//
//                    case R.id.nav_track:
//                        //fragment = new TrackingActivity();
//                        Toast.makeText(MainActivity.this, "Not available now!!", Toast.LENGTH_SHORT).show();
//                        break;
//
//                    case R.id.nav_profile:
//                        //fragment = new ProfileActivity();
//                        Toast.makeText(MainActivity.this, "Not available now!!!", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//
//                return true;
//            }
//        });
//    }
}