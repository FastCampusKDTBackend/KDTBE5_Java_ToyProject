package menu;

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
            int choice = chooseMenu(new String[] {
                    "Parameter",
                    "Customer",
                    "Classification Summary",
                    "Quit"});

            if (choice == 1) {groupMenu.manage();}
            else if (choice == 2) {customerMenu.manage();}
            else if (choice == 3) {summaryMenu.manage();}
            else {
                System.out.println("Program Finished");
                break;
            }
        }
    }
}
