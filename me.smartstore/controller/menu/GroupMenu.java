package controller.menu;

import domain.group.Group;
import domain.group.GroupType;
import exception.*;
import service.GroupService;
import service.SummaryService;
import view.Input;
import view.Output;

import java.util.Objects;

public class GroupMenu implements Menu {

    private static final String[] GROUP_MAIN_MENU_ITEMS;
    private static final String[] GROUP_SET_MENU_ITEMS;
    private static final int MENU_ITEMS_MAX_NUM;
    private static final int MENU_ITEMS_MIN_NUM;

    private static GroupMenu parameterMenu;

    private final GroupService groupService;

    static {
        GROUP_MAIN_MENU_ITEMS = new String[]{"Set Parameter", "View Parameter", "Update Parameter", "Back"};
        GROUP_SET_MENU_ITEMS = new String[]{"Minimum Spent Time", "Minimum Total Pay", "Back"};
        MENU_ITEMS_MAX_NUM = GROUP_MAIN_MENU_ITEMS.length;
        MENU_ITEMS_MIN_NUM = 1;
    }

    public static GroupMenu getInstance() {
        if (Objects.isNull(parameterMenu)){
            parameterMenu = new GroupMenu();
        }
        return parameterMenu;
    }

    private GroupMenu() {
        groupService = GroupService.getInstance();
    }

    @Override
    public String[] items() {
        return GROUP_MAIN_MENU_ITEMS;
    }

    @Override
    public int menuItemsMaxNum() {
        return MENU_ITEMS_MAX_NUM;
    }

    @Override
    public int menuItemsMinNum() {
        return MENU_ITEMS_MIN_NUM;
    }

    @Override
    public void service(int menuNum) {
        if (menuNum == 1) setGroupData();
        if (menuNum == 2) viewAllGroup();
        if (menuNum == 3) updateGroupData();
    }

    // 리팩터링 고려할 것.
    private void setGroupData() {
        GroupType groupType = Input.chooseGroupType();
        try {
            groupService.checkInvalidGroup(groupType);
            Group group = new Group(groupType);
            setParameter(group);
            groupService.insertGroup(group);

        } catch (GroupSetAlreadyException exception){
            Output.printErrorMessage(exception.getMessage());
            Output.printGroup(groupService.selectGroupByGroupType(groupType));
        }
        callSummaryServiceRefresh();
    }

    private void viewAllGroup() {
        try {
            Output.printGroups(groupService.selectAllGroup());
        } catch (ArrayEmptyException exception) {
            Output.printErrorMessage(exception.getMessage());
        }
    }

    private void updateGroupData() {
        try {
            GroupType groupType = Input.chooseGroupType();
            Group group = groupService.selectGroupByGroupType(groupType);
            Output.printGroup(group);
            setParameter(group);
        } catch (ArrayEmptyException | GroupNotFoundException | InputEndException exception) {
            Output.printErrorMessage(exception.getMessage());
        }
        callSummaryServiceRefresh();
    }

    // 이것들도 먼가 컨트롤러에 장착시켜서 처리할 수 있을 거 같은 느낌
    void setParameter(Group group){
        while (true) {
            try {
                int choiceMenuNumber = chooseSetParameterMenu();
                if (choiceMenuNumber == 3) break;
                if (choiceMenuNumber == 1) group.setMinTime(Input.inputMinSpentTime());
                if (choiceMenuNumber == 2) group.setMinPay(Input.inputMinTotalPayment());
            } catch (InputEndException exception) {
                Output.printErrorMessage(exception.getMessage());
                break;
            }
        }
    }

    private int chooseSetParameterMenu(){
        while (true) {
            try {
                int inputMenuNumber = Input.chooseMenuNumber(GROUP_SET_MENU_ITEMS);
                if (inputMenuNumber < 1 || inputMenuNumber > GROUP_SET_MENU_ITEMS.length) throw new InputRangeException();
                return inputMenuNumber;
            } catch (InputRangeException exception){
                Output.printErrorMessage(exception.getMessage());
            }
        }
    }

    private void callSummaryServiceRefresh(){
        SummaryService.getInstance().refreshClassifiedCustomers();
    }
}
