package me.smartstore.menu;

import me.smartstore.customer.Customers;
import me.smartstore.exception.InvalidInputExcetion;
import me.smartstore.scanner.Scan;

public class Parameter {
    public static void parameterMenu(){
        System.out.println("==============================\n" +
                            " 1. Set Parameter\n" +
                            " 2. View Parameter\n" +
                            " 3. Update Parameter\n" +
                            " 4. Back\n" +
                            "==============================\n");
        System.out.print("Choose One: ");
        String in = Scan.getInstance().nextLine();

        if(in.equals("1")){
            System.out.println("Which group (GENERAL (G), VIP (V), VVIP (VV))?\n" +
                               "** Press 'end', if you want to exit! **");
            in = Scan.getInstance().nextLine();
            if(in.equalsIgnoreCase("G")){

            }else if(in.equalsIgnoreCase("V")){

            }else if(in.equalsIgnoreCase("VV")){

            }else{

            }
        }else if(in.equals("2")){

        }else if(in.equals("3")){

        }else if(in.equals("4")){
            Lobby.lobbyMenuUI();
        }else{
            try {
                throw new InvalidInputExcetion();
            } catch (InvalidInputExcetion e) {
                System.out.println(e.getMessage());
                parameterMenu();
            }
        }

    }
}
