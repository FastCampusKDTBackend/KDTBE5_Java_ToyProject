package controller;

import controller.menu.Menu;
import exception.InputFormatException;
import exception.InputRangeException;
import view.Input;

public class MenuController {

    private Menu menu;

    public MenuController() {

    }

    public MenuController setMenu(Menu menu) {
        this.menu = menu;
        return this;
    }

    public void start(){
        while (true) {
            int choiceMenuNumber = chooseMenu();
            if (choiceMenuNumber == menu.menuItemsMaxNum()) break;
            menu.service(choiceMenuNumber);
        }
    }

    public int chooseMenu() {
        while (true) {
            try {
                int inputMenuNumber = Input.chooseMenuNumber(menu.items());
                validateChoiceMenuNumRange(inputMenuNumber);
                return inputMenuNumber;
            } catch (InputFormatException | InputRangeException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void validateChoiceMenuNumRange(int inputNum) throws InputRangeException {
        if (inputNum < menu.menuItemsMinNum() || inputNum > menu.menuItemsMaxNum()) {
            throw new InputRangeException();
        }
    }
}
