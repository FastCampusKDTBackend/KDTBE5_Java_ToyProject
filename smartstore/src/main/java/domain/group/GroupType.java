package domain.group;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum GroupType {
    NONE("N"),
    GENERAL("G"),
    VIP("V"),
    VVIP("VV");

    private final String symbol;
    private Parameter parameter;

    GroupType(String symbol) {
        this.symbol = symbol;
        this.parameter = null;
    }

    public String getSymbol() {
        return symbol;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void initParameter() {
        parameter = new Parameter();
    }

//    public void updateParameter

    public static GroupType getBySymbolOrName(String groupName) {
        return Arrays.stream(GroupType.values())
                .filter(groupType -> groupType.name().equals(groupName) || groupType.symbol.equals(groupName))
                .findFirst()
                .orElseThrow();
    }

    public static GroupType findGroupType(int spentTime, int totalPay) {
        List<GroupType> findGroupList = Arrays.stream(GroupType.values())
                .filter(param -> {
                    int paramTime = param.getParameter().getMinimumSpentTime();
                    int paramPay = param.getParameter().getMinimumTotalPay();
                    if (spentTime >= paramTime && totalPay >= paramPay) {
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());

        return findGroupList.get(findGroupList.size() - 1);
    }

    public static String generateFormatForView() {
//        GENERAL (G), VIP (V), VVIP (VV)
        StringBuilder stringBuilder = new StringBuilder();
        GroupType[] groupTypes = GroupType.values();
        for (int i = 1; i < groupTypes.length; i++) {
            GroupType groupType = groupTypes[i];

            stringBuilder
                    .append(groupType.name())
                    .append(" (")
                    .append(groupType.getSymbol())
                    .append(")");

            if (i == groupTypes.length - 1) {
                break;
            }

            stringBuilder.append(", ");
        }

        return stringBuilder.toString();
    }
    @Override
    public String toString() {
        return "GroupType{" +
                "symbol='" + symbol + '\'' +
                ", parameter=" + parameter +
                '}';
    }
}
