package com.smartstore.function.customer.update;

import com.smartstore.customer.Customer;

public class SetPaymentAmount implements UpdateCustomerHandler {
    private static SetPaymentAmount instance;

    private SetPaymentAmount(){

    }

    public static SetPaymentAmount getInstance(){
        if(instance == null){
            return new SetPaymentAmount();
        }
        return instance;
    }

    @Override
    public <T> void run(T value) {
        String paymentAmount;
        paymentAmount = getValueOrEnd("Input New PaymentAmount\n Wait for input... 'end' to exit", Integer.class);
        if(paymentAmount.isBlank() || "end".equals(paymentAmount)){
            System.out.println("Change not saved");
            return;
        }
        ((Customer) value).setPaymentAmount(Integer.parseInt(paymentAmount));
        //update membership
        ((Customer) value).updateMembership();
        System.out.println("Change Successfully Saved");
    }
}
