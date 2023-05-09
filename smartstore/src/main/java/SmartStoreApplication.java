import domain.menu.main.MainMenu;

public class SmartStoreApplication {
    private static SmartStoreApplication smartStoreApplication = null;

    private SmartStoreApplication() {
    }

    public static SmartStoreApplication getInstance() {
        if (smartStoreApplication == null) {
            smartStoreApplication = new SmartStoreApplication();
        }
        return smartStoreApplication;
    }

    public void run() {
        MainMenu.executeMain();
    }
}
