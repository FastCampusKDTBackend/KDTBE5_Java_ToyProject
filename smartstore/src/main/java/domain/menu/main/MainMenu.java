package domain.menu.main;

import domain.menu.Menu;
import domain.menu.main.classification.ClassificationMenu;
import domain.menu.main.customer.CustomerMenu;
import domain.menu.main.parameter.ParameterMenu;

import java.util.Arrays;
import java.util.Optional;

public enum MainMenu implements Menu {
    PARAMETER(1, "Parameter", MainMenuExecute.getMethod(ParameterMenu.class)),
    CUSTOMER(2, "Customer Data", MainMenuExecute.getMethod(CustomerMenu.class)),
    CLASSIFICATION(3, "Classification Summary", MainMenuExecute.getMethod(ClassificationMenu.class));


    private final int menuNumber;
    private final String menuName;
    private final Runnable runnable;

    MainMenu(int menuNumber, String menuName, Runnable runnable) {
        this.menuNumber = menuNumber;
        this.menuName = menuName;
        this.runnable = runnable;
    }

    public static Optional<MainMenu> findByNumber(int menuNumber) {
        return Arrays.stream(MainMenu.values())
                .filter(mainMenu -> mainMenu.menuNumber == menuNumber)
                .findFirst();
    }

    public static boolean isQuit(int menuNumber) {
        return MainMenu.values().length + 1 == menuNumber;
    }

    @Override
    public String toString() {
        return "  " + menuNumber + ". " + menuName + "\n";
    }

    @Override
    public void execute() {
        runnable.run();
    }
}
