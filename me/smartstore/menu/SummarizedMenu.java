package me.smartstore.menu;

import java.util.Comparator;
import me.smartstore.customers.ClassifiedCustomersGroup;
import me.smartstore.customers.Customer;
import me.smartstore.customers.Customers;
import me.smartstore.exception.InputEmptyException;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;

public class SummarizedMenu extends Menu {
  private static SummarizedMenu summarizedMenu;

  public static SummarizedMenu getInstance() {
    if (summarizedMenu == null)
      summarizedMenu = new SummarizedMenu();
    return summarizedMenu;
  }

  private Customers allCustomers = Customers.getInstance();

  private ClassifiedCustomersGroup classifiedCusGroup = ClassifiedCustomersGroup.getInstance();

  public void manageSummaryMenu() {
    classifiedCusGroup = allCustomers.classified();
    while (true) {
      int choice = displayMenus(new String[] {
          "요약",
          "요약(이름별 정렬)",
          "요약(상주시간별 정렬)",
          "요약(사용금액별 정렬)",
          "되돌아가기"
      });
      if (choice == 1) {
        displaySummery();
      } else if (choice == 2) {
        manageSort(Comparator.comparing(Customer::getName).thenComparing(Customer::getUserId));
      } else if (choice == 3) {
        manageSort(Comparator.comparing(Customer::getSpentTime).thenComparing(Customer::getName));
      } else if (choice == 4) {
        manageSort(Comparator.comparing(Customer::getTotalPay).thenComparing(Customer::getName));
      } else if (choice == 5) {
        break;
      }
    }
  }

  public void displaySummery() {
    if (classifiedCusGroup == null) {
      System.out.println("입력값은 공백이 될 수 없습니다. 다시 시도해주세요.");
      return;
    }
    classifiedCusGroup.print();
  }

  public void manageSort(Comparator<Customer> comparator) {
    while (true) {
      String strOrder = chooseSortOrder();
      if (strOrder == null) {
        return;
      }
      if (strOrder.equals("END")) {
        return;
      }
      try {
        OrderType orderType = OrderType.valueOf(strOrder).replaceFullName();
        if (orderType == OrderType.ASCENDING) {
          classifiedCusGroup.sort(comparator);
        } else if (orderType == OrderType.DESCENDING) {
          classifiedCusGroup.sort(comparator.reversed());
        } else {
          throw new InputRangeException();
        }
      } catch (IllegalArgumentException | InputRangeException e) {
        System.out.println("\n잘못 입력하셨습니다. 다시 시도하세요.");
      }
      displaySummery();
    }
  }

  public String chooseSortOrder() {
    while (true) {
      try {
        System.out.print("정렬순서 (ASCENDING (A), DESCENDING (D)): ");
        String choice = nextLine("END");
        if (choice.equals("")) {
          throw new InputEmptyException();
        }
        try {
          OrderType orderType = OrderType.valueOf(choice).replaceFullName();
          for (OrderType value : OrderType.values()) {
            if (orderType == value) {
              return choice;
            }
          }
          throw new InputRangeException();
        } catch (IllegalArgumentException e) {
          System.out.println("입력값은 공백이 될 수 없습니다. 다시 시도해주세요.");
        }
      } catch (InputEmptyException e) {
        System.out.println("입력값은 공백이 될 수 없습니다. 다시 시도해주세요.");
      } catch (InputRangeException e) {
        System.out.println("잘못 입력하셨습니다. 다시 시도해주세요.");
      } catch (InputEndException e) {
        System.out.println("메뉴를 닫습니다.");
        return null;
      }
    }
  }
}
