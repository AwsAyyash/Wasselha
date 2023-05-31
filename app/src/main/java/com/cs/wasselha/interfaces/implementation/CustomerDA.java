package com.cs.wasselha.interfaces.implementation;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

/*import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;*/
import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cs.wasselha.interfaces.ICustomerDA;
import com.cs.wasselha.model.Customer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class CustomerDA implements ICustomerDA {


    ArrayList<Customer> customerList = new ArrayList<>();
   // FirebaseFirestore db = FirebaseFirestore.getInstance();
    //final String collectionName = "Customer";

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

        ArrayList<Customer> customersList  = new ArrayList<>();
         RequestQueue mRequestQueue;
        // StringRequest mStringRequest;
         String url = "http://176.119.254.198:8000/wasselha/customers/";

        // RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(context);

        // String Request initialized
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>()
         {
            @Override
            public void onResponse(JSONArray response) {


                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        Gson gson = new Gson();
                        Customer customer = gson.fromJson(obj.toString(), Customer.class);
                        customersList.add(customer);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }

                //customersList.add((Customer) obj);
                //Toast.makeText(getApplicationContext(), "Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
                Log.d("customersList",customersList.toString());
                //return customersList;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "Error :" + error.toString());
            }
        });

        mRequestQueue.add(request);

        return customersList;

       // return null;
    }



    @Override
    public Customer getCustomer(int id) {

       /* DocumentReference docRef = db.collection(collectionName).document(customer.getDocumentId());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Customer customer1 = documentSnapshot.toObject(Customer.class);
            }
        });*/

       // getAll();
        //for (Customer customer : customerList) {
          ///  if (customer.getId() == id) {
             ///   return user;
         //   }

       // }
        return null; // this means, my userName (USER) does not exist -Not registered-




    }

    @Override
    public void saveCustomer(Customer customer) {

    }


   /* private void getAll(){

        db.collection(collectionName).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {


                        if (queryDocumentSnapshots.isEmpty()) {
                            Log.d(ContentValues.TAG, "onSuccess: LIST EMPTY");
                        } else {
                            // Convert the whole Query Snapshot to a list
                            // of objects directly! No need to fetch each
                            // document.
                            customerList.clear();
                            customerList.addAll(queryDocumentSnapshots.toObjects(Customer.class));

                            //Log.d(TAG, "onSuccess: " + d);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.e("gettingAllUsers", e.getMessage());
                    }
                });
    }
    @Override
    public void saveCustomer(Customer customer) {

        db.collection(collectionName).add(customer).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                String id = documentReference.getId();

                db.collection(collectionName).document(id).update("documentId", id);
                customer.setDocumentId(id);
            }
        });
    }*/
}
