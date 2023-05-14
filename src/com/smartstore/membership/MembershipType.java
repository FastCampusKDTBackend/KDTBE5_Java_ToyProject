
package com.smartstore.membership;

import com.smartstore.util.MenuProvider;

import java.util.Arrays;
import java.util.Optional;

public enum MembershipType implements MenuProvider {
    GENERAL(new String[] {"G", "GENERAL", "일반"}, "General"),
    VIP(new String[] {"V", "VIP", "우수"}, "VIP"),
    VVIP(new String[] {"VV", "VVIP", "최우수"}, "VVIP");

    private final String[] membershipNames;
    private final String menuText;
    MembershipType(String[] membershipNames, String menuText) {
        this.membershipNames = membershipNames;
        this.menuText = menuText;
    }

    public String getMenuText() {
        return menuText;
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
