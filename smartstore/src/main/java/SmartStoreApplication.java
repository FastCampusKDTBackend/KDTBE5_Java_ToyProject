import util.view.InputView;
import util.view.OutputView;

public class SmartStoreApplication {
    private static SmartStoreApplication smartStoreApplication = null;
    private final InputView inputView;
    private final OutputView outputView;

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

    public void run() {
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
