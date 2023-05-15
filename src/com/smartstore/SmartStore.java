package com.smartstore;

import com.smartstore.customer.Customer;
import com.smartstore.customer.Customers;
import com.smartstore.membership.MembershipType;
import com.smartstore.membership.Memberships;
import com.smartstore.util.CustomList;
import com.smartstore.util.Function;
import com.smartstore.function.mainmenu.MainMenuFunction;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SmartStore {
    public static void run(){
        //Load Initial Screen
        Function.of(0, MainMenuFunction.class).run();
    }

    public static void test(){

        Memberships.getInstance().setMembershipRequirement(MembershipType.GENERAL,3,3);
        Memberships.getInstance().setMembershipRequirement(MembershipType.VIP,6,6);
        Memberships.getInstance().setMembershipRequirement(MembershipType.VVIP,9,9);

        CustomList<Customer> customerList = Customers.getInstance().getCustomerList();
        Random random = new Random();
        int temp = ThreadLocalRandom.current().nextInt(97, 122+1);
        System.out.println(temp);
        System.out.println(((char) temp));
        for(int i = 0 ; i < 28 ; i++ ){
            String name = String.valueOf(((char)(ThreadLocalRandom.current().nextInt(97, 122+1))));
            String id = "id"+random.nextInt(100)+1;
            customerList.add(new Customer(name, id, random.nextInt(28)+1, random.nextInt(28)+1));
        }
        System.out.println(customerList);

        run();
    }
}
