package me.smartstore.menus;

import me.smartstore.core.CustomerManager;
import me.smartstore.exceptions.StoreException;

public class ClassificationSummaryMenu extends AbstractMenu {
  private static final ClassificationSummaryMenu classificationSummaryMenu =
      new ClassificationSummaryMenu();

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
          case 1 -> CustomerManager.printSummary();
          case 2 -> CustomerManager.printSummary(OrderBy.NAME);
          case 3 -> CustomerManager.printSummary(OrderBy.SPENT_TIME);
          case 4 -> CustomerManager.printSummary(OrderBy.TOTAL_PAY_AMOUNT);
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
