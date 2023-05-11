package domain.menu.main.parameter;

import domain.menu.Menu;

import java.util.Arrays;
import java.util.Optional;

public enum ParameterMenu implements Menu {
    SET(1, "Set Parameter", SetParameterExecute.getMethod()),
    VIEW(2, "View Parameter", ViewParameterExecute.getMethod()),
    UPDATE(3, "Update Parameter", UpdateParameterExecute.getMethod());

    private final int menuNumber;
    private final String menuName;
    private final Runnable runnable;

    ParameterMenu(int menuNumber, String menuName, Runnable runnable) {
        this.menuNumber = menuNumber;
        this.menuName = menuName;
        this.runnable = runnable;
    }

    public static void view() {
    }

    public static Optional<ParameterMenu> findByNumber(int menuNumber) {
        return Arrays.stream(ParameterMenu.values())
                .filter(mainMenu -> mainMenu.menuNumber == menuNumber)
                .findFirst();
    }

    public static boolean isQuit(int menuNumber) {
        return ParameterMenu.values().length + 1 == menuNumber;
    }

    @Override
    public void execute() {
        runnable.run();
    }

    @Override
    public String toString() {
        return "  " + menuNumber + ". " + menuName + "\n";
    }
}
