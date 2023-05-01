package me.day10.smartstore.menu;

public class GroupMenu implements Menu {

    private static class InstanceHolder {
        private static final GroupMenu INSTANCE = new GroupMenu();
    }
    private GroupMenu() {}
    public static GroupMenu getInstance() { return InstanceHolder.INSTANCE; }

    private static final String OUTPUT =
                    '\n' +
                    "==========Group Menu==========" + '\n' +
                    "1. " + "Set Parameter" + '\n' +
                    "2. " + "View Parameter" + '\n' +
                    "3. " + "Update Parameter" + '\n' +
                    "4. " + "Back" + '\n' +
                    "==============================" + '\n' +
                    "Choose One: ";

    private final Menu[] nextMenu = {
            null,
    };

    @Override
    public Menu printAndInputAndGetNextMenu() {
        while (true) {
            try {
                print(OUTPUT);
                int menu = inputMenu();
                return nextMenu[menu];
            } catch (Exception e) {
                print(e.getMessage());
            }
        }
    }

    private void print(String s) {}

    private int inputMenu() {
        return 0;
    }
}
