package me.smartstore.core.view;

import static me.smartstore.enums.SmartStoreMessage.INPUT_NUMBER_OF_CUSTOMERS;
import static me.smartstore.enums.SmartStoreMessage.PRESS_END_MSG;
import static me.smartstore.exceptions.StoreErrorCode.*;
import static me.smartstore.utils.ScannerUtility.getInput;
import static me.smartstore.utils.ScannerUtility.getIntegerInputSafely;

import me.smartstore.core.domain.CustomerDTO;
import me.smartstore.core.service.CustomerService;
import me.smartstore.exceptions.StoreException;

public class CustomerMenu extends AbstractMenu {
  private static final CustomerMenu customerMenu = new CustomerMenu();
  private static final CustomerService customerService = CustomerService.getInstance();
  private static final CustomerSubMenu customerSubMenu = CustomerSubMenu.getInstance();

  private static CustomerDTO[] dtoCache = new CustomerDTO[] {};

  private CustomerMenu() {
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
      customerMenu.show();
      try {
        switch (customerMenu.selectMenuNumber()) {
            // Add Customer Data
          case 1 -> {
            int numberOfCustomers = inputNumberOfCustomers();
            for (int idx = 0; idx < numberOfCustomers; idx++) {
              System.out.println("====== Customer " + (idx + 1) + ". Info. ======\n");
              CustomerDTO newCustomer = customerSubMenu.inputCustomerInfo();
              customerService.saveNewCustomer(newCustomer);
            }
          }

            // View Customer Data
          case 2 -> showCustomerInfo();

            // Update Customer Data
          case 3 -> {
            showCustomerInfo();
            int customerNumber = inputCustomerNumber();
            Long id = dtoCache[customerNumber - 1].id();
            CustomerDTO updateDTO = customerSubMenu.inputCustomerInfo();
            customerService.updateCustomerById(id, updateDTO);
          }

            // Delete Customer Data
          case 4 -> {
            showCustomerInfo();
            int customerNumber = inputCustomerNumber();
            Long id = dtoCache[customerNumber - 1].id();
            customerService.deleteCustomerById(id);
          }

          case 5 -> {
            break loop;
          }
        }
      } catch (StoreException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  private static void showCustomerInfo() throws StoreException {
    // No. 1 => Customer{userId='TEST123', name='TEST', spentTime=null, totalPay=null, group=null}
    dtoCache = customerService.findAll();
    if (dtoCache.length == 0) {
      throw new StoreException(NO_CUSTOMER);
    }

    System.out.println("======= Customer Info. =======\n");
    for (int idx = 0; idx < dtoCache.length; idx++) {
      System.out.println("No. " + (idx + 1) + " => " + dtoCache[idx]);
    }
  }

  private static int inputNumberOfCustomers() throws StoreException {
    while (true) {
      System.out.println(INPUT_NUMBER_OF_CUSTOMERS + "\n" + PRESS_END_MSG);
      String input = getInput();
      if ("end".equalsIgnoreCase(input)) throw new StoreException(INPUT_END);
      try {
        int numberOfCustomers = Integer.parseInt(input);
        if (numberOfCustomers == -1) {
          throw new StoreException(INVALID_FORMAT);
        }
        return numberOfCustomers;
      } catch (NumberFormatException e) {
        System.out.println(INVALID_FORMAT.getMessage());
      } catch (StoreException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  private static int inputCustomerNumber() throws StoreException {
    while (true) {
      System.out.println("Which customer ( 1 ~ " + customerService.getNumberOfCustomers() + " )? ");
      try {
        int customerNumber = getIntegerInputSafely();
        if (customerNumber < 1 || customerNumber > customerService.getNumberOfCustomers()) {
          throw new StoreException(INVALID_FORMAT);
        }
        return customerNumber;
      } catch (StoreException e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
