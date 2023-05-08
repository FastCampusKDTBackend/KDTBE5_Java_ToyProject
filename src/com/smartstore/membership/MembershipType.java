
package com.smartstore.membership;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum MembershipType {
    GENERAL(new String[] {"G", "GENERAL", "일반"}),
    VIP(new String[] {"V", "VIP", "우수"}),
    VVIP(new String[] {"VV", "VVIP", "최우수"});

    String[] membershipNames = new String[3];
    MembershipType(String[] grade) {
        this.membershipNames = grade;
    }

    public String[] getMembership() {
        return membershipNames;
    }

    public boolean isMatchedName(String membershipName){
        return Arrays.stream(membershipNames)
                .filter(name -> name.equals(membershipName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Not found Membership")).length() > 0;
    }
}
