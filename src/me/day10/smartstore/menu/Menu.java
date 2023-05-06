package me.day10.smartstore.menu;

import me.day10.smartstore.menu.exception.InputIsEndException;
import me.day10.smartstore.menu.exception.InvalidMenuException;

import java.util.InputMismatchException;

public abstract class Menu {

    protected static final Reader reader = Reader.getInstance();
    protected static final Printer printer = Printer.getInstance();
    protected static final String END_INPUT = "** Press 'end', if you want to exit! **\n";
    protected static final String GROUP_OUTPUT =
                    '\n' +
                    "Which group (GENERAL (G), VIP (V), VVIP (VV))?\n"
                    + END_INPUT;

    protected final Menu[] nextMenu;

    protected Menu(Menu... nextMenus) {
        int len = nextMenus.length;
        nextMenu = new Menu[len];
        System.arraycopy(nextMenus, 0, nextMenu, 0, len);
    }

    public abstract Menu printAndInputAndGetNextMenu();

    protected final void setBackMenu(Menu backMenu) {
        nextMenu[nextMenu.length - 1] = backMenu;
    }

    protected void print(Object s) {
        printer.print(s.toString());
    }

    protected int inputMenu() throws InputMismatchException, InvalidMenuException {
        int ret = reader.inputInteger();
        if (ret <= 0 || ret >= nextMenu.length)
            throw new InvalidMenuException("Invalid Menu Input." + " Please try again.\n");
        return ret;
    }

    protected String inputGroupName() throws InputIsEndException {
        String s = reader.inputString().toUpperCase();
        checkIfInputIsEnd(s);
        return s;
    }

    protected void checkIfInputIsEnd(String s) throws InputIsEndException {
        if (s.equals("END"))
            throw new InputIsEndException("'end' is pressed. Exit this menu.\n\n");
    }
}
