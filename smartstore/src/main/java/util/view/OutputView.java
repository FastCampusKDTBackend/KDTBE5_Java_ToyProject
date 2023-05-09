package util.view;

import domain.menu.Menu;

import java.util.Arrays;

public class OutputView {

    private OutputView() {
    }

    public static void showMenus(Menu[] menus) {
        System.out.println(generateMenuFormat(menus));
    }

    public static void showMessage(ViewMessage viewMessage) {
        System.out.println(viewMessage.getMessage());
    }

    public static void showExitGuide() {
        System.out.println("** Press '" + ViewMessage.QUIT_MENU_NAME.getMessage() + "', if you want to exit! **");
    }

    public static void chooseType(String format) {
        System.out.println("Which type (" + format + ")?  \n");
        showExitGuide();
    }

    public static void showSelectCustomerMessage(int size) {
        System.out.print("Which customer ( 1 ~ " + size + " )?");
    }

    public static void showErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    private static String generateMenuFormat(Menu[] menus) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ViewMessage.SEPERATOR.getMessage());

        Arrays.stream(menus).forEach(menu -> stringBuilder.append(menu.toString()));

        stringBuilder.append("  " + (menus.length + 1) + ". " + ViewMessage.QUIT_MENU_NAME.getMessage() + "\n");
        stringBuilder.append(ViewMessage.SEPERATOR.getMessage());
        return stringBuilder.toString();
    }

    public static void showInfo(String info) {
        System.out.println(info);
    }
}
