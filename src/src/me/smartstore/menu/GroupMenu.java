package me.smartstore.menu;

import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;
import me.smartstore.group.Parameter;
import me.smartstore.util.Message;

import java.util.InputMismatchException;

import static me.smartstore.util.Message.END_MSG;


public class GroupMenu implements Menu{
    private static GroupMenu groupMenu;
    private final Customers allcustomers = Customers.getInstance();
    private final Groups allGroups = Groups.getInstance();


    public static GroupMenu getInstance(){
        if(groupMenu == null){
            return groupMenu = new GroupMenu();
        }
        else {
            return groupMenu;
        }
    }

    private GroupMenu(){};

    @Override
    public void manage() {
        while ( true ) { // 서브 메뉴 페이지를 유지하기 위한 while
            int choice = chooseMenu(new String[]{
                    "Set Parameter",
                    "View Parameter",
                    "Update Parameter",
                    "Back"});

            if (choice == 1) {
                createParameter();
            }
            else if (choice == 2) {
                viewParameter();
            }
            else if (choice == 3) {
                updateParameter();
            }
            else break; // choice == 4
        }
    }
    private void createParameter(){
        while (true){
            try {
                System.out.println("Which group (GENERAL (G), VIP (V), VVIP (VV))?");
                String inputData = nextLine(END_MSG);
                GroupType groupType = GroupType.valueOf(inputData).replaceFullName();
                Group group = allGroups.find(groupType);

                if (group != null && group.getParameter() != null) {
                    System.out.println("group already exists.");
                }
                else{
                    Parameter parameter = new Parameter();
                    setParameter(parameter);
                    group = new Group(parameter, groupType);
                    allGroups.add(group);
                    allcustomers.refresh();
                }
            }
            catch (InputEndException e){
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            }
            catch (IllegalArgumentException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    private void setParameter(Parameter parameter) {
        while (true) {
            int choice = chooseMenu(new String[] {
                    "Minimum Spent Time",
                    "Minimum Total Pay",
                    "Back"
            });

            if (choice == 1) {
                while (true) {
                    try {
                        System.out.println("Input Minimum Spent Time: ");
                        Integer minTime = formatInt(nextLine(END_MSG));
                        parameter.setMinTime(minTime);
                        break;
                    }

                    catch (InputRangeException e) {
                        System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
                    }

                    catch (InputMismatchException e) {
                        System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
                    }
                }
            } else if (choice == 2) {
                while (true) {
                    try {
                        System.out.println("Input Minimum Total Pay: ");
                        Integer minPay = formatInt(nextLine(END_MSG));
                        parameter.setMinPay(minPay);
                        break;
                    }

                    catch (InputRangeException e) {
                        System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
                    }

                    catch (InputMismatchException e) {
                        System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
                    }
                }
            } else {
                break;
            }
        }
    }


    private void viewParameter(){
        while (true){
            try{
                System.out.println("Which group (GENERAL (G), VIP (V), VVIP (VV))?");
                String inputData = nextLine(END_MSG);
                GroupType groupType = GroupType.valueOf(inputData).replaceFullName();
                Group group = allGroups.find(groupType);

                System.out.println(String.format("\nGroupType: %s", groupType.name()));
                System.out.println("Parameter: " + group.getParameter());
            }

            catch (InputEndException e){
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            }

            catch (IllegalArgumentException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }
    private void updateParameter(){
        while (true){
            try{
                System.out.println("Which group (GENERAL (G), VIP (V), VVIP (VV))?");
                String inputData = nextLine(END_MSG);
                GroupType groupType = GroupType.valueOf(inputData).replaceFullName();
                Group group = allGroups.find(groupType);

                if (group == null){
                    System.out.println(Message.ERR_MSG_INVALID_ARR_EMPTY);
                    break;
                }
                else{
                    Parameter parameter = group.getParameter();
                    setParameter(parameter);
                    group.setParameter(parameter);
                    allcustomers.refresh();
                }
            }
            catch (InputEndException e){
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            }
            catch (IllegalArgumentException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }
}
