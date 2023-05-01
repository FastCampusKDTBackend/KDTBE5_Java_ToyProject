package me.day10.smartstore.menu;

import me.day10.smartstore.group.GroupType;

import java.util.Arrays;

public class ViewParameterMenu implements Menu {

    private static class InstanceHolder {
        private static final ViewParameterMenu INSTANCE = new ViewParameterMenu();
    }
    private ViewParameterMenu() {}
    public static ViewParameterMenu getInstance() { return ViewParameterMenu.InstanceHolder.INSTANCE; }

    private static final Reader reader = Reader.getInstance();
    private static final Printer printer = Printer.getInstance();
    private static final String GROUP_OUTPUT =
            "Which group (GENERAL (G), VIP (V), VVIP (VV))?\n" +
                    "** Press 'end', if you want to exit! **\n";

    @Override
    public Menu printAndInputAndGetNextMenu() {
        while (true) {
            print(GROUP_OUTPUT);
            try {
                String groupName = InputGroupName();
                GroupType groupType = GroupType.getGroupTypeByString(groupName);
                print(groupType.toString());
            } catch (BackMenuException e) {
                print(e.getMessage());
                return GroupMenu.getInstance();
            } catch (InvalidGroupTypeException e) {
                print(e.getMessage());
            }
        }
    }

    private void print(String s) {
        printer.print(s);
    }

    private String InputGroupName() throws BackMenuException {
        String s = reader.inputString().toUpperCase();
        if (s.equals("END"))
            throw new BackMenuException("'end' is pressed. Exit this menu.\n");
        return s;
    }
}
