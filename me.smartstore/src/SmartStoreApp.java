import domain.menu.MainMenu;
import test.TestCase;

public class SmartStoreApp {
    private final MainMenu mainMenu = MainMenu.getInstance();

    private static SmartStoreApp smartStoreApp;

    public static SmartStoreApp getInstance() {
        if (smartStoreApp == null) {
            smartStoreApp = new SmartStoreApp();
        }
        return smartStoreApp;
    }

    private SmartStoreApp() {}

    public void details() {
        System.out.println("\n===========================================");
        System.out.println(" Title : SmartStore Customer Classification");
        System.out.println(" Release Date : 23.05.10");
        System.out.println(" Revision by matrixpower1004@gmail.com");
        System.out.println("===========================================\n");
    }

    public SmartStoreApp test() {
        TestCase testCase = new TestCase();
        testCase.buildTestCase();

        return this; // smartStoreApp
    }

    public void run() {
        details();
        mainMenu.manage();
    }
}
