package Menu;

public class MainMenu implements Menu{

    private final CustomerMenu customerMenu = CustomerMenu.getInstance();
    private final ParameterMenu groupMenu = ParameterMenu.getInstance();
    private final SummaryMenu summaryMenu = SummaryMenu.getInstance();

    private static MainMenu allMainMenu;

    public static MainMenu getInstance() {
        if (allMainMenu == null) {
            allMainMenu = new MainMenu();
        }
        return allMainMenu;
    }

    private MainMenu() {

    }

    @Override
    public void manage() {
        while ( true ) { 
            int choice = allMainMenu.chooseMenu(new String[] {
                    "Parameter",
                    "Customer",
                    "Classification Summary",
                    "Quit"});

            if (choice == 1) groupMenu.manage();
            else if (choice == 2) customerMenu.manage();
            else if (choice == 3) summaryMenu.manage();
            else { // choice == 4
                System.out.println("Program Finished");
                break;
            }
        }
    }
}