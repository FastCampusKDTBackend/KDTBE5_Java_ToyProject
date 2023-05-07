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

    protected Menu[] nextMenus;
    private Menu BACK_MENU;
    private Menu prevMenu;

    protected Menu() {}

    public abstract Menu printAndInputAndGetNextMenu();

    protected final Menu getBackMenu() { return BACK_MENU; }

    protected abstract void setNextMenus();

    protected final void setNextMenus(Menu... nextMenus) {
        int len = nextMenus.length;
        if (this.nextMenus == null)
            this.nextMenus = new Menu[len];
        System.arraycopy(nextMenus, 1, this.nextMenus, 1, len - 1);
        BACK_MENU = this.nextMenus[len - 1];
    }

    protected void print(Object s) {
        printer.print(s.toString());
    }

    protected int inputMenu() throws InputMismatchException, InvalidMenuException {
        int ret = reader.inputInteger();
        if (ret <= 0 || ret >= nextMenus.length)
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

    protected final String inputStringOrEnd() throws InputIsEndException {
        String s = reader.inputString();
        checkIfInputIsEnd(s.toUpperCase());
        return s;
    }

    protected Integer inputZeroOrPositiveIntegerOrEnd() throws InputIsEndException, InputMismatchException {
        String s = inputStringOrEnd();

        try {
            int i = Integer.parseInt(s);
            if (i < 0)
                throw new InputMismatchException("Invalid Input. Please try again.\n");
            return i;
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Invalid Format for Input. Please try again.\n");
        }
    }

    public void setPrevMenu(Menu menu) {
        this.prevMenu = menu;
    }

    protected Menu getPrevMenu() {
        return this.prevMenu;
    }
}
