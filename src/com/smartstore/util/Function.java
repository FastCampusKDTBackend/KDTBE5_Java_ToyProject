package com.smartstore.util;

import java.util.Arrays;

public interface Function<T> extends MenuProvider, Handleable, HandleableParam {
    static <E extends Enum<E> & Function> E of(int menuNumber, Class<E> enumClass){

        return Arrays.stream(enumClass.getEnumConstants())
                .filter(enumValue -> enumValue.isMatchedMenuNumber(menuNumber))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Can't Find Function"));
    }

    int getMenuNumber();

    T getMenuHandler();

    default boolean isMatchedMenuNumber(int menuNumber){
        return getMenuNumber() == menuNumber;
    }

    @Override
    default void run() {
        ((Handleable) getMenuHandler()).run();
    }

    @Override
    default <T> void run(T value) {
        ((HandleableParam) getMenuHandler()).run(value);
    }
}
