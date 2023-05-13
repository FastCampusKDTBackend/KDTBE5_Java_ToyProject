package com.smartstore.function;

import java.util.Arrays;

public interface Function {
    static <E extends Enum<E> & Function> E of(int menuNumber, Class<E> enumClass){

        return Arrays.stream(enumClass.getEnumConstants())
                .filter(enumValue -> enumValue.isMatchedMenuNumber(menuNumber))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Can't Find Function"));
    }

    int getMenuNumber();

    <T extends Handleable> T getMenuHandler();

    default boolean isMatchedMenuNumber(int menuNumber){
        return getMenuNumber() == menuNumber;
    }

    default void run() {
        getMenuHandler().run();
    }

}
