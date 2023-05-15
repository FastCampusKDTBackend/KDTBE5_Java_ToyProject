package me.smartstore.menu;

import me.smartstore.domain.customer.Customers;
import me.smartstore.exception.InputEmptyException;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;
import me.smartstore.domain.group.*;
import me.smartstore.constant.GroupType;

public class GroupMenu extends Menu {
	private static GroupMenu groupMenu;
	
	public static GroupMenu getInstance() {
		if (groupMenu == null)
			groupMenu = new GroupMenu(); 
		return groupMenu;
	}
	
	Groups allGroups = Groups.getInstance();
	Customers allCustomers = Customers.getInstance();
	
	public String chooseGroup() {
		while (true) {
			try {
				System.out.print("Which group (GENERAL (G), VIP (V), VVIP (VV))? ");
				String choice = nextLine("END");
				if (choice.equals(""))
					throw new InputEmptyException(); 
				return choice;
			} catch (InputEmptyException e) {
				System.out.println("입력해주세요.");
			} catch (IllegalArgumentException e) {
				System.out.println("다시 입력해주세요.");
			} catch (InputEndException e) {
				System.out.println("이 메뉴를 종료합니다.");
				return null;
			} 
		} 
	}
	  
	public void manageParameterMenu() {
		while (true) {
			int choice = dispMenus(new String[] { "Set Parameter", "View Parameter", "Update Parameter", "Back" });
			if (choice == 1) {
				setParameter();
				continue;
			} 
			if (choice == 2) {
				viewParameter();
				continue;
			} 
			if (choice == 3) {
				updateParameter();
				continue;
			} 
			if (choice == 4)
				break; 
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
				System.out.println("\n다시 입력해주세요.");
				continue;
			} 
			Group grp = this.allGroups.find(groupType);
			if (grp != null && grp.getParam() != null) {
				System.out.println("\n" + grp.getType() + " group already exists.");
				System.out.println("\n" + grp);
				continue;
			} 
			Parameter param = new Parameter();
			while (true) {
				int choice = dispMenus(new String[] { "Minimum Spent Time", "Minimum Total Pay", "Back" });
				if (choice == 1) {
					setParameterMinimumSpentTime(param);
					continue;
				} 
				if (choice == 2) {
					setParameterMinimumTotalPay(param);
					continue;
				} 
				if (choice == 3)
					break; 
			} 
			this.allGroups.add(new Group(groupType, param));
			this.allCustomers.refresh(this.allGroups);
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
				System.out.println("\n다시 입력해주세요");
				continue;
			} 
			Group grp = this.allGroups.find(groupType);
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
				System.out.println("\n다시 입력해주세요.");
				return;
			} 
			Group grp = this.allGroups.find(groupType);
			if (grp.getParam() == null) {
				System.out.println("\n파라미터를 지정해주세요.");
				return;
			} 
			System.out.println("\n" + grp);
			Parameter param = grp.getParam();
			while (true) {
				int choice = dispMenus(new String[] { "Minimum Spent Time", "Minimum Total Pay", "Back" });
				if (choice == 1) {
					setParameterMinimumSpentTime(param);
					continue;
				} 
				if (choice == 2) {
					setParameterMinimumTotalPay(param);
					continue;
				} 
				if (choice == 3)
					break; 
			} 
			this.allGroups.update(new Group(groupType, param));
			this.allCustomers.refresh(this.allGroups);
			System.out.println("\n" + grp);
		} 
	}
	
	public void setParameterMinimumSpentTime(Parameter param) {
		while (true) {
			try {
				System.out.print("\nInput Minimum Spent Time: ");
				int minimumSpentTime = Integer.parseInt(nextLine("END"));
				if (minimumSpentTime < 0)
					throw new InputRangeException(); 
				param.setMinimumSpentTime(Integer.valueOf(minimumSpentTime));
				return;
			} catch (NumberFormatException e) {
				System.out.println("다시 입력해주세요.");
			} catch (InputRangeException e) {
				System.out.println("입력 범위가 잘못되었습니다.");
			} catch (InputEndException e) {
				System.out.println("이 메뉴를 종료합니다.");
				break;
			} 
		} 
	}

	public void setParameterMinimumTotalPay(Parameter param) {
		while (true) {
			try {
				System.out.print("\nInput Minimum Total Pay: ");
				int minimumTotalPay = Integer.parseInt(nextLine("END"));
				if (minimumTotalPay < 0)
					throw new InputRangeException(); 
				param.setMinimumTotalPay(Integer.valueOf(minimumTotalPay));
				return;
			} catch (NumberFormatException e) {
				System.out.println("입력 형식이 잘못되었습니다.");
			} catch (InputRangeException e) {
				System.out.println("입력 범위가 잘못되었습니다.");
			} catch (InputEndException e) {
				System.out.println("이 메뉴를 종료합니다.");
				break;
			} 
		} 
	}

}
