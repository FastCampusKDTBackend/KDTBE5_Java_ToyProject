package me.smartstore.menu;

import me.smartstore.customers.Customers;
import me.smartstore.exception.InputEmptyException;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;
import me.smartstore.groups.Group;
import me.smartstore.groups.GroupType;
import me.smartstore.groups.Groups;
import me.smartstore.groups.Parameter;

public class GroupMenu extends Menu {
  private static GroupMenu instance;

  public static GroupMenu getInstance() {
    if (instance == null)
      instance = new GroupMenu();
    return instance;
  }

  private Groups allGroups = Groups.getInstance();
  private Customers allCustomers = Customers.getInstance();

  public String chooseGroup() {
    while (true) {
      try {
        System.out.print("소비자 그룹 선택(일반 (G), 우수 (V), 최우수 (VV)) : ");
        String choice = nextLine("END");
        if (choice.isEmpty())
          throw new InputEmptyException();
        return choice;
      } catch (InputEmptyException e) {
        System.out.println("입력값은 빈 값이 될 수 없습니다.");
      } catch (IllegalArgumentException e) {
        System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
      } catch (InputEndException e) {
        System.out.println("메뉴로 돌아갑니다.");
        return null;
      }
    }
  }

  public void manageParameterMenu() {
    while (true) {
      int choice = displayMenus(new String[] { "매개변수 설정", "매개변수 확인", "매개변수 갱신", "되돌아가기" });
      switch (choice) {
        case 1:
          setParameter();
          break;

        case 2:
          viewParameter();
          break;

        case 3:
          updateParameter();
          break;

        case 4:
          return;

        default:
          System.out.println("잘못 고르셨습니다. 다시 시도하세요.");
      }
    }
  }

  public void setParameter() {
    while (true) {
      GroupType groupType;
      String strGroup = chooseGroup();
      if (strGroup == null)
        return;
      try {
        groupType = GroupType.valueOf(strGroup).replaceFullName();
      } catch (IllegalArgumentException e) {
        System.out.println("\n잘못 입력하셨습니다. 다시 시도하세요.");
        continue;
      }
      Group grp = allGroups.find(groupType);
      if (grp != null && grp.getParam() != null) {
        System.out.println("\n" + grp.getType() + "그룹이 존재합니다.");
        System.out.println("\n" + grp);
        continue;
      }
      Parameter param = new Parameter();
      while (true) {
        int choice = displayMenus(new String[] { "최소 소비시간", "최소 소비금액", "되돌아가기" });
        switch (choice) {
          case 1:
            setParameterMinimumSpentTime(param);
            break;

          case 2:
            setParameterMinimumTotalPay(param);
            break;

          case 3:
            break;

          default:
            System.out.println("잘못 선택하셨습니다. 다시 시도하세요.");
        }
        if (choice == 3)
          break;
      }
      allGroups.add(new Group(groupType, param));
      allCustomers.refresh(allGroups);
      System.out.println("\n" + grp);
    }
  }

  public void viewParameter() {
    while (true) {
      GroupType groupType;
      String strGroup = chooseGroup();
      if (strGroup == null)
        return;
      try {
        groupType = GroupType.valueOf(strGroup).replaceFullName();
      } catch (IllegalArgumentException e) {
        System.out.println("\n입력 형식이 잘못되었습니다. 다시 시도해주세요.");
        continue;
      }
      Group grp = allGroups.find(groupType);
      System.out.println();
      System.out.println(grp);
    }
  }

  public void updateParameter() {
    while (true) {
      GroupType groupType;
      String strGroup = chooseGroup();
      if (strGroup == null)
        return;
      try {
        groupType = GroupType.valueOf(strGroup).replaceFullName();
      } catch (IllegalArgumentException e) {
        System.out.println("\n잘못 입력하셨습니다. 다시 시도해주세요.");
        return;
      }
      Group grp = allGroups.find(groupType);
      if (grp.getParam() == null) {
        System.out.println("\n매개변수가 없습니다. 먼저 설정 후 시도해주세요.");
        return;
      }
      System.out.println("\n" + grp);
      Parameter param = grp.getParam();
      while (true) {
        int choice = displayMenus(new String[] { "최소 소비시간", "최소 소비금액", "되돌아가기" });
        switch (choice) {
          case 1:
            setParameterMinimumSpentTime(param);
            break;

          case 2:
            setParameterMinimumTotalPay(param);
            break;

          case 3:
            break;
            
          default:
            System.out.println("잘못 선택하셨습니다. 다시 시도해주세요.");
        }
        if (choice == 3)
          break;
      }
      allGroups.update(new Group(groupType, param));
      allCustomers.refresh(allGroups);
      System.out.println("\n" + grp);
    }
  }

  public void setParameterMinimumSpentTime(Parameter param) {
    while (true) {
      try {
        System.out.print("\n최소 소비금액 : ");
        int minimumSpentTime = Integer.parseInt(nextLine("END"));
        if (minimumSpentTime < 0)
          throw new InputRangeException();
        param.setMinimumSpentTime(Integer.valueOf(minimumSpentTime));
        return;
      } catch (NumberFormatException e) {
        System.out.println("입력값은 빈 값이 될 수 없습니다.");
      } catch (InputRangeException e) {
        System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
      } catch (InputEndException e) {
        System.out.println("메뉴로 돌아갑니다.");
        break;
      }
    }
  }

public void setParameterMinimumTotalPay(Parameter param) {
    while (true) {
      try {
        System.out.print("\n최소 소비금액 : ");
        int minimumTotalPay = Integer.parseInt(nextLine("END"));
        if (minimumTotalPay < 0)
          throw new InputRangeException();
        param.setMinimumTotalPay(Integer.valueOf(minimumTotalPay));
        return;
      } catch (NumberFormatException e) {
        System.out.println("잘못 입력되었습니다. 다시 시도해주세요.");
      } catch (InputRangeException e) {
        System.out.println("잘못 입력되었습니다. 다시 시도해주세요.");
      } catch (InputEndException e) {
        System.out.println("메뉴로 돌아갑니다.");
        break;
      }
    }
  }
}
