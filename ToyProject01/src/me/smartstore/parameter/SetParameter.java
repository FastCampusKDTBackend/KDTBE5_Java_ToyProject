package me.smartstore.parameter;

import me.smartstore.exception.InvalidFormatInput;
import me.smartstore.exception.InvalidInputExcetion;
import me.smartstore.group.GroupGeneral;
import me.smartstore.group.Groups;
import me.smartstore.menu.Parameter;
import me.smartstore.scanner.Scan;

import java.util.Optional;

public class SetParameter {
    public static void setParameterMethod() {

        System.out.println("Which group (GENERAL (G), VIP (V), VVIP (VV))?\n" +
                "** Press 'end', if you want to exit! **");
        String in = Scan.getInstance().nextLine();
        if (in.equalsIgnoreCase("G")) {
            setGeneralParameterMethod();
        } else if (in.equalsIgnoreCase("V")) {
            setVipParameterMethod();
        } else if (in.equalsIgnoreCase("VV")) {
            setVvipParameterMethod();
        } else if (in.equals("end")) {
            System.out.println("END is pressed. Exit this menu.\n");
            Parameter.parameterMenu();
        } else {
            try {
                throw new InvalidInputExcetion();
            } catch (InvalidInputExcetion e) {
                System.out.println(e.getMessage());

            }
            setParameterMethod();
        }
    }

    public static void setGeneralParameterMethod() {
        Groups groups = Groups.getInstance();
        if (groups.getGroupGeneral().isPresent()) {
            String groupType = groups.getGroupGeneral().get().groupType;
            System.out.println(groupType + " group already exists.\n");
            System.out.println();
            System.out.println("GroupType: " + groupType);
            System.out.println("Parameter: " + groups.getGroupGeneral().get().toString());
            setParameterMethod();

        } else {
            System.out.println("==============================\n" +
                    " 1. Minimum Spent Time\n" +
                    " 2. Minimum Total Pay\n" +
                    " 3. Back\n" +
                    "==============================");
            String in = Scan.getInstance().nextLine();
            if(in.equals("1")){
                setMinimumSpentTime();
            }else if(in.equals("2")){
                setMinimumTotalPay();
            }else if(in.equals("3")){

            }else{
                try {
                    throw new InvalidFormatInput();
                } catch (InvalidFormatInput e) {
                    System.out.println(e.getMessage());

                }
            }

        }
    }

    public static void setVipParameterMethod() {

    }

    public static void setVvipParameterMethod() {

    }

    public static void setMinimumSpentTime(){
        String in = Scan.getInstance().nextLine();
    }

    public static void setMinimumTotalPay(){

    }


}
