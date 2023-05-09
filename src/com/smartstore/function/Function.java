package com.smartstore.function;

import java.util.Arrays;

public interface Function extends MenuFunction {
    static <T extends Enum<T> & Function> T of(int menuNumber, Class<T> enumClass){

        return Arrays.stream(enumClass.getEnumConstants())
                .filter(enumValue -> enumValue.isMatchedMenuNumber(menuNumber))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Can't Find Function"));
    }

}
