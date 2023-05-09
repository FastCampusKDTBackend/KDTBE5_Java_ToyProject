
package com.smartstore.membership;

import java.util.Arrays;
import java.util.Optional;

public enum MembershipType {
    GENERAL(new String[] {"G", "GENERAL", "일반"}),
    VIP(new String[] {"V", "VIP", "우수"}),
    VVIP(new String[] {"VV", "VVIP", "최우수"});

    private String[] membershipNames={};
    MembershipType(String[] membershipNames) {
        this.membershipNames = membershipNames;
    }

    public String[] getMembership() {
        return membershipNames;
    }

    public boolean isMatchedName(String membershipName){
        return Arrays.stream(membershipNames)
                .anyMatch(name -> name.equalsIgnoreCase(membershipName));
    }

    public boolean findByName(String membershipName){
        Optional<String> membershipOpt = Arrays.stream(membershipNames)
                .filter(name -> name.equalsIgnoreCase(membershipName))
                .findFirst();
        return (membershipOpt.isPresent());
    }
}
