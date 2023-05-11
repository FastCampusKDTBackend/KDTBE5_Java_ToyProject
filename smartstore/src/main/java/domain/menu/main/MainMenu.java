package domain.menu.main;

import domain.menu.Menu;
import domain.menu.main.classification.ClassificationMenu;
import domain.menu.main.customer.CustomerMenu;
import domain.menu.main.parameter.ParameterMenu;
import util.common.ErrorMessage;
import util.view.InputScanner;
import util.view.OutputView;
import util.view.ViewMessage;

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

    public static void executeMain() {
        while (true) {
            OutputView.showMenus(MainMenu.values());
            OutputView.showMessage(ViewMessage.INPUT_MENU);

            int menuNumber = getMenuNumber();

            if (isFinish(menuNumber)) {
                return;
            }

            executeSubMenu(menuNumber);
        }
    }

    private static int getMenuNumber() {
        while (true) {
            try {
                return Integer.parseInt(InputScanner.get().nextLine());
            } catch (RuntimeException runtimeException) {
                OutputView.showErrorMessage(ErrorMessage.INVALID_INPUT);
            }
        }
    }

    private static boolean isFinish(int menuNumber) {
        return MainMenu.isQuit(menuNumber);
    }

    private static void executeSubMenu(int menuNumber) {
        try {
            MainMenu.findByNumber(menuNumber)
                    .ifPresentOrElse(
                            MainMenu::execute,
                            () -> {
                                throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT);
                            }
                    );
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e.getMessage());
        }
    }

    private static Optional<MainMenu> findByNumber(int menuNumber) {
        return Arrays.stream(MainMenu.values())
                .filter(mainMenu -> mainMenu.menuNumber == menuNumber)
                .findFirst();
    }

    private static boolean isQuit(int menuNumber) {
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
