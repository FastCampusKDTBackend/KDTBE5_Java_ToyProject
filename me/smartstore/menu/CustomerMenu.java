package me.smartstore.menu;

import me.smartstore.customers.Customer;
import me.smartstore.customers.Customers;
import me.smartstore.exception.ArrayEmptyException;
import me.smartstore.exception.InputEmptyException;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;
import me.smartstore.groups.Group;
import me.smartstore.groups.Groups;

public class CustomerMenu extends Menu {
  private static CustomerMenu customerMenu;

  public static CustomerMenu getInstance() {
    if (customerMenu == null)
      customerMenu = new CustomerMenu();
    return customerMenu;
  }

  private Groups allGroups = Groups.getInstance();
  private Customers allCustomers = Customers.getInstance();

  public void manageCustomerMenu() {
    while (true) {
      int choice = displayMenus(new String[] { "고객 데이터 추가", "고객 데이터 보기", "고객 데이터 갱신",
          "고객 데이터 삭제", "되돌아가기" });
      if (choice == 1) {
        int size = 0;
        size = getCustomerSizeToAdd();
        setCustomerData(size);
        continue;
      }
      if (choice == 2) {
        viewCustomerData();
        continue;
      }
      if (choice == 3) {
        updateCustomerData();
        continue;
      }
      if (choice == 4) {
        deleteCustomerData();
        continue;
      }
      if (choice == 5)
        break;
    }
  }

  public void setCustomerData(int size) {
    for (int i = 0; i < size; i++) {
      Customer customer = new Customer();
      System.out.println("\n====== 고객 " + (i + 1) + " 정보 ======"); 
      while (true) {
        int choice = displayMenus(
            new String[] { "고객 이름", "ID", "상주 시간", "소비 금액", "뒤로" });
        if (choice == 1) {
          setCustomerName(customer);
          continue;
        }
        if (choice == 2) {
          setCustomerUserId(customer);
          continue;
        }
        if (choice == 3) {
          setCustomerSpentTime(customer);
          continue;
        }
        if (choice == 4) {
          setCustomerTotalPay(customer);
          continue;
        }
        if (choice == 5)
          break;
      }
      Group grp = allGroups.findGroupFor(customer); 
      if (grp == null) {
        customer.setGroup(null);
      } else if (!grp.equals(customer.getGroup())) {
        customer.setGroup(grp);
      }
      allCustomers.add(customer); 
    }
  }

  public void viewCustomerData() {
    if (allCustomers.size() == 0) { 
      System.out.println("고객이 없습니다.");
      return;
    }
    System.out.println("\n======== 고객정보 ========");
    for (int i = 0; i < allCustomers.size(); i++) { 
      Customer cust = allCustomers.get(i); 
      if (cust != null) {
        System.out.println("No. " + (i + 1) + " => " + cust); 
      } else {
        System.out.println("null");
      }
    }
  }

  public void updateCustomerData() {
    viewCustomerData();
    int custNo = 0;
    try {
      custNo = findCustomer();
    } catch (ArrayEmptyException | InputEndException e) {
      return;
    }
    Customer customer = allCustomers.get(custNo - 1);
    if (customer == null)
      return;
    while (true) {
      int choice = displayMenus(
          new String[] { "고객 이름", "ID", "상주 시간", "소비 금액", "뒤로" });
      if (choice == 1) {
        setCustomerName(customer);
        continue;
      }
      if (choice == 2) {
        setCustomerUserId(customer);
        continue;
      }
      if (choice == 3) {
        setCustomerSpentTime(customer);
        continue;
      }
      if (choice == 4) {
        setCustomerTotalPay(customer);
        continue;
      }
      if (choice == 5)
        break;
    }
    Group grp = allGroups.findGroupFor(customer); 
    if (grp == null) {
      customer.setGroup(null);
    } else if (!grp.equals(customer.getGroup())) {
      customer.setGroup(grp);
    }
  }

  public void deleteCustomerData() {
    viewCustomerData();
    int custNo = 0;
    try {
      custNo = findCustomer();
    } catch (ArrayEmptyException | InputEndException e) {
      return;
    }
    Customer customer = allCustomers.get(custNo - 1); 
    System.out.println(customer);
    allCustomers.pop(custNo - 1); 
    viewCustomerData();
  }

  private int getCustomerSizeToAdd() {
    while (true) {
      try {
        System.out.print("입력할 고객 수 : ");
        int size = Integer.parseInt(nextLine("END"));
        if (size < 0)
          throw new InputRangeException();
        return size;
      } catch (NumberFormatException e) {
        System.out.println("잘못된 포맷입니다. 다시 시도하세요.");
      } catch (InputRangeException e) {
        System.out.println("잘못된 입력입니다. 다시 시도하세요.");
      } catch (InputEndException e) {
        System.out.println("메뉴로 돌아갑니다.");
        return -1;
      }
    }
  }

public void setCustomerName(Customer customer) {
    while (true) {
      try {
        System.out.print("\n고객 이름 입력 : ");
        String name = nextLine("END");
        if (name == null || name.equals(""))
          throw new InputEmptyException();
        customer.setName(name);
        return;
      } catch (InputEmptyException e) {
        System.out.println("입력값은 빈 값이 될 수 없습니다.. 다시 시도하세요.");
      } catch (InputEndException e) {
        System.out.println("메뉴로 돌아갑니다.");
        break;
      }
    }
  }

  public void setCustomerUserId(Customer customer) {
    while (true) {
      try {
        System.out.print("\n고객 ID 입력 : ");
        String userId = nextLine("END");
        if (userId == null || userId.equals(""))
          throw new InputEmptyException();
        customer.setUserId(userId);
        return;
      } catch (InputEmptyException e) {
        System.out.println("입력값은 빈 값이 될 수 없습니다.. 다시 시도하세요.");
      } catch (InputEndException e) {
        System.out.println("메뉴로 돌아갑니다.");
        break;
      }
    }
  }

  public void setCustomerSpentTime(Customer customer) {
    while (true) {
      try {
        System.out.print("\n고객 상주 시간 : ");
        int spentTime = Integer.parseInt(nextLine("END"));
        if (spentTime < 0)
          throw new InputRangeException();
        customer.setSpentTime(Integer.valueOf(spentTime));
        return;
      } catch (InputRangeException e) {
        System.out.println("입력값은 빈 값이 될 수 없습니다.. 다시 시도하세요.");
      } catch (InputEndException e) {
        System.out.println("메뉴로 돌아갑니다.");
        break;
      }
    }
  }

  public void setCustomerTotalPay(Customer customer) {
    while (true) {
      try {
        System.out.print("\n입력된 고객의 총 사용 금액은 : ");
        int totalPay = Integer.parseInt(nextLine("END"));
        if (totalPay < 0)
          throw new InputRangeException();
        customer.setTotalPay(Integer.valueOf(totalPay));
        return;
      } catch (NumberFormatException e) {
        System.out.println("잘못된 포맷입니다. 다시 시도하세요.");
      } catch (InputRangeException e) {
        System.out.println("잘못된 입력입니다. 다시 시도하세요.");
      } catch (InputEndException e) {
        System.out.println("메뉴로 돌아갑니다.");
        break;
      }
    }
  }

  public int findCustomer() throws ArrayEmptyException, InputEndException {
    int size = allCustomers.size(); 
    if (size == 0)
      throw new ArrayEmptyException();
    while (true) {
      try {
        System.out.print("\n어느 고객 ( 1 ~ " + size + " )? ");
        int custNo = Integer.parseInt(nextLine());
        if (custNo < 1 || custNo > size)
          throw new InputRangeException();
        return custNo;
      } catch (NumberFormatException e) {
        System.out.println("\n잘못된 포맷입니다. 다시 시도하세요.");
      } catch (InputRangeException e) {
        System.out.println("\n잘못된 입력입니다. 다시 시도하세요.");
      } catch (InputEndException e) {
        System.out.println("메뉴로 돌아갑니다.");
        break;
      }
    }
    return size;
  }
}
