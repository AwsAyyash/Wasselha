package com.cs.wasselha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.cs.wasselha.CollectionPointProvider.MainCollectionPointProviderActivity;
import com.cs.wasselha.Customer.MainCustomerActivity;
import com.cs.wasselha.Transporter.MainTransporterActivity;

public class SplashActivity extends AppCompatActivity {

    private static final String ID_KEY = "id";
    private static final String LOGIN_TYPE_KEY = "loginType";
    private static final String PREFERENCES_NAME = "MyPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);
            getSupportActionBar().hide();

            SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
            // SharedPreferences.Editor editor = preferences.edit();
            String loginType = preferences.getString(LOGIN_TYPE_KEY, null);
            String id = preferences.getString(ID_KEY, null);

            if (loginType != null && id != null)
            {
                if (loginType.equals("customer"))
                {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run()
                        {
                            Intent intent = new Intent(SplashActivity.this, MainCustomerActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 1500);

                }
                else if (loginType.equals("transporter"))
                {
                    Handler handler = new Handler();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run()
                        {
                            Intent intent = new Intent(SplashActivity.this, MainTransporterActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 1500);

                }
                else if (loginType.equals("collectionpointprovider"))
                {
                    Handler handler = new Handler();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run()
                        {
                            Intent intent = new Intent(SplashActivity.this, MainCollectionPointProviderActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 1500);
                }
            }
            else
            {
                buildSplash();
            }
        }
         catch (Exception e)
        {
            Log.e("error:",e.toString());
        }


    }


    //Build Splash
    private void buildSplash() {
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }
}