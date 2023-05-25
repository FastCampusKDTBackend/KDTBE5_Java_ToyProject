package me.smartstore.menu;
public class MainMenu implements Menu{
    private static MainMenu mainMenu;
    private static GroupMenu groupMenu = GroupMenu.getInstance();
    private static CustomerMenu customerMenu = CustomerMenu.getInstance();
    private static SummaryMenu summaryMenu = SummaryMenu.getInstance();

    public static MainMenu getInstance(){
        if(mainMenu == null){
            return mainMenu = new MainMenu();
        }
        else {
            return mainMenu;
        }
    }

    private MainMenu(){};

    @Override
    public void manage() {
        while ( true ) {
            int choice = mainMenu.chooseMenu(new String[]{
                    "Parameter",
                    "Customer",
                    "Classification Summary",
                    "Quit"
            });
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
