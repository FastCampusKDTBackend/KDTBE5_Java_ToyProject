package jyjang.smartstore.group;

import java.util.Arrays;

public enum GroupType {
    NONE("N", "해당없음")
    , GENERAL("G", "일반고객")
    , VIP("V", "우수고객")
    , VVIP("VV", "최우수고객");


    String label;
    final String groupType;

    GroupType(String initial, String groupType) {
        this.label = initial;
        this.groupType = groupType;
    }

    public String getLabel() {
        return label;
    }

    public String getGroupType() {
        return groupType;
    }

    public static GroupType getGroupTypeByInitial(String label) {
        GroupType groupType = Arrays.stream(values())
                .filter(type -> type.label.equals(label.toUpperCase()))
                .findAny()
                .orElse(null);

        if(null == groupType) {
            throw new IllegalArgumentException();
        }

        return groupType;
    }

}