package me.smartstore.groups;

// N, G, V, VV
// NONE, GENERAL, VIP, VVIP
public enum GroupType {
    NONE("해당없음"), GENERAL("일반고객"), VIP("우수고객"), VVIP("최우수고객"),
    N("해당없음"), G("일반고객"), V("우수고객"), VV("최우수고객");

    String groupType = "";

    GroupType(String groupType) {
        this.groupType = groupType;
    }

    /*
    * TODO : 엔터나, 특수문자 예외처리 생각하기
    * */
    public GroupType replaceFullName() {
        if (this == N) return NONE;
        else if (this == G) return GENERAL;
        else if (this == V) return VIP;
        else if (this == VV) return VVIP;
        return this;
    }

}
