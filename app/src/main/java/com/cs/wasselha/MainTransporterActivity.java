package com.cs.wasselha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.cs.wasselha.databinding.ActivityMainTransporterBinding;
import com.cs.wasselha.interfaces.ICustomerDA;
import com.cs.wasselha.interfaces.implementation.CustomerDA;
import com.cs.wasselha.model.Customer;
import com.cs.wasselha.model.Location;
import com.cs.wasselha.model.Transporter;

import java.io.FileNotFoundException;

public class MainTransporterActivity extends AppCompatActivity {

    ActivityMainTransporterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityMainTransporterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeTransporterFragment());


        binding.bottomBarInTransporterMainPage.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.nav_home_transporter:
                    replaceFragment(new HomeTransporterFragment());
                    break;

                case R.id.nav_history_transporter:
                    replaceFragment(new ReservationsTransporterFragment());
                    break;

                case R.id.nav_add_service_transporter:
                    replaceFragment(new AddServiceTransporterFragment());
                    break;

                case R.id.nav_notifications_transporter:
                    replaceFragment(new NotificationsTransporterFragment());
                    break;

                case R.id.nav_profile_transporter:
                    replaceFragment(new ProfileTransporterFragment());
                    break;
            }

            return true;
        });

        //References
        setupReference();

       /* try {
            Location location = new Location();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }*/

       // new Location();
        //new Customer();
       // new Transporter();

        ICustomerDA customerDA = new CustomerDA();

        Log.d("insideMainTransporter",customerDA.getCustomers(this).toString());
    }

        //References
        private void setupReference()
        {

        }

        private void replaceFragment(Fragment fragment)
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mainTransporterLayout,fragment);
            fragmentTransaction.commit();
        }

    }
