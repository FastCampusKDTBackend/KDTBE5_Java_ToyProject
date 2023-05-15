package me.smartstore.menu;


import me.smartstore.scanner.Scan;
import java.util.Scanner;

public class LobbyMenu {

    public static void lobbyMenuUI(){
       String strlobbyMenuUI = "==============================\n" +
                               " 1. Parameter\n" +
                               " 2. Customer Data\n" +
                               " 3. Classification Summary\n" +
                               " 4. Quit\n" +
                               "==============================\n";
       System.out.print(strlobbyMenuUI+"Choose One: ");
       String in = Scan.getInstance().nextLine();

       if(in.equals("1")){
           System.out.println(in);
       }else{
           System.out.println("error");
           System.out.println(in);
       }


    }


}
