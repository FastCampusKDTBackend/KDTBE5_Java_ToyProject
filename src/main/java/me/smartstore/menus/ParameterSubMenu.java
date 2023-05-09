package me.smartstore.menus;

import me.smartstore.domain.Parameter;
import me.smartstore.exceptions.StoreErrorCode;
import me.smartstore.exceptions.StoreException;
import me.smartstore.utils.ScannerHolder;

public class ParameterSubMenu extends AbstractMenu {
  private static final ParameterSubMenu parameterSubMenu = new ParameterSubMenu();

  private ParameterSubMenu() {
    super(new String[] {"Minimum Spent Time", "Minimum Total PayAmount", "Back"});
  }

  public static Parameter inputParameter() {
    Integer minimumSpentTime = null;
    Integer minimumTotalPayAmount = null;

    while (true) {
      parameterSubMenu.show();

      try {
        switch (parameterSubMenu.selectMenuNumber()) {
          case 1 -> {
            while (true) {
              System.out.println(
                  "Input Minimum Spent Time.\n** Press 'end', if you want to exit! **");

              String input = ScannerHolder.getInput();

              if ("end".equals(input)) break;

              try {
                minimumSpentTime = Integer.parseInt(input);
                break;
              } catch (NumberFormatException e) {
                System.out.println(StoreErrorCode.INVALID_FORMAT.getMessage());
              }
            }
          }

          case 2 -> {
            while (true) {
              System.out.println(
                  "Input Minimum Total Pay Amount.\n** Press 'end', if you want to exit! **");

              String input = ScannerHolder.getInput();

              if ("end".equals(input)) break;

              try {
                minimumTotalPayAmount = Integer.parseInt(input);
                break;
              } catch (NumberFormatException e) {
                System.out.println(StoreErrorCode.INVALID_FORMAT.getMessage());
              }
            }
          }

          case 3 -> {
            return new Parameter(minimumSpentTime, minimumTotalPayAmount);
          }
        }
      } catch (StoreException e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
