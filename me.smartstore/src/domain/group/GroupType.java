package domain.group;

// None, General, VIP, VVIP
// N, G, V, VV
//그룹이 가져야 하는 것? -> 시간과 금액 -> 이걸 기준으로 나눔

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum GroupType {
    NONE("N"), GENERAL("G"), VIP("V"), VVIP("VV");

    private String shortName;

    GroupType(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public GroupType shortToFull() {
        if (getShortName().equals("N")) return NONE;
        else if (getShortName().equals("G")) return GENERAL;
        else if (getShortName().equals("V")) return VIP;
        else if (getShortName().equals("VV")) return VVIP;

        return this;
    }



}
