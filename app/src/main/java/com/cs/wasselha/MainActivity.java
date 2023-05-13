package com.cs.wasselha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.cs.wasselha.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView searchImg;
    ActivityMainBinding binding;

    //AppCompatButton detailsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        getSupportActionBar().hide();

        binding.bottomBarInCustomerMainPage.setOnItemSelectedListener(item -> {

            switch(item.getItemId())
            {
                case R.id.nav_home:
                    replaceFragment(new HomeFragment());
                    break;

                case R.id.nav_history:
                    replaceFragment(new ReservationsFragment());
                    break;

                case R.id.nav_track:
                    replaceFragment(new TrackingFragment());
                    break;

                case R.id.nav_notifications:
                    replaceFragment(new NotificationsFragment());
                    break;

                case R.id.nav_profile:
                    replaceFragment(new ProfileFragment());
                    break;
            }

            return true;
        });

        //References
        setupReference();
        clickOnSearchImgSetup();
        //detailsBtnSetup();


    }

    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainCustomerLayout,fragment);
        fragmentTransaction.commit();

    }


    //References
    private void setupReference()
    {
        searchImg = findViewById(R.id.searchImg);
        //detailsBtn = findViewById(R.id.detailsBtnInCustomerCardRecyclerView);

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

//    private void detailsBtnSetup()
//    {
//        detailsBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, ServiceDetailsActivity.class);
//                startActivity(intent);
//            }
//        });
//    }

}