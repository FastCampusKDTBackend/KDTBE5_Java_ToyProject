package service;

import domain.group.Group;
import domain.group.GroupType;
import domain.group.Groups;
import domain.group.Parameter;
import exception.InputEndException;
import exception.InputRangeException;
import view.Input;

import java.util.Objects;

public class GroupService {

    private static GroupService groupService;
    private static final Groups groups = Groups.getInstance();
    private static final String[] MENU_ITEMS = new String[]{"Minimum Spent Time", "Minimum Total Pay", "Back"};

    public static GroupService getInstance() {
        if (Objects.isNull(groupService)){
            groupService = new GroupService();
        }
        return groupService;
    }

    private GroupService() {

    }

    void setParameter(Parameter parameter){
        while (true) {
            try {
                int choiceMenuNumber = chooseSetParameterMenu();
                if (choiceMenuNumber == 3) break;
                if (choiceMenuNumber == 1) parameter.setMinTime(Input.inputMinSpentTime());
                if (choiceMenuNumber == 2) parameter.setMinPay(Input.inputMinTotalPayment());
            } catch (InputEndException | InputRangeException exception) {
                System.out.println(exception.getMessage());
            }
        }
        setParameterEmptyValueToDefault(parameter);
    }

    private void setParameterEmptyValueToDefault(Parameter parameter){
        if (Objects.isNull(parameter.getMinTime())) parameter.setMinTime(0);
        if (Objects.isNull(parameter.getMinPay())) parameter.setMinPay(0);
    }

    private int chooseSetParameterMenu(){
        while (true) {
            try {
                int inputMenuNumber = Input.chooseMenuNumber(MENU_ITEMS);
                if (inputMenuNumber < 1 || inputMenuNumber > MENU_ITEMS.length) throw new InputRangeException();
                return inputMenuNumber;
            } catch (InputRangeException exception){
                System.out.println(exception.getMessage());
            }
        }
    }

    public void checkInvalidGroup(GroupType groupType) {
        if (groups.size() != 0 && groups.find(groupType) != null)
            throw new IllegalArgumentException();
    }

    public void addGroup() {
        while (true) {
            GroupType groupType = null;
            try{
                groupType = Input.chooseGroup();
                checkInvalidGroup(groupType);
                Group group = new Group(new Parameter(), groupType);
                setParameter(group.getParameter());
                groups.add(group);

            } catch (IllegalArgumentException exception) {
                System.out.println(groupType + " group is already exists");
                System.out.println(groups.find(groupType));

            } catch (InputEndException exception) {
                System.out.println(exception.getMessage());
                break;
            }
        }
    }

    public void viewParameter() {
        System.out.println(groups);
    }

    public void updateParameter() {
        while (true) {
            try {
                setParameter(groups.find(Input.chooseGroup()).getParameter());
            } catch (InputEndException exception) {
                System.out.println(exception.getMessage());
                break;
            }
        }
    }
}
