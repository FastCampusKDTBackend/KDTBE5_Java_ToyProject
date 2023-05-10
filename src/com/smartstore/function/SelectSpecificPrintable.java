package com.smartstore.function;

public interface SelectSpecificPrintable {
    default void printMenu(String[] menus){
        for(int i = 0 ; i < menus.length ; i++){
            System.out.printf("| %s ",menus[i]);
        }
        System.out.println("| or 'end'");
    }
}
