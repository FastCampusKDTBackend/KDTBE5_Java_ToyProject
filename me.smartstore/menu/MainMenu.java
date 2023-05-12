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
            int choice = mainMenu.chooseMenu(new String[] {
                    "Parameter",
                    "Customer",
                    "Classification Summary",
                    "Quit"});

            if (choice == 1) {
                editParameter();
            }
            else if (choice == 2) {
                customerMenu.manage();
            }
            else if (choice == 3) {
                summaryMenu.manage();
            }
            else {
                System.out.println("Program Finished");
                saveData();
                break;
            }
        }
    }

    public void editParameter() {
        while ( true ) {
            int choice = mainMenu.chooseMenu(new String[]{
                    "Set Parameter",
                    "View Parameter",
                    "Update Parameter",
                    "Back"});

            if (choice == 1) {
                groupMenu.setParameter();
            }
            else if (choice == 2) {
            }
            else if (choice == 3) {
            }
            else {
                break;
            }
        }
    }

    public void saveData() {
        System.out.println("Data Saved");
    }
}
