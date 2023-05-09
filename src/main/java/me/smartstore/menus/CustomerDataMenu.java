package me.smartstore.menus;

import me.smartstore.core.CustomerManager;
import me.smartstore.exceptions.StoreException;

public class CustomerDataMenu extends AbstractMenu {
  private static final CustomerDataMenu customerDataMenu = new CustomerDataMenu();

  private CustomerDataMenu() {
    super(
        new String[] {
          "Add Customer Data",
          "View Customer Data",
          "Update Customer Data",
          "Delete Customer Data",
          "Back"
        });
  }

  public static void launch() {
    loop:
    while (true) {
      customerDataMenu.show();
      try {
        switch (customerDataMenu.selectMenuNumber()) {
          case 1 -> CustomerManager.launchCustomerManager(1);
          case 2 -> CustomerManager.launchCustomerManager(2);
          case 3 -> CustomerManager.launchCustomerManager(3);
          case 4 -> CustomerManager.launchCustomerManager(4);
          case 5 -> {
            break loop;
          }
        }
      } catch (StoreException e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
