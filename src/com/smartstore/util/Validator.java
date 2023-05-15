package com.smartstore.util;

import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;
import com.smartstore.membership.Memberships;

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
                        break;
                    }
                    for (String membershipName : values) {
                        if (MembershipType.valueOf(membershipName.toUpperCase()).isMatchedName(valueName)) {
                            isExit = true;
                            break;
                        }
                    }
                    if(!isExit){
                        throw new IllegalArgumentException();
                    }
                } catch (IOException | IllegalArgumentException | NullPointerException e) {
                    System.out.println("Invalid Parameter");
                }
            }
        }
        return valueName;
    }

    public static String getMenuNumber(String[] values) {
        int menu;
        while (true){
            try {
                System.out.print("Wait for Input : ");
                menu = Integer.parseInt(br.readLine());
                if (menu <= 0 || menu > values.length) {
                    throw new NumberFormatException();
                }
                break;

            } catch (IOException | NumberFormatException e) {
                System.out.println("Input valid Menu Number 1 ~ " + (values.length));
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

    public static int getInteger(String msg){
        int value;
        while (true){
            try {
                System.out.print(msg);
                value = Integer.parseInt(br.readLine());
                //check value overflowed or negative
                if (value < 0) {
                    throw new NumberFormatException("");
                }
                break;

            } catch (IOException | NumberFormatException e) {
                System.out.println("Invalid Range of Input try 0 ~ Integer.Max");
            }
        }
        return value;
    }

    public static boolean isValidMinUsage(MembershipType membershipType, int value){
        int prevMinUsageTime;
        int nextMinUsageTime;
        MembershipRequirement requirement;
        CustomEnumMap<MembershipType, MembershipRequirement> membershipMap = Memberships.getInstance().getMembershipMap();

        //if current membershipType is The Highest Membership
        if(membershipType == MembershipType.values()[MembershipType.values().length-1]){
            nextMinUsageTime = Integer.MAX_VALUE;
        } else {
            MembershipType nextMembershipType = MembershipType.values()[membershipType.ordinal()+1];
            requirement = membershipMap.get(nextMembershipType);
            nextMinUsageTime = (requirement == null) ? Integer.MAX_VALUE : requirement.getMinUsageTime();
        }

        //if current membershipType is The Lowest Membership or Next of Lowest
        if(membershipType == MembershipType.values()[0] || membershipType == MembershipType.values()[1]){
            prevMinUsageTime = 0;
        } else {
            MembershipType prevMembershipType = MembershipType.values()[membershipType.ordinal()-1];
            requirement = membershipMap.get(prevMembershipType);
            prevMinUsageTime = (requirement == null) ? 0 : requirement.getMinUsageTime();
        }


        return value <= nextMinUsageTime && value >= prevMinUsageTime;
    }

    public static boolean isValidMinPayment(MembershipType membershipType, int value){
        int prevMinPayment;
        int nextMinPayment;
        MembershipRequirement requirement;
        CustomEnumMap<MembershipType, MembershipRequirement> membershipMap = Memberships.getInstance().getMembershipMap();

        //if current membershipType is The Highest Membership
        if(membershipType == MembershipType.values()[MembershipType.values().length-1]){
            nextMinPayment = Integer.MAX_VALUE;
        } else {
            MembershipType nextMembershipType = MembershipType.values()[membershipType.ordinal()+1];
            requirement = membershipMap.get(nextMembershipType);
            //if next is not exist, 0
            nextMinPayment = (requirement == null) ? Integer.MAX_VALUE : requirement.getMinPaymentAmount();
        }

        //if current membershipType is The Lowest Membership or Next of Lowest
        if(membershipType == MembershipType.values()[0] || membershipType == MembershipType.values()[1]){
            prevMinPayment = 0;
        } else {
            MembershipType prevMembershipType = MembershipType.values()[membershipType.ordinal()-1];
            requirement = membershipMap.get(prevMembershipType);
            prevMinPayment = (requirement == null) ? 0 : requirement.getMinPaymentAmount();
        }


        return value <= nextMinPayment && value >= prevMinPayment;
    }

    public static String isAscendingOrEnd(){
        System.out.println(PrettyTerminal.BLUE.getAttribute() + "Ascending" + PrettyTerminal.RESET.getAttribute() + " or " + PrettyTerminal.RED.getAttribute() +"Descending?" + PrettyTerminal.RESET.getAttribute());
        String value = "";
        while (true){
            try {
                System.out.print("Wait for Input A or D " + PrettyTerminal.RED.getAttribute() + "end" + PrettyTerminal.RESET.getAttribute() + " to exit :");
                value = br.readLine();
            } catch (IOException e) {
                System.out.println("Please Input A or D");
            }

            if("A".equalsIgnoreCase(value) || "D".equalsIgnoreCase(value) || "end".equalsIgnoreCase(value)){
                return value;
            }

            System.out.println("Please Input A or D or end");
        }
    }

}
