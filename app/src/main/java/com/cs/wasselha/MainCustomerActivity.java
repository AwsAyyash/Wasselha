package com.cs.wasselha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.cs.wasselha.databinding.ActivityMainCustomerBinding;

public class MainCustomerActivity extends AppCompatActivity {

    ImageView searchImg;
    ActivityMainCustomerBinding binding;

    //AppCompatButton detailsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityMainCustomerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeCustomerFragment());
        getSupportActionBar().hide();

        binding.bottomBarInCustomerMainPage.setOnItemSelectedListener(item -> {

            switch(item.getItemId())
            {
                case R.id.nav_home_transporter:
                    replaceFragment(new HomeCustomerFragment());
                    break;

                case R.id.nav_history_transporter:
                    replaceFragment(new ReservationsCustomerFragment());
                    break;

                case R.id.nav_track:
                    replaceFragment(new TrackingCustomerFragment());
                    break;

                case R.id.nav_notifications_transporter:
                    replaceFragment(new NotificationsCustomerFragment());
                    break;

                case R.id.nav_profile_transporter:
                    replaceFragment(new ProfileCustomerFragment());
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
                    Intent intent = new Intent(MainCustomerActivity.this, FilterActivity.class);
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