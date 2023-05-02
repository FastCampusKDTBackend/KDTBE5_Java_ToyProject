package me.smartstore.menu;

public class CustomerMenu implements Menu{
    private static CustomerMenu customerMenu;

    public static CustomerMenu getInstance(){
        if(customerMenu == null){
            return customerMenu = new CustomerMenu();
        }
        else {
            return customerMenu;
        }
    }

    private CustomerMenu(){};

    public void manage() {
        while (true) { // 서브 메뉴 페이지를 유지하기 위한 while
            int choice = chooseMenu(new String[]{
                    "Add Customer",
                    "View Customer",
                    "Update Customer",
                    "Delete Customer",
                    "Back"});
        }
    }
}
