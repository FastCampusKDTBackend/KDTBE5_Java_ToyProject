package com.smartstore.membership;

import com.smartstore.customer.Customers;
import com.smartstore.util.CustomEnumMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Memberships {
    private final CustomEnumMap<MembershipType, MembershipRequirement> membershipList = new CustomEnumMap<>(MembershipType.class);
    private static Memberships instance;

    public static Memberships getInstance(){
        if(instance == null){
            instance = new Memberships();
        }
        return instance;
    }

    private Memberships(){

    }

    public CustomEnumMap<MembershipType, MembershipRequirement> getMembershipList() {
        return membershipList;
    }

    public MembershipRequirement findByType(MembershipType membershipType){
        if(membershipList.size() == 0 ){
            return null;
        }
        return membershipList.get(membershipType);
    }

    public void setMembershipRequirement(MembershipType membershipType){
        int minUsageTime;
        int minPaymentAmount;

        minUsageTime = setMinUsage();
        minPaymentAmount = setMinPaymentAmount();
        membershipList.put(membershipType, new MembershipRequirement(minUsageTime, minPaymentAmount));
    }

    public void setMembershipRequirement(MembershipType membershipType, int minUsage, int minPaymentAmount){
        membershipList.put(membershipType, new MembershipRequirement(minUsage, minPaymentAmount));
        //update membership
        Customers.getInstance().updateMembership();
    }

    public int setMinUsage(){
        return getInputFromConsole("Input minUsageTime : ");
    }

    public int setMinPaymentAmount(){
        return getInputFromConsole("Input minPaymentAmount : ");
    }

    private int getInputFromConsole(String msg){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int value;
        while (true){
            try {
                System.out.print(msg);
                value = Integer.parseInt(br.readLine());
                //check value overflowed or negative
                if (value < 0) {
                    // TODO: 2023-05-08 throw other exception, catch it
                    throw new NumberFormatException("");
                }
                break;

            } catch (IOException | NumberFormatException e) {
                System.out.println("Invalid Range of Input try 0 ~ Integer.Max");
            }
        }
        return value;
    }
}
