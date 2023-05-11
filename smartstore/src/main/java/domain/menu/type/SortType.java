package domain.menu.type;

import util.common.exception.NotFoundException;

import java.util.Arrays;

public enum SortType {
    ASCENDING("A"),
    DESCENDING("D");


    private final String symbol;

    SortType(String symbol) {
        this.symbol = symbol;
    }

    public static String generateFormatForView() {
//        GENERAL (G), VIP (V), VVIP (VV)
        StringBuilder stringBuilder = new StringBuilder();
        SortType[] sortTypes = SortType.values();
        for (int i = 0; i < sortTypes.length; i++) {
            SortType sortType = sortTypes[i];

            stringBuilder
                    .append(sortType.name())
                    .append(" (")
                    .append(sortType.symbol)
                    .append(")");

            if (i == sortTypes.length - 1) {
                break;
            }

            stringBuilder.append(", ");
        }

        return stringBuilder.toString();
    }

    public static SortType findOrder(String command) {
        return Arrays.stream(SortType.values())
                .filter(sortType -> command.equals(sortType.symbol) || command.equals(sortType.name()))
                .findAny()
                .orElseThrow(NotFoundException::new);
    }

    public static boolean isAscending(SortType sortType) {
        return sortType.equals(ASCENDING);
    }
}
