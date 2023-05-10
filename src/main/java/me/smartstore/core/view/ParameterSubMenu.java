package me.smartstore.core.view;

import me.smartstore.core.domain.CustomerGroup;
import me.smartstore.core.domain.Parameter;
import me.smartstore.core.service.CustomerGroupService;
import me.smartstore.enums.CustomerType;
import me.smartstore.exceptions.StoreErrorCode;
import me.smartstore.exceptions.StoreException;
import me.smartstore.utils.ScannerUtility;

import static me.smartstore.enums.SmartStoreMessage.*;
import static me.smartstore.enums.SmartStoreMessage.PRESS_END_MSG;
import static me.smartstore.exceptions.StoreErrorCode.*;
import static me.smartstore.utils.StoreUtility.convertInputStrToCustomerType;

public class ParameterSubMenu extends AbstractMenu {

  private static final CustomerGroupService customerGroupService =
      CustomerGroupService.getInstance();
  private static final ParameterSubMenu parameterSubMenu = new ParameterSubMenu();

  private ParameterSubMenu() {
    super(new String[] {"Minimum Spent Time", "Minimum Pay Amount", "Back"});
  }

  public static void launch(int parameterMenuNumber) {
    while (true) {
      System.out.println(INPUT_CUSTOMER_GROUP_MSG + "\n" + PRESS_END_MSG);

      try {
        String input = ScannerUtility.getInput().toUpperCase();

        if ("end".equalsIgnoreCase(input)) return;
        if ("".equals(input)) throw new StoreException(NULL_INPUT);

        CustomerType inputCustomerType = convertInputStrToCustomerType(input);

        switch (parameterMenuNumber) {

            // Set Parameter
          case 1 -> {
            Parameter inputParameter = inputParameter();
            CustomerGroup customerGroup =
                customerGroupService.setParameter(inputCustomerType, inputParameter);
            System.out.println(customerGroup);
          }

            // View Parameter
          case 2 -> System.out.println(customerGroupService.find(inputCustomerType));

            // Update Parameter
          case 3 -> {
            CustomerGroup customerGroup = customerGroupService.find(inputCustomerType);
            if (customerGroup.getParameter() == null) {
              throw new StoreException(NO_PARAMETER);
            }
            Parameter inputParameter = inputParameter();
            customerGroup = customerGroupService.updateParameter(inputCustomerType, inputParameter);
            System.out.println(customerGroup);
          }
        }

      } catch (StoreException e) {
        System.out.println(e.getMessage());
        if (e.getErrorCode() == NO_PARAMETER) return;
      }
    }
  }

  private static Parameter inputParameter() {
    Integer minimumSpentTime = null;
    Integer minimumPayAmount = null;

    while (true) {
      parameterSubMenu.show();

      try {
        switch (parameterSubMenu.selectMenuNumber()) {
          case 1 -> minimumSpentTime = inputMinimumSpentTime();
          case 2 -> minimumPayAmount = inputMinimumPayAmount();
          case 3 -> {
            return new Parameter(minimumSpentTime, minimumPayAmount);
          }
        }
      } catch (StoreException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  private static int inputMinimumSpentTime() throws StoreException {
    while (true) {
      System.out.println(INPUT_MINIMUM_SPENT_TIME_MSG + "\n" + PRESS_END_MSG);

      String input = ScannerUtility.getInput();

      if ("end".equals(input)) throw new StoreException(INPUT_END);

      try {
        return Integer.parseInt(input);
      } catch (NumberFormatException e) {
        System.out.println(StoreErrorCode.INVALID_FORMAT.getMessage());
      }
    }
  }

  private static int inputMinimumPayAmount() throws StoreException {
    while (true) {
      System.out.println(INPUT_MINIMUM_PAY_AMOUNT_MSG + "\n" + PRESS_END_MSG);

      String input = ScannerUtility.getInput();

      if ("end".equals(input)) throw new StoreException(INPUT_END);

      try {
        return Integer.parseInt(input);
      } catch (NumberFormatException e) {
        System.out.println(StoreErrorCode.INVALID_FORMAT.getMessage());
      }
    }
  }
}
