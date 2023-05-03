package me.smartstore.menu;

import java.util.stream.Stream;

public class CustomerMenu implements Menu{
    private static CustomerMenu customerMenu;

    private CustomerMenu() {
    }

    public static CustomerMenu getInstance(){
        if (customerMenu == null) {
            customerMenu = new CustomerMenu();
        }
        return customerMenu;
    }

    @Override
    public void root() {
//        while (true) {
//            int choice = chooseMenu(Stream.of(RootMenu.values()).map(m -> m.toString()));
//
//            Boolean isBack = RootMenu.findByCode(choice).run();
//            if (isBack) {
//                break;
//            }
//        }
    }

    enum RootMenu{

    }

}
