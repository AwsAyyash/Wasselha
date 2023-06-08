package com.cs.wasselha;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private TextView signupQuestionBtn, errorMessage;
    private EditText email, password;
    private Button loginBtn;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        //References
        setupReference();
        loginSetup();
        signupQuestionBtnSetup();
    }




    //--------------Methods---------------------------------------------------------

    //References
    private void setupReference()
    {
        signupQuestionBtn = findViewById(R.id.signupQuestionBtn);
        loginBtn = findViewById(R.id.loginBtnInLoginPage);
        email = findViewById(R.id.emailInLoginPage);
        password = findViewById(R.id.passwordInLoginPage);
        errorMessage = findViewById(R.id.errorMessage);

    }

    private void loginSetup() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (email.length() > 0 && password.length() > 0)
                {
                    LoginUser();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Fill all fields, and try again!", Toast.LENGTH_SHORT).show();
                    errorMessage.setText("Fill all fields, and try again!");
                }
            }
        });
    }



    private void LoginUser()
    {
                requestQueue = Volley.newRequestQueue(this);
                String url = "http://176.119.254.198:8000/wasselha/login/customer/";
                final String value = "";

                JSONObject jsonObject = new JSONObject();

                try
                {
                    Log.d("ressss",email.getText().toString());
                    Log.d("ressss",password.getText().toString());
                    jsonObject.put("email", email.getText().toString());
                    jsonObject.put("password", password.getText().toString());
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
                                        Intent intent = new Intent(LoginActivity.this, MainCustomerActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(LoginActivity.this, "Login succeeded!", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(LoginActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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


    private void signupQuestionBtnSetup()
    {
        signupQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(LoginActivity.this, TypeSignupActivity.class);
                startActivity(intent);
            }
        });
    }
}