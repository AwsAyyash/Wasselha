package com.cs.wasselha.interfaces;

import android.content.Context;

import com.cs.wasselha.model.Customer;

import java.util.ArrayList;

public interface ICustomerDA {

    ArrayList<Customer> getCustomers(Context context);
    Customer getCustomer(int id);
    void saveCustomer(Customer customer);

}
