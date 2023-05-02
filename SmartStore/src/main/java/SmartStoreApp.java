public class SmartStoreApp {
    private static SmartStoreApp allSmartStoreApp;

    public static SmartStoreApp getInstance() {
        if (allSmartStoreApp == null) {
            allSmartStoreApp = new SmartStoreApp();
        }
        return allSmartStoreApp;
    }

    private SmartStoreApp() {

    }

    public void details() {

    }

    public SmartStoreApp test() {

        return this;
    }

    public void run() {

    }
}
