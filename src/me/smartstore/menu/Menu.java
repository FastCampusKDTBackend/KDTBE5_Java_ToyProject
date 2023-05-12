package me.smartstore.menu;

import me.smartstore.menu.exception.InputIsEndException;

import java.util.InputMismatchException;

public abstract class Menu {

    private static final Reader reader = Reader.getInstance();
    private static final Printer printer = Printer.getInstance();
    protected static final String END_INPUT = "** Press 'end', if you want to exit! **\n";

    private Menu[] nextMenus;
    private Menu BACK_MENU;
    private Menu prevMenu;

    protected Menu() {}

    public abstract Menu printAndInputAndGetNextMenu();

    protected abstract void setNextMenus();

    protected final Menu getNextMenu(int idx) { return nextMenus[idx]; }

    protected final Menu getBackMenu() { return BACK_MENU; }

    public final void setNextMenus(Menu... nextMenus) {
        int len = nextMenus.length;
        if (this.nextMenus == null)
            this.nextMenus = new Menu[len];
        System.arraycopy(nextMenus, 0, this.nextMenus, 0, len);
        BACK_MENU = this.nextMenus[len - 1];
    }

    public final void setPrevMenu(Menu menu) { this.prevMenu = menu; }

    protected final Menu getPrevMenu() { return this.prevMenu; }

    protected void print(Object s) {
        printer.print(s.toString());
    }

    protected int inputMenuIdx() throws InputMismatchException {
        int menu = inputIntegerRanged(1, nextMenus.length);
        return menu - 1;
    }

    protected final String inputStringOrEnd() throws InputIsEndException {
        String s = reader.inputString();
        checkIfInputIsEnd(s);
        return s;
    }

    protected void checkIfInputIsEnd(String s) throws InputIsEndException {
        if (s.equalsIgnoreCase("end"))
            throw new InputIsEndException("'end' is pressed. Exit this menu.\n\n");
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

    protected int inputIntegerRanged(int fromInclusive, int toInclusive) throws InputMismatchException {
        int i = reader.inputInteger();
        if (i < fromInclusive || i > toInclusive)
            throw new InputMismatchException("Invalid Input. Please try again.\n");
        return i;
    }
}
