import domain.menu.main.MainMenu;
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

            int menuNumber = Integer.parseInt(scanner.nextLine());

            if (isFinish(menuNumber)) {
                return;
            }

            executeMenu(menuNumber);
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
                                throw new IllegalArgumentException("존재하지 않는 메뉴 번호입니다.");
                            }
                    );
        } catch (IllegalArgumentException e) {
            OutputView.viewErrorMessage(e.getMessage());
        }
    }
}
