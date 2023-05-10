package me.smartstore.core.view;

import static me.smartstore.enums.SmartStoreMessage.*;
import static me.smartstore.exceptions.StoreErrorCode.*;

import me.smartstore.core.domain.CustomerDTO;
import me.smartstore.exceptions.StoreErrorCode;
import me.smartstore.exceptions.StoreException;
import me.smartstore.utils.ScannerUtility;

public class CustomerSubMenu extends AbstractMenu {
  private static final CustomerSubMenu customerSubMenu = new CustomerSubMenu();

  private CustomerSubMenu() {
    super(
        new String[] {
          "Customer Name", "Customer ID", "Customer Spent Time", "Customer Total Pay Amount", "Back"
        });
  }

  public static CustomerSubMenu getInstance() {
    return customerSubMenu;
  }

  public CustomerDTO inputCustomerInfo() {
    String customerName = null;
    String customerID = null;
    Integer customerSpentTime = null;
    Integer customerPayAmount = null;

    loop:
    while (true) {
      customerSubMenu.show();

      try {
        switch (customerSubMenu.selectMenuNumber()) {
          case 1 -> customerName = customerSubMenu.inputCustomerName();
          case 2 -> customerID = customerSubMenu.inputCustomerId();
          case 3 -> customerSpentTime = customerSubMenu.inputCustomerSpentTime();
          case 4 -> customerPayAmount = customerSubMenu.inputCustomerPayAmount();
          case 5 -> {
            break loop;
          }
        }
      } catch (StoreException e) {
        System.out.println(e.getMessage());
        if (e.getErrorCode() == INPUT_END) break;
      }
    }

    return CustomerDTO.of(customerName, customerID, customerSpentTime, customerPayAmount);
  }

  private String inputCustomerName() throws StoreException {
    System.out.println(INPUT_CUSTOMER_NAME + "\n" + PRESS_END_MSG);
    String input = ScannerUtility.getInput();
    if ("end".equalsIgnoreCase(input)) throw new StoreException(INPUT_END);
    return input;
  }

  private String inputCustomerId() throws StoreException {
    System.out.println(INPUT_CUSTOMER_ID + "\n" + PRESS_END_MSG);
    String input = ScannerUtility.getInput();
    if ("end".equalsIgnoreCase(input)) throw new StoreException(INPUT_END);
    return input;
  }

  private int inputCustomerSpentTime() throws StoreException {
    while (true) {
      System.out.println(INPUT_CUSTOMER_SPENT_TIME + "\n" + PRESS_END_MSG);
      String input = ScannerUtility.getInput();
      if ("end".equalsIgnoreCase(input)) throw new StoreException(INPUT_END);
      try {
        return Integer.parseInt(input);
      } catch (NumberFormatException e) {
        System.out.println(StoreErrorCode.INVALID_FORMAT.getMessage());
      }
    }
  }

  private int inputCustomerPayAmount() throws StoreException {
    while (true) {
      System.out.println(INPUT_CUSTOMER_PAY_AMOUNT + "\n" + PRESS_END_MSG);
      String input = ScannerUtility.getInput();
      if ("end".equalsIgnoreCase(input)) throw new StoreException(INPUT_END);
      try {
        return Integer.parseInt(input);
      } catch (NumberFormatException e) {
        System.out.println(StoreErrorCode.INVALID_FORMAT.getMessage());
      }
    }
  }
}
