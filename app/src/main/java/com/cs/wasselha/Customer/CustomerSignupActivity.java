package com.cs.wasselha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CustomerSignupActivity extends AppCompatActivity {

    Button signupBtnInCustomerSignupPage;
    EditText firstName, lastName,email, phoneNumber, address, password, repeatPassword;
    TextView errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_signup);
        getSupportActionBar().hide();

        //References
        setupReference();
        customerSignupSetup();


    }


    //--------------Methods---------------------------------------------------------
    //References
    private void setupReference()
    {
        signupBtnInCustomerSignupPage = findViewById(R.id.signupCustomerBtn);
        firstName = findViewById(R.id.firstNameCustomer);
        lastName = findViewById(R.id.lastNameCustomer);
        email = findViewById(R.id.emailCustomerSignup);
        phoneNumber = findViewById(R.id.phoneNumberCustomerSignup);
        address = findViewById(R.id.addressCustomerSignup);
        password = findViewById(R.id.passwordCustomerSignup);
        repeatPassword = findViewById(R.id.repeatPasswordCustomerSignup);
        errorMessage = findViewById(R.id.errorMessageInCustomerSignup);
    }


    private void customerSignupSetup()
    {
        signupBtnInCustomerSignupPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                if (email.length() > 0 && firstName.length() > 0 && lastName.length() > 0 &&  password.length() > 0 && phoneNumber.length() > 0 && address.length() > 0 && repeatPassword.length() > 0)
                {
                    if (password.getText().length() > 8 && repeatPassword.getText().length() > 8)
                    {
                        if(password.getText().toString().equals(repeatPassword.getText().toString()))
                        {
                            errorMessage.setText("");
                            addCustomer();
                            //addCustomer(email.getText().toString(), firstName.getText().toString(),lastName.getText().toString(), password.getText().toString(), phoneNumber.getText().toString(), true,"3",  address.getText().toString());
                        }
                        else
                        {
                            errorMessage.setText("Passwords is not match!");
                        }
                    }
                    else
                    {
                        errorMessage.setText("Password must contain more than 8 characters!");
                    }

                }
                else
                {
                    Toast.makeText(CustomerSignupActivity.this, "Fill all fields, and try again!", Toast.LENGTH_SHORT).show();
                    errorMessage.setText("Fill all fields, and try again!");
                }
            }
        });
    }


    private void addCustomer()
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://176.119.254.198:8000/wasselha/customers/";

        JSONObject jsonObject = new JSONObject();

        try
        {
            jsonObject.put("email", email.getText().toString());
            jsonObject.put("first_name", firstName.getText().toString());
            jsonObject.put("last_name", lastName.getText().toString());
            jsonObject.put("password", password.getText().toString());
            jsonObject.put("phone_number", phoneNumber.getText().toString());
            jsonObject.put("is_verified", true);
            jsonObject.put("review", 3);
            jsonObject.put("location", 1);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        // Create the POST request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            Log.d("ressss",response.toString());
                            String result = response.getString("result");
                            Log.d("ressss",result);

                            if (result.equals("true"))
                            {
                                Intent intent = new Intent(CustomerSignupActivity.this, RegistrationActivity.class);
                                startActivity(intent);
                                Toast.makeText(CustomerSignupActivity.this, "Test"+result, Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                errorMessage.setText("The information is not correct, try again!");                                    }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(CustomerSignupActivity.this, "Email or phone number already exists!", Toast.LENGTH_SHORT).show();
                        errorMessage.setText("Email or phone number already exists!");
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        // Add the request to the RequestQueue
        requestQueue.add(jsonObjectRequest);
    }





}