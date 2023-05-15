package com.smartstore.util;

public class Printer {

    public static void printMenu(String[] menus){
        for(int i = 0 ; i < menus.length ; i++){
            System.out.printf(PrettyTerminal.BOLD.getAttribute() + "%d. %s\n",i + 1,menus[i] + PrettyTerminal.RESET.getAttribute());
        }
    }
    public static void printSelectable(String[] menus){
        for (String menu : menus) {
            System.out.printf("| " + PrettyTerminal.PURPLE.getAttribute() + "%s " + PrettyTerminal.RESET.getAttribute(), menu);
        }
        System.out.println("| or " + PrettyTerminal.RED.getAttribute() + "end" + PrettyTerminal.RESET.getAttribute());
    }
}
