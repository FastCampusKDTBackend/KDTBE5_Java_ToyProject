package com.smartstore.util;

public class Printer {

    public static void printMenu(String[] menus){
        for(int i = 0 ; i < menus.length ; i++){
            System.out.printf("%d. %s\n",i + 1,menus[i]);
        }
    }
    public static void printSelectable(String[] menus){
        for (String menu : menus) {
            System.out.printf("| %s ", menu);
        }
        System.out.println("| or 'end'");
    }
}
