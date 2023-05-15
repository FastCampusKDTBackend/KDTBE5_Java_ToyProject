package me.smartstore.scanner;

import java.util.Scanner;

public class Scan {

    private static Scanner instance;

    private Scan(){}

    public static Scanner getInstance(){
        if(instance == null) {
            instance = new Scanner(System.in);
        }
        return instance;
    }

}
