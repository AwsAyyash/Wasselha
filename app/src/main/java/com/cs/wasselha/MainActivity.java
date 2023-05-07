package com.cs.wasselha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //BottomNavigationView customerBottomNavigationView;
    RecyclerView servicesAvailableRecyclerView;
    ArrayList<ServicesModel> servicesModelList = new ArrayList<>();
    int imageCard = R.drawable.car2;
    ImageView searchImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //References
        setupReference();
        servicesModelSetup();
        clickOnSearchImgSetup();
        //bottomBarSetup();
        servicesAvailableRecyclerView = findViewById(R.id.servicesAvailableRecyclerView);
        ServicesModelRecyclerViewAdapter serviceAdapter = new ServicesModelRecyclerViewAdapter(this, servicesModelList);
        servicesAvailableRecyclerView.setAdapter(serviceAdapter);
        servicesAvailableRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }


    //References
    private void setupReference()
    {
        searchImg = findViewById(R.id.searchImg);

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

    private void clickOnSearchImgSetup()
    {
            searchImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(MainActivity.this, FilterActivity.class);
                    startActivity(intent);
                }
            });

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