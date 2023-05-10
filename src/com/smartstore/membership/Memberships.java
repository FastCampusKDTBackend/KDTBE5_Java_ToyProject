package com.smartstore.membership;

import com.smartstore.util.CustomEnumMap;
import com.smartstore.util.Map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Member;

public class Memberships {
    private Map<MembershipType, MembershipRequirement> membershipList = new CustomEnumMap<>(MembershipType.class);
    private static Memberships instance;

    public static Memberships getInstance(){
        if(instance == null){
            instance = new Memberships();
        }
        return instance;
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

        minUsageTime = setMinUsage(membershipType);
        minPaymentAmount = setMinPaymentAmount(membershipType);
        membershipList.put(membershipType, new MembershipRequirement(minUsageTime, minPaymentAmount));
    }

    public void setMembershipRequirement(MembershipType membershipType, int minUsage, int minPaymentAmount){
        membershipList.put(membershipType, new MembershipRequirement(minUsage, minPaymentAmount));
    }

    public int setMinUsage(MembershipType membershipType){
        return getInputFromConsole("Input minUsageTime : ");
    }

    public int setMinPaymentAmount(MembershipType membershipType){
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
