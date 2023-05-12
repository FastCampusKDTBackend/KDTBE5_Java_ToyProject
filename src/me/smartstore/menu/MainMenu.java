package me.smartstore.menu;

public class MainMenu implements Menu {

    private static MainMenu mainMenu;

    private final CustomerMenu customerMenu;
    private final GroupMenu groupMenu;
    private final SummaryMenu summaryMenu;

    public static MainMenu getInstance(){
        if(mainMenu == null){
            mainMenu = new MainMenu();
        }
        return mainMenu;
    }

    private MainMenu() {
        this.customerMenu = CustomerMenu.getInstance();
        this.groupMenu = GroupMenu.getInstance();
        this.summaryMenu = SummaryMenu.getInstance();
    }

    @Override
    public void manage() {
        while ( true ) {
            int choice = mainMenu.chooseMenu(new String[] {
                    "Parameter",
                    "Customer",
                    "Classification Summary",
                    "Quit"});

            if (choice == 1) groupMenu.manage();
            else if (choice == 2) customerMenu.manage();
            else if (choice == 3) summaryMenu.manage();
            else {
                System.out.println("Program Finished");
                break;
            }
        }
    }
}
