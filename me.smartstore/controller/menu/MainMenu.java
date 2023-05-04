package controller.menu;

import controller.MenuController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainMenu implements Menu {

    private static final String[] MENU_ITEMS_NAME;
    private static final ArrayList<Menu> MENU_LIST;

    private static final int MENU_ITEMS_MAX_NUM;
    private static final int MENU_ITEMS_MIN_NUM;

    private static MainMenu mainMenu;

    static {
        MENU_ITEMS_NAME = new String[]{"Parameter", "Customer Data",
                "Classification Summary", "Quit"};

        MENU_LIST = new ArrayList<>(
                List.of(GroupMenu.getInstance(), CustomerMenu.getInstance(), SummaryMenu.getInstance()));

        MENU_ITEMS_MAX_NUM = MENU_ITEMS_NAME.length;
        MENU_ITEMS_MIN_NUM = 1;
    }

    public static MainMenu getInstance() {
        if (Objects.isNull(mainMenu)) {
            mainMenu = new MainMenu();
        }
        return mainMenu;
    }

    private MainMenu() {

    }

    @Override
    public String[] items() {
        return MENU_ITEMS_NAME;
    }

    @Override
    public int menuItemsMaxNum() {
        return MENU_ITEMS_MAX_NUM;
    }

    @Override
    public int menuItemsMinNum() {
        return MENU_ITEMS_MIN_NUM;
    }

    // 메뉴 컨트롤러 재사용을 위해 선택된 Menu 를 갈아 끼우며 동작
    // GroupMenu.getInstance(), CustomerMenu.getInstance(), SummaryMenu.getInstance()
    @Override
    public void service(int menuNum) {
        MenuController menuController = new MenuController();
        menuController.setMenu(MENU_LIST.get(menuNum - 1)).start();
    }
}
