package me.smartstore.domain.menu;

public class CustomerMenu implements Menu {
    private static CustomerMenu customerMenu;

    public static CustomerMenu getInstance() {
        if (customerMenu == null) {
            customerMenu = new CustomerMenu();
        }
        return customerMenu;
    }

    private CustomerMenu() {}

    @Override
    public void manage() {
        System.out.println("고객 메뉴입니다.");
    }
}
