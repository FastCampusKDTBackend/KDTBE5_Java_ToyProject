package com.smartstore.util;

public class Printer {

    public static void printMenu(String[] menus){
        for(int i = 0 ; i < menus.length ; i++){
            System.out.printf("%d. %s\n",i + 1,menus[i]);
        }
    }
    public static void printSelectable(String[] menus){
        for(int i = 0 ; i < menus.length ; i++){
            System.out.printf("| %s ",menus[i]);
        }
        System.out.println("| or 'end'");
    }
}
