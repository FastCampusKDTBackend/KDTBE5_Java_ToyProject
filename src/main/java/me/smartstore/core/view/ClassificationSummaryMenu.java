package me.smartstore.core.view;

import static me.smartstore.enums.SmartStoreMessage.INPUT_SORT_ORDER;
import static me.smartstore.enums.SmartStoreMessage.PRESS_END_MSG;
import static me.smartstore.enums.SortBy.*;
import static me.smartstore.exceptions.StoreErrorCode.INPUT_END;
import static me.smartstore.utils.StoreUtility.convertInputStrToSortOrder;

import me.smartstore.core.service.CustomerService;
import me.smartstore.enums.SortOrder;
import me.smartstore.exceptions.StoreException;
import me.smartstore.utils.ScannerUtility;

public class ClassificationSummaryMenu extends AbstractMenu {
  private static final ClassificationSummaryMenu classificationSummaryMenu =
      new ClassificationSummaryMenu();

  private static final CustomerService customerService = CustomerService.getInstance();

  private ClassificationSummaryMenu() {
    super(
        new String[] {
          "Summary",
          "Summary (Sorted By Name)",
          "Summary (Sorted By Spent Time)",
          "Summary (Sorted By Total Pay Amount)",
          "Back"
        });
  }

  public static void launch() {
    loop:
    while (true) {
      classificationSummaryMenu.show();
      try {
        switch (classificationSummaryMenu.selectMenuNumber()) {
          case 1 -> customerService.displayClassificationSummary();
          case 2 -> customerService.displayClassificationSummary(NAME, inputSortOrder());
          case 3 -> customerService.displayClassificationSummary(SPENT_TIME, inputSortOrder());
          case 4 -> customerService.displayClassificationSummary(PAY_AMOUNT, inputSortOrder());
          case 5 -> {
            break loop;
          }
        }
      } catch (StoreException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  private static SortOrder inputSortOrder() {
    while (true) {
      System.out.println(INPUT_SORT_ORDER + "\n" + PRESS_END_MSG);
      try {
        String input = ScannerUtility.getInput();
        if ("end".equalsIgnoreCase(input)) throw new StoreException(INPUT_END);
        return convertInputStrToSortOrder(input);
      } catch (StoreException e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
