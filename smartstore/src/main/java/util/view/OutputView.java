package util.view;

import domain.group.GroupType;
import domain.menu.Menu;
import util.common.ViewMessage;

import java.util.Arrays;

public class OutputView {
    public static void viewMenus(Menu[] menus) {
        System.out.println(generateMenuFormat(menus));
    }

    public static void chooseMenu() {
        System.out.print(ViewMessage.INPUT_MENU);
    }

    public static void viewExitGuide() {
        System.out.println("** Press '" +ViewMessage.EXIT_CHOICE+"', if you want to exit! **");
    }
    public static void chooseGroup() {
        System.out.println("Which group ("+ GroupType.generateFormatForView() +")?  \n");
        viewExitGuide();
    }

    public static void chooseParameterForSetting() {
        System.out.println("==============================\n" +
                " 1. Minimum Spent Time\n" +
                " 2. Minimum Total Pay\n" +
                " 3. Back\n" +
                "==============================\n");

        chooseMenu();
    }

    private static String generateMenuFormat(Menu[] menus) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ViewMessage.SEPERATOR);

        Arrays.stream(menus).forEach(menu -> {
            stringBuilder.append(menu.toString());
        });

        stringBuilder.append("  " + (menus.length + 1) + ". " + ViewMessage.QUIT + "\n");
        stringBuilder.append(ViewMessage.SEPERATOR);
        return stringBuilder.toString();
    }

}
