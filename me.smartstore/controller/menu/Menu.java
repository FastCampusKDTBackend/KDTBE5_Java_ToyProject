package controller.menu;

public interface Menu {

    String[] items();

    int menuItemsMaxNum();

    int menuItemsMinNum();

    void service(int menuNum);
}
