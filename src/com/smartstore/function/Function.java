package com.smartstore.function;

import com.smartstore.function.menu.Back;

import java.util.Arrays;

public interface Function {
    static <T extends Enum<T> & Function> T of(int menuNumber, Class<T> enumClass){

        return Arrays.stream(enumClass.getEnumConstants())
                .filter(enumValue -> enumValue.isMatchedMenuNumber(menuNumber))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Can't Find Function"));
    }

    int getMenuNumber();

    MenuController getMenuController();

    default boolean isMatchedMenuNumber(int menuNumber){
        return getMenuNumber() == menuNumber;
    }

    default void run() {
        MenuController menuController = getMenuController();
        if(menuController.getClass().equals(Back.class)){
            menuController.run(0);
        }else{
            menuController.run();
        }
    }
}
