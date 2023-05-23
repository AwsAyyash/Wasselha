package com.cs.wasselha.interfaces.implementation;

import android.content.Context;
import android.util.Log;

/*import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;*/
import com.cs.wasselha.interfaces.ICustomerDA;
import com.cs.wasselha.model.Customer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class CustomerDA implements ICustomerDA {


    @Override
    public ArrayList<Customer> getCustomers(Context context) {

        /*RequestQueue queue =  Volley.newRequestQueue(context);;
        //List<Customer> customersList;
        String url = "http://127.0.0.1:8000/wasselha/customers/?format=json";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                String msg = "";
                List<Customer> customersList = new ArrayList<>();
                for (int i = 0; i < 14; i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        //customersList.add((Customer) obj);

                    } catch (JSONException exception) {
                        Log.d("Error", exception.toString());
                    }
                }

               // putDataInListView();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("error_req", error.getMessage().toString());
            }
        });

        queue.add(request);

*/
        return null;
    }



    @Override
    public Customer getCustomer(int id) {
        return null;
    }

    @Override
    public void saveCustomer(Customer customer) {

    }
}
