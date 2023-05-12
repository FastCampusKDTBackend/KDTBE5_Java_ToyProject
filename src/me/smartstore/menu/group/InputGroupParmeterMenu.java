package me.smartstore.menu.group;

import me.smartstore.menu.Menu;
import me.smartstore.menu.exception.InputIsEndException;

import java.util.InputMismatchException;

public abstract class InputGroupParmeterMenu extends Menu {

    private final String PARAMETER_INPUT;

    InputGroupParmeterMenu(String PARAMETER_INPUT) { this.PARAMETER_INPUT = PARAMETER_INPUT; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        while (true) {
            print(PARAMETER_INPUT);
            try {
                Integer property = inputZeroOrPositiveIntegerOrEnd();
                setTempProperty(property);
                return getPrevMenu();
            } catch (InputMismatchException e) {
                print(e.getMessage());
            } catch (InputIsEndException e) {
                print(e.getMessage());
                return getPrevMenu();
            }
        }
    }

    @Override
    protected void setNextMenus() {}

    protected abstract void setTempProperty(Integer property);
}
