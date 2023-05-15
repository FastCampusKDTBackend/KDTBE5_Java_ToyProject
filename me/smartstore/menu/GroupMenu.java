package me.smartstore.menu;

import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputTypeException;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;
import me.smartstore.group.Parameter;
import me.smartstore.util.Message;

public class GroupMenu implements Menu {
    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();

    //singleton
    private static GroupMenu groupMenu;

    public static GroupMenu getInstance() {
        if (groupMenu == null)
            groupMenu = new GroupMenu();
        return groupMenu;
    }

    private GroupMenu() {
    }

    @Override
    public void manage() {
        while (true) {
            int choice = chooseMenu(new String[]{
                    "Set Parameter",
                    "View Parameter",
                    "Update Parameter",
                    "Back"});

            if (choice == 1)
                setParameter();
            else if (choice == 2)
                viewParameter();
            else if (choice == 3)
                updateParameter();
            else break; // choice == 4
        }
    }

    public GroupType chooseGroup() {
        while (true) {
            try {
                System.out.println("Which Group (GENERAL (G), VIP (V), VVIP (VV)? ");
                String choice = nextLine(Message.END_MSG);

                GroupType groupType = GroupType.valueOf(choice).replaceFullName();
                return groupType;
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
        return null;
    }

    public void setParameter() {
        while (true) {
            GroupType groupType = chooseGroup();

            Group group = allGroups.find(groupType);
            if (group != null && group.getGroupType() != null) {
                System.out.println("\n" + group.getGroupType() + "group already exists.");
                System.out.println("\n" + group);
            } else if(group == null){
                return;
            }else {
                Parameter parameter = new Parameter();
                //time, pay 사용자 입력받은 후, 설정 필요

                group.setParameter(parameter);
                allCustomers.refresh(allGroups);
            }
        }
    }

    public void viewParameter() {
        while (true) {
            GroupType groupType = chooseGroup();

            Group group = allGroups.find(groupType);
            if (group == null || group.getGroupType() == null)
                break;
            else {
                System.out.println("\n" + group.getGroupType());
                System.out.println("\n" + group);
            }
        }
    }

    public void updateParameter() {
        while (true) {
            GroupType groupType = chooseGroup();

            Group group = allGroups.find(groupType);
            if (group == null || group.getGroupType() == null)
                return;
            else {
                chooseParameter(group);
            }
        }

    }

    public void chooseParameter(Group group){
        while( true ){
            int choice = chooseMenu(new String[]{
                    "Minimum Spent Time",
                    "Minimum Total Pay",
                    "Back"});
            try{
                if (choice == 1) {
                    inputTime(group);
                    allCustomers.refresh(allGroups);
                }else if ( choice == 2){
                    inputPay(group);
                    allCustomers.refresh(allGroups);
                }else {
                    System.out.println(group.toString());
                    break; //choice == 3
                }
            }catch (InputTypeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
            }
        }
    }

    public int inputTime(Group group){
        int minTime = 0;
        while(true){
            try{
                System.out.println("Input Minimum Spent Time: ");
                minTime = Integer.parseInt(nextLine(Message.END_MSG));
            }catch (InputTypeException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
            }catch (NumberFormatException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            }catch (InputEndException e){
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            }
        }
        return minTime;
    }

    public int inputPay(Group group){
        int totalPay = 0;
        while(true){
            try{
                System.out.println("Input Minimum Total pay: ");
                totalPay = Integer.parseInt(nextLine(Message.END_MSG));
            }catch (InputTypeException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
            }catch (NumberFormatException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            }catch (InputEndException e){
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            }
        }
        return totalPay;
    }
}