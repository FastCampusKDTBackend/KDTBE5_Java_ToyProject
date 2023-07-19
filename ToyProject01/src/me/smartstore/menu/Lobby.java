package me.smartstore.menu;


import me.smartstore.exception.InvalidInputExcetion;
import me.smartstore.scanner.Scan;
import java.util.Scanner;

public class Lobby {

    public static void lobbyMenuUI() {
        System.out.println( "==============================\n" +
                            " 1. Parameter\n" +
                            " 2. Customer Data\n" +
                            " 3. Classification Summary\n" +
                            " 4. Quit\n" +
                            "==============================");
       System.out.print("Choose One: ");
       String in = Scan.getInstance().nextLine();



       if(in.equals("1")){
           Parameter.parameterMenu();
       }else if(in.equals("2")){

       }else if(in.equals("3")){

       }else if(in.equals("4")){// 프로그램 종료
           System.exit(0);
       }
       else{
           try {
               throw new InvalidInputExcetion();
           } catch (InvalidInputExcetion e) {
               System.out.println(e.getMessage());
               lobbyMenuUI();
           }
       }

    }


}
