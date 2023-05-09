package me.smartstore.menu;

import me.smartstore.exception.InputEmptyException;
import me.smartstore.exception.constant.Message;

public class MainMenu implements Menu{
    private final CustomerMenu customerMenu;
    private final GroupMenu groupMenu;
    private final SummaryMenu summaryMenu;
    private static MainMenu mainMenu;

    public static MainMenu getInstance() {
        if (mainMenu == null) {
            mainMenu = new MainMenu();
        }
        return mainMenu;
    }

    private MainMenu() {
        this.customerMenu = CustomerMenu.getInstance();
        this.groupMenu = GroupMenu.getInstance();
        this.summaryMenu = SummaryMenu.getInstance();
    }
    public void manage(){
        while (true) {
            try {
                int choice = mainMenu.chooseMenu(new String[]{
                        "Parameter",
                        "Customer Data",
                        "Classification Summary",
                        "Quit"});

                if (choice == 1) {
                    groupMenu.manage();
                } else if (choice == 2) {
                    customerMenu.manage();
                } else if (choice == 3) {
                    summaryMenu.manage();
                } else {
                    System.out.println("Program Finished");
                    break;
                }
            } catch (InputEmptyException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_EMPTY.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT.getMessage());
            }
        }
    }
}

