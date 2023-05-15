package com.smartstore.util;

import com.smartstore.membership.MembershipType;

import java.io.IOException;

public class Validator implements Readable {
    public static <T> String getValueOrEnd(String msg, Class<T> type){
        String value;
        while (true){
            try {
                System.out.println(msg);
                value = br.readLine();
                if("end".equalsIgnoreCase(java.lang.String.valueOf(value))){
                    break;
                }
                if(Number.class.isAssignableFrom(type) && Integer.parseInt(value) < 0){
                    throw new IllegalArgumentException();
                }else {
                    break;
                }
            } catch (IOException | IllegalArgumentException e) {
                System.out.println("Input valid Integer Data 1 ~ Integer.Max");
            }
        }
        return value;
    }


    /**
     *
     * @param  isRequired : is Data Required
     * @return <T> input value from user
     */
    public static  <T> String getUserData(boolean isRequired, String msg){
        String value = null;
        while (true){
            try {
                System.out.print(msg);
                value = br.readLine();
            } catch (IOException e) {
                System.out.println("Please Input Validate Data");
            }

            if(isRequired && !value.isBlank()){
                break;
            }
            System.out.println("Please Input Validate Data, This Element Can't be Empty or Black");
        }
        return value;
    }

    public static String getParameter(String[] values) {
        String valueName="";
        boolean isExit = false;
        if(values.length > 0) {
            while (!isExit) {
                try {
                    System.out.print("Input : ");
                    valueName = br.readLine();
                    if("end".equals(valueName)){
                        isExit = true;
                        break;
                    }
                    for (String membershipName : values) {
                        if (MembershipType.valueOf(membershipName.toUpperCase()).isMatchedName(valueName)) {
                            isExit = true;
                            break;
                        }
                    }
                    System.out.println("Invalid Parameter");
                } catch (IOException | IllegalArgumentException | NullPointerException e) {
                    System.out.println("Invalid Parameter");
                }
            }
        }
        return valueName;
    }

    public static String getMenuNumber(String[] values) {
        int menu = -1;
        while (true){
            try {
                System.out.print("Wait for Input : ");
                menu = Integer.parseInt(br.readLine());
                if (menu <= 0 || menu > values.length) {
                    throw new NumberFormatException("Input valid Menu Number 1 ~ " + (values.length-1));
                }
                break;

            } catch (IOException | NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        return String.valueOf(menu);
    }

    public static boolean isAnswerYes(String msg){
        System.out.println(msg);
        String value = "";
        while (true){
            try {
                System.out.print("Wait for Input Y or N: ");
                value = br.readLine();
            } catch (IOException e) {
                System.out.println("Please Input Y or N");
            }
            if("y".equalsIgnoreCase(value)){
                return true;
            }
            if("n".equalsIgnoreCase(value)){
                return false;
            }
            System.out.println("Please Input Y or N");
        }
    }

}
