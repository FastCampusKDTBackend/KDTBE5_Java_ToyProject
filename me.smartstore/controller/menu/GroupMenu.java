package controller.menu;

import service.GroupService;
import service.SummaryService;

import java.util.Objects;

public class GroupMenu implements Menu {

    private static final String[] MENU_ITEMS;
    private static final int MENU_ITEMS_MAX_NUM;
    private static final int MENU_ITEMS_MIN_NUM;

    private static GroupMenu parameterMenu;
    private static GroupService groupService;

    static {
        MENU_ITEMS = new String[]{"Set Parameter", "View Parameter",
                "Update Parameter", "Back"};
        MENU_ITEMS_MAX_NUM = MENU_ITEMS.length;
        MENU_ITEMS_MIN_NUM = 1;
        groupService = GroupService.getInstance();
    }

    public static GroupMenu getInstance() {
        if (Objects.isNull(parameterMenu)){
            parameterMenu = new GroupMenu();
        }
        return parameterMenu;
    }

    private GroupMenu() {

    }

    @Override
    public String[] items() {
        return MENU_ITEMS;
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
        if (menuNum == 1) setParameterData();       // call groupService.addGroup();
        if (menuNum == 2) viewParameterData();      // call groupService.viewParameter();
        if (menuNum == 3) updateParameterData();    // call groupService.updateParameter();
        SummaryService.getInstance().refreshClassifiedCustomers();
    }

    private void setParameterData() {
        groupService.addGroup();
    }

    private void viewParameterData() {
        groupService.viewParameter();
    }

    private void updateParameterData() {
        groupService.updateParameter();
    }
}
