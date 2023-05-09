import domain.menu.main.MainMenu;
import util.common.ErrorMessage;
import util.view.InputScanner;
import util.view.OutputView;

import java.util.Scanner;

public class SmartStoreApplication {
    private static SmartStoreApplication smartStoreApplication = null;
    private final Scanner scanner = InputScanner.get();

    private SmartStoreApplication() {
    }

    public static SmartStoreApplication getInstance() {
        if (smartStoreApplication == null) {
            smartStoreApplication = new SmartStoreApplication();
        }

        return smartStoreApplication;
    }

    public void run() {
        while (true) {
            OutputView.viewMenus(MainMenu.values());
            OutputView.chooseMenu();

            int menuNumber = getMenuNumber();

            if (isFinish(menuNumber)) {
                return;
            }

            executeMenu(menuNumber);
        }
    }

    private int getMenuNumber() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (RuntimeException runtimeException) {
                OutputView.viewErrorMessage(ErrorMessage.INVALID_INPUT);
            }
        }
    }

    private boolean isFinish(int menuNumber) {
        return MainMenu.isQuit(menuNumber);
    }

    private void executeMenu(int menuNumber) {
        try {
            MainMenu.findByNumber(menuNumber)
                    .ifPresentOrElse(
                            MainMenu::execute,
                            () -> {
                                throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT);
                            }
                    );
        } catch (IllegalArgumentException e) {
            OutputView.viewErrorMessage(e.getMessage());
        }
    }
}
