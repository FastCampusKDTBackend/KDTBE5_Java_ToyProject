package com.smartstore.function;

public interface MenuPrintable {
    default void printMenu(String[] menus){
        for(int i = 0 ; i < menus.length ; i++){
            System.out.printf("%d. %s\n",i + 1,menus[i]);
        }
    }
}
