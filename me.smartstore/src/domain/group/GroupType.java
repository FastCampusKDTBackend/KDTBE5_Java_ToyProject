package domain.group;

// None, General, VIP, VVIP
// N, G, V, VV
// 그룹이 가져야 하는 것? 시간과 금액 - 이걸 기준으로 분류한다.

public enum GroupType {
    NONE("N"), GENERAL("G"), VIP("V"), VVIP("VV");

    private String shortName;

    GroupType(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public boolean isName(String input) {
        if (this.shortName.equals(input) || this.name().equals(input)) return true;
        return false;
    }
}
