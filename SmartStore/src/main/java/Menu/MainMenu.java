package Menu;

public class MainMenu {

    private static MainMenu allMainMenu;

    public static MainMenu getInstance() {
        if (allMainMenu == null) {
            allMainMenu = new MainMenu();
        }
        return allMainMenu;
    }

    private MainMenu() {

    }
}