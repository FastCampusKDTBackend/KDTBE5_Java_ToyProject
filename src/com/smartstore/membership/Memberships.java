package com.smartstore.membership;

import com.smartstore.util.CustomEnumMap;
import com.smartstore.util.Map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Memberships {
    private Map<MembershipType, MembershipRequirement> membershipList = new CustomEnumMap<>(MembershipType.class);
    private static Memberships instance;

    public static Memberships getInstance(){
        if(instance == null){
            instance = new Memberships();
        }
        return instance;
    }

    public void refresh(){

    }
    private Memberships(){

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

        minUsageTime = getInputFromConsole("Input minUsageTime : ");
        minPaymentAmount = getInputFromConsole("Input minPaymentAmount : ");
        membershipList.put(membershipType, new MembershipRequirement(minUsageTime, minPaymentAmount));
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
                    throw new NumberFormatException("Invalid Menu");
                }
                break;

            } catch (IOException | NumberFormatException e) {
                System.out.println("Invalid Range of Input try 0 ~ Integer.Max");
            }
        }
        return value;
    }
}
