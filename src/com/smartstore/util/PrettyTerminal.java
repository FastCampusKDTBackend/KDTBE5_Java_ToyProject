package com.smartstore.util;

public enum PrettyTerminal {
    RESET("\u001B[0m"),
    BOLD("\u001B[1m"),
    UNDERLINE("\u001B[4m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m");

    String attribute;
    PrettyTerminal(String attribute) {
        this.attribute = attribute;
    }
    public static void cls(){
        for (int i = 0; i < 15; i++) {
            System.out.println();
        }
    }

    public String getAttribute() {
        //System.out.println(RED.getAttribute() + BOLD.getAttribute() + "Welcome to my decorated terminal!" + RESET.getAttribute();
        return attribute;
    }
}
