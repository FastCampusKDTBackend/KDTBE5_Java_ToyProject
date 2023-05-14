package com.smartstore.function.customer.view;

import com.smartstore.customer.Customer;
import com.smartstore.customer.Customers;
import com.smartstore.function.*;

public class ViewCustomerById implements ValueWithEndValidator, Handleable {

    private static ViewCustomerById instance;

    private ViewCustomerById(){

    }

    public static ViewCustomerById getInstance() {
        if(instance == null){
            return new ViewCustomerById();
        }
        return instance;
    }

    @Override
    public void run() {
        Customers customers = Customers.getInstance();
        Customer customer = null;
        String value = getValueOrEnd("Type User Id to Find | 'end' to cancel : ", String.class);
        String msg="";

        if(!"end".equalsIgnoreCase(value)){
            customer = customers.getCustomerById(value);
            if(customer == null){
                msg = "There is No User : " + value;
            }else {
                msg = customer.toString();
            }
        }
        System.out.println(msg);
    }
}
