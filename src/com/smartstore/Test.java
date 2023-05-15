package com.smartstore;

import com.smartstore.customer.Customer;
import com.smartstore.customer.Customers;
import com.smartstore.function.membership.set.SetMembershipHandler;
import com.smartstore.membership.MembershipType;
import com.smartstore.util.CustomList;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Test {
    public static void run() {
        SetMembershipHandler setMembershipHandler = new SetMembershipHandler() {
            @Override
            public <T> void run(T value) {

            }
        };
        setMembershipHandler.setMembershipRequirement(MembershipType.GENERAL,3,5);
        setMembershipHandler.setMembershipRequirement(MembershipType.VIP,3,10);
        setMembershipHandler.setMembershipRequirement(MembershipType.VVIP,20,20);

        CustomList<Customer> customerList = Customers.getInstance().getCustomerList();
        Random random = new Random();

        for(int i = 0 ; i < 28 ; i++ ){
            String name = String.valueOf(((char)(ThreadLocalRandom.current().nextInt(97, 122+1))));
            String id = "id"+random.nextInt(100)+1;
            customerList.add(new Customer(name, id, random.nextInt(28)+1, random.nextInt(28)+1));
        }
        System.out.println(customerList);


        SmartStore.run();
    }
}
