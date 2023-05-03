import util.view.InputView;
import util.view.OutputView;
import vo.Menus;
import vo.type.RootMenuName;

import java.util.ArrayList;
import java.util.Optional;

public class SmartStoreApplication {
    private static SmartStoreApplication smartStoreApplication = null;
    private final InputView inputView;
    private final OutputView outputView;
    private final ArrayList<Menus> rootMenus = new ArrayList<>();

    private SmartStoreApplication(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public static SmartStoreApplication getInstance() {

        if (smartStoreApplication == null) {
            smartStoreApplication = new SmartStoreApplication(new InputView(), new OutputView());
        }

        return smartStoreApplication;
    }

    public SmartStoreApplication test() {
        return this;
    }

    private void rootMenuInit() {
        addRootMenu();
    }

    private void addRootMenu() {
        RootMenuName.getAllNames().stream()
                .map(Menus::new)
                .forEach(rootMenus::add);
    }

    public void run() {
        rootMenuInit();
        outputView.copyRight();

        while (true) {
            outputView.choiceMenu();
            outputView.chooseOne();

            try {
                int menuNumber = inputView.getMenuNumber();
                if (menuNumber == 4) {
                    System.out.println("Program Finished");
                    break;
                }

                if (menuNumber == 1) {
                    executeParameterMenu();
                }

                if (menuNumber == 2) {
                    executeCustomerDataMenu();
                }

                if (menuNumber == 3) {
                    executeClassificationSummaryMenu();
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

        }

    }

    private void executeClassificationSummaryMenu() {
    }

    private void executeCustomerDataMenu() {
    }

    private void executeParameterMenu() {
        outputView.choiceParameterMenu();

    }


    private void setParameter() {
    }

    private void viewParameter() {
        
    }

    private void updateParameter() {
    }
}
