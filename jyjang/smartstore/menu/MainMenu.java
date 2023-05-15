package jyjang.smartstore.menu;

public class MainMenu implements Menu {

    private static MainMenu mainMenu;

    public static MainMenu getInstance() {
        if (mainMenu == null)
            mainMenu = new MainMenu();
        return mainMenu;
    }

    private MainMenu() {

    }

    private final CustomerMenu customerMenu = CustomerMenu.getInstance();
    private final GroupMenu groupMenu = GroupMenu.getInstance();
    private final SummarizedMenu summarizedMenu = SummarizedMenu.getInstance();


    @Override
    public void manage() {
        // 프로그램을 실행(유지) While
        while (true) {
            int choice = mainMenu.displayMenu(new String[] {
                    "Parameter"
                    , "Customer Data"
                    , "Classification Summary"
                    , "Quit"});

            if (choice == 1) {
                groupMenu.manage();
            } else if (choice == 2) {
                customerMenu.manage();
            } else if (choice == 3) {
                summarizedMenu.manage();
            } else if (choice == 4) {
                System.out.println("Program Finished");
                break;
            }
        }
    }
}
