package com.cs.wasselha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.cs.wasselha.databinding.ActivityMainTransporterBinding;
//import com.cs.wasselha.interfaces.ICustomerDA;
//import com.cs.wasselha.interfaces.implementation.CustomerDA;
import com.cs.wasselha.interfaces.implementation.CollectionPPDA;
import com.cs.wasselha.interfaces.implementation.LocationDA;
import com.cs.wasselha.interfaces.implementation.ServiceDA;
import com.cs.wasselha.interfaces.implementation.TransporterDA;
import com.cs.wasselha.model.CollectionPointProvider;
import com.cs.wasselha.model.Customer;
import com.cs.wasselha.model.Location;
import com.cs.wasselha.model.Service;
import com.cs.wasselha.model.Transporter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

        //List<Customer> customers = new ArrayList<>();
       // CustomerDA customerDA = new CustomerDA();
        //try {

            //Log.d("ArrList",customerDA.getCustomerArrList().toString());
          //  Log.d("customer26",customerDA.getCustomer(26).toString());

      //  } catch (IOException e) {
        //    throw new RuntimeException(e);
        //}

        //customerDA.saveCustomer();

        //customerDA.getCustomers(this,customers);
        //Customer customer = new Customer(customers);

        //Log.d("insideMainTransporter",customers.toString());


        //ICustomerDA customerDA2 = new CustomerDA();
        /*for (int j=0;j< customers.size();j++){

            customerDA.saveCustomer(this,customers.get(j));
        }*/

        //customerDA.getCustomers(this,customers);
        //customerDA.getCustomer(this,26,customers);
       /* try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/

       // Log.d("customer26",customers.get(0).toString());

        /*Transporter transporter = new Transporter();

        List<Transporter> transporterList  = transporter.getTransporters();
        TransporterDA transporterDA = new TransporterDA();

        for (Transporter t : transporterList) {

            try {
                transporterDA.saveTransporter(t);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }*/

        /*Location location = new Location();

        List<Location> locationList  = location.getLocations();
        LocationDA locationDA = new LocationDA();

        for (Location l : locationList) {

            try {
                locationDA.saveLocation(l);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }*/



        // -----------------

        /*CollectionPointProvider cpp = new CollectionPointProvider();

        List<CollectionPointProvider> cppList  = cpp.getCollectionPointProviders();
        CollectionPPDA cppDA = new CollectionPPDA();

        for (CollectionPointProvider cc : cppList) {

            try {
                cppDA.saveCollectionPP(cc);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }*/
        // -----------------



        // -----------------

        // todo do it, when salah add this url
       /* Service service = null;
        try {
            service = new Service();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Service> servicesL  = service.getServices();
        ServiceDA sDA = new ServiceDA();

        for (Service s : servicesL) {

            try {
                sDA.saveService(s);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }*/
        // -----------------

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
