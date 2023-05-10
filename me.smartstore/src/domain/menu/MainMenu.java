package domain.menu;

import domain.group.Groups;

import java.util.NoSuchElementException;

public class MainMenu implements Menu {

    private final CustomerMenu customerMenu = CustomerMenu.getInstance();
    private final GroupMenu groupMenu = GroupMenu.getInstance();
    private final SummaryMenu summaryMenu = SummaryMenu.getInstance();

    private static MainMenu mainMenu;

    public static MainMenu getInstance() {
        if (mainMenu == null) {
            mainMenu = new MainMenu();
        }
        return mainMenu;
    }

    private MainMenu() {}

    @Override
    public void manage() {
        while ( true ) {
            try {
                int choice = mainMenu.selectMenu(new String[] {
                        "Parameter",
                        "Customer",
                        "Classification Summary",
                        "Quit"});

                if (choice == 1) groupMenu.manage();
                if (choice == 2) customerMenu.manage();
                if (choice == 3) summaryMenu.manage();
                if (choice == 4) {
                    System.out.println("Program Finished");
                    break;
                }
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
