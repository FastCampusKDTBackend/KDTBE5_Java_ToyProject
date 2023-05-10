package com.smartstore.function;

import java.util.Arrays;

public interface Function {
    static <T extends Enum<T> & Function> T of(int menuNumber, Class<T> enumClass){

        return Arrays.stream(enumClass.getEnumConstants())
                .filter(enumValue -> enumValue.isMatchedMenuNumber(menuNumber))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Can't Find Function"));
    }

    int getMenuNumber();

    MenuHandler getMenuController();

    default boolean isMatchedMenuNumber(int menuNumber){
        return getMenuNumber() == menuNumber;
    }

    default void run() {
        MenuHandler menuHandler = getMenuController();
        if(menuHandler.getClass().equals(Back.class)){
            menuHandler.run(0);
        }else{
            menuHandler.run();
        }
    }
}
