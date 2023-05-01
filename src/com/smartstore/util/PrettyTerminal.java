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
    static void cls(){

        String osName = System.getProperty("os.name");
        try{
            if (osName.contains("Windows")) {
                String terminal = System.getenv("TERM");
                if (terminal != null && terminal.contains("xterm")) {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                } else if (osName.contains("Windows 10")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } else {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                }
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        }

        catch (Exception e){
        }
    }

    public String getAttribute() {
        //System.out.println(RED.getAttribute() + BOLD.getAttribute() + "Welcome to my decorated terminal!" + RESET.getAttribute();
        return attribute;
    }
}
