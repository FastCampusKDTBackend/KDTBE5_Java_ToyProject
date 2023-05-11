package domain.group;

import java.util.Arrays;
import java.util.Objects;

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
        if (symbol.equals("N")) {
            this.parameter = new Parameter();
        }

    }

    public String getSymbol() {
        return symbol;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public boolean isParameterExist() {
        return parameter != null;
    }

    public void initParameter() {
        parameter = new Parameter();
    }

    public static boolean isGroupParameterSet() {
        return Arrays.stream(GroupType.values())
                .map(GroupType::getParameter)
                .allMatch(Objects::nonNull);
    }

    public static GroupType getBySymbolOrName(String groupName) {
        return Arrays.stream(GroupType.values())
                .filter(groupType -> groupType.name().equals(groupName) || groupType.symbol.equals(groupName))
                .findFirst()
                .orElseThrow();
    }

    public static GroupType findGroupType(int spentTime, int totalPay) {
        return Arrays.stream(GroupType.values())
                .reduce(NONE, (prevType, currentType) -> {
                            if (spentTime >= currentType.getParameter().getMinimumSpentTime() && totalPay >= currentType.getParameter().getMinimumTotalPay()) {
                                return currentType;
                            }
                            return prevType;
                        }
                );
    }

    public static String generateFormatForView() {
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
