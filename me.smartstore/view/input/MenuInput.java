package view.input;

import util.Console;

public class MenuInput {

    public static final String MENU_PRINT_FORMAT = "%d. %s\n";

    public static final String INPUT_MENU_CHOICE = "메뉴 번호를 입력해주세요: "; // choose one:


    public static int chooseMenuNumber(String[] menu) {
        System.out.println("\n==============================");
        for (int i = 0; i < menu.length; i++) {
            System.out.printf(MENU_PRINT_FORMAT, i+1, menu[i]);
        }
        System.out.println("==============================");
        System.out.print(INPUT_MENU_CHOICE);
        return Console.readLineToInteger();
    }
}
