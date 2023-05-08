
package com.smartstore.membership;

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
}
