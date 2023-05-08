package com.smartstore.membership;

import java.util.Map;

public class Membership {
    private Map<MembershipType, MembershipRequirement> gradeInfo;


    Membership(String gradeName, int minUsageTime, int minPaymentAmount){

    }

    public Membership getGrade(){
        return null;
    }

    public void updateGrade(String gradeName){

    }
}
