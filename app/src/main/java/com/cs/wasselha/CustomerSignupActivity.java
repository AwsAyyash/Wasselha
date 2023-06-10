package com.cs.wasselha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
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
                            addCustomer(email.getText().toString(), firstName.getText().toString(),lastName.getText().toString(), password.getText().toString(), phoneNumber.getText().toString(), true,"3",  address.getText().toString());
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


    private void addCustomer(String email, String firstName, String lastName, String password, String phoneNumber, boolean isVerified, String review, String location) {
        String url = "http://176.119.254.198:8000/wasselha/customers/";
        RequestQueue queue = Volley.newRequestQueue(CustomerSignupActivity.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                {
                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.getString("error").equalsIgnoreCase("true"))
                    {
                        errorMessage.setText("Eamil or Phone number is already exist!");
                        Toast.makeText(CustomerSignupActivity.this, "Eamil or Phone number is already exist!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
//                        editor.putString(LOGIN, "true");
//                        editor.putString(USERNAME, username);
//                        editor.putString(FULLNAME, fullName);
//                        editor.putString(EMAIL, email);
//                        editor.commit();
                        Toast.makeText(CustomerSignupActivity.this, "Add customer succeeded!", Toast.LENGTH_SHORT).show();
//                        if (nextActivity.equalsIgnoreCase("main")) {
//                            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
//                            startActivity(intent);
//                        } else {
//                            Intent intent = new Intent(SignupActivity.this, FixedIncomeInfoActivity.class);
//                            startActivity(intent);
//                        }
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(CustomerSignupActivity.this, "" + error, Toast.LENGTH_SHORT).show();
                errorMessage.setText(error.toString());
            }
        }) {
            @Override
            public String getBodyContentType()
            {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("email", email);
                params.put("first_name", firstName);
                params.put("last_name", lastName);
                params.put("password", password);
                params.put("phone_number", phoneNumber);
                params.put("isVerified", String.valueOf(isVerified));
                params.put("review", review);
                params.put("location", location);
                return params;
            }
        };
        queue.add(request);
    }





}