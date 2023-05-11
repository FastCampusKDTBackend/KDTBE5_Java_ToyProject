package menu.subMenu;

import domain.group.Group;
import domain.group.GroupType;
import exception.*;
import menu.SubMenu;
import util.MenuManager;
import service.GroupService;
import service.SummaryService;
import util.view.Input;
import util.view.Output;

public class GroupMenu implements SubMenu {

    private static final String[] MENU_ITEMS;
    private static final String[] SUB_MENU_ITEMS;

    static {
        MENU_ITEMS = new String[]{"Set Parameter", "View Parameter", "Update Parameter", "Back"};
        SUB_MENU_ITEMS = new String[]{"Minimum Spent Time", "Minimum Total Pay", "Back"};
    }

    private final GroupService groupService;
    private final SummaryService summaryService;

    public GroupMenu(GroupService groupService, SummaryService summaryService) {
        this.groupService = groupService;
        this.summaryService = summaryService;
    }

    @Override
    public void service() {
        while (true) {
            try {
                int choiceMenuNum = MenuManager.chooseMenu(MENU_ITEMS);
                if (choiceMenuNum == 1) setGroupData();
                if (choiceMenuNum == 2) viewAllGroup();
                if (choiceMenuNum == 3) updateGroupData();
            } catch (InputNumOfEndMenuException exception) {
                break;
            }
        }
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

    void setParameter(Group group){
        while (true) {
            try {
                int choiceMenuNumber = MenuManager.chooseMenu(SUB_MENU_ITEMS);
                if (choiceMenuNumber == 1) group.setMinTime(Input.inputMinSpentTime());
                if (choiceMenuNumber == 2) group.setMinPay(Input.inputMinTotalPayment());
            } catch (InputEndException exception) {
                Output.printErrorMessage(exception.getMessage());
                break;
            } catch (InputNumOfEndMenuException exception) {
                break;
            }
        }
    }

    private void callSummaryServiceRefresh(){
       summaryService.refreshClassifiedCustomers();
    }
}
