package com.smartstore;

import com.smartstore.customer.Customer;
import com.smartstore.membership.MembershipType;
import com.smartstore.membership.Memberships;
import com.smartstore.util.CustomList;

public class Main {
    public static void main(String[] args) {
        //SmartStore.run();
        CustomList<Customer> test = new CustomList<>();
        //Added before Membership Setting (Will update when Membership requirement set)
        test.add(new Customer("b1","b1ID",1,1));
        test.add(new Customer("b2","b2ID",3,1));
        test.add(new Customer("b3","b3ID",4,3));
        test.add(new Customer("b4","b4ID",6,3));
        test.add(new Customer("b5","b5ID",9,3));
        test.add(new Customer("b6","b6ID",9,6));

        Memberships.getInstance().setMembershipRequirement(MembershipType.GENERAL,3,3);
        Memberships.getInstance().setMembershipRequirement(MembershipType.VIP,6,6);
        Memberships.getInstance().setMembershipRequirement(MembershipType.VVIP,9,9);

        //Added after Membership Setting (update when added)
        test.add(new Customer("a1","a1ID",1,1));
        test.add(new Customer("a2","a2ID",3,3));
        test.add(new Customer("a3","a3ID",4,4));
        test.add(new Customer("a4","a4ID",5,5));
        test.add(new Customer("a5","a5ID",6,6));
        test.add(new Customer("a6","a6ID",7,7));
        test.add(new Customer("a7","a7ID",8,8));
        test.add(new Customer("a8","a8ID",9,9));
        test.add(new Customer("a9","a9ID",10,10));
        test.add(new Customer("c1","c1ID",6,3));
        test.add(new Customer("c2","c2ID",9,3));
        test.add(new Customer("c3","c3ID",9,6));


        System.out.println(test.toString());
        SmartStore.run();

    }
}
