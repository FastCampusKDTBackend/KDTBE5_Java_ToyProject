package com.smartstore.membership;

import com.smartstore.util.CustomEnumMap;
import com.smartstore.util.Readable;

public class Memberships implements Readable {
    private final CustomEnumMap<MembershipType, MembershipRequirement> membershipMap = new CustomEnumMap<>(MembershipType.class);
    private static Memberships instance;

    public static Memberships getInstance(){
        if(instance == null){
            instance = new Memberships();
        }
        return instance;
    }

    private Memberships(){

    }

    public CustomEnumMap<MembershipType, MembershipRequirement> getMembershipMap() {
        return membershipMap;
    }

    public MembershipRequirement findByType(MembershipType membershipType) {
        if (membershipMap.size() == 0) {
            return null;
        }
        return membershipMap.get(membershipType);
    }

}
