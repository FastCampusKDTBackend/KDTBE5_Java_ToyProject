package menu;

import exception.InputNumOfEndMenuException;
import menu.subMenu.CustomerMenu;
import menu.subMenu.GroupMenu;
import menu.subMenu.SubMenu;
import menu.subMenu.SummaryMenu;
import util.MenuManager;

import java.util.ArrayList;
import java.util.List;

public class MainMenu {

    private static final String[] MENU_ITEMS;

    static {
        MENU_ITEMS = new String[]{"Parameter", "Customer Data", "Classification Summary", "Quit"};
    }

    private final ArrayList<SubMenu> menuList;

    public MainMenu(GroupMenu groupMenu, CustomerMenu customerMenu, SummaryMenu summaryMenu) {
        menuList = new ArrayList<>(List.of(groupMenu, customerMenu, summaryMenu));
    }

    public void service() {
        while (true) {
            try {
                int choiceMenuNum = MenuManager.chooseMenu(MENU_ITEMS) - 1;
                menuList.get(choiceMenuNum).service();
            } catch (InputNumOfEndMenuException exception) {
                break;
            }
        }
    }
}
