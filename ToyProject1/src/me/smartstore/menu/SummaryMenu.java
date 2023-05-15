package me.smartstore.menu;

import java.util.Comparator;
import me.smartstore.domain.customer.Customer;
import me.smartstore.domain.customer.Customers;
import me.smartstore.domain.customer.ClassifiedCustomersGroup;
import me.smartstore.exception.InputEmptyException;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;

public class SummaryMenu extends Menu {
	private static SummaryMenu summaryMenu;
	
	public static SummaryMenu getInstance() {
		if (summaryMenu == null)
			summaryMenu = new SummaryMenu(); 
		return summaryMenu;
	}

	private Customers allCustomers = Customers.getInstance();
	private ClassifiedCustomersGroup classifiedCusGroup = ClassifiedCustomersGroup.getInstance();

	public void manageSummaryMenu() {
		this.classifiedCusGroup = this.allCustomers.classified();
		while (true) {
			int choice = dispMenus(new String[] { "Summary", "Summary (Sorted By Name)", "Summary (Sorted By Spent Time)", "Summary (Sorted By Total Payment)", "Back" });
			if (choice == 1) {
				dispSummary();
				continue;
			} 
			if (choice == 2) {
				manageSort(
						Comparator.comparing(Customer::getName)
						.thenComparing(Customer::getUserId));
				continue;
			} 
			if (choice == 3) {
				manageSort(
						Comparator.comparing(Customer::getSpentTime)
						.thenComparing(Customer::getName));
				continue;
			} 
			if (choice == 4) {
				manageSort(
						Comparator.comparing(Customer::getTotalPay)
						.thenComparing(Customer::getName));
				continue;
			} 
			if (choice == 5)
				break; 
		} 
	}
  
	public void dispSummary() {
		if (this.classifiedCusGroup == null) {
			System.out.println("입력해주세요.");
			return;
		} 
		this.classifiedCusGroup.print();
	}
	
	public void manageSort(Comparator<Customer> comparator) {
		while (true) {
			String strOrder = chooseSortOrder();
			if (strOrder == null)
				return; 
			if (strOrder.equals("종료"))
				return; 
			try {
				OrderType orderType = OrderType.valueOf(strOrder).replaceFullName();
				if (orderType == OrderType.ASCENDING) {
					this.classifiedCusGroup.sort(comparator);
				} else if (orderType == OrderType.DESCENDING) {
					this.classifiedCusGroup.sort(comparator.reversed());
				} else {
					throw new InputRangeException();
				} 
			} catch (IllegalArgumentException|InputRangeException e) {
				System.out.println("\n입력 범위가 잘못되었습니다.");
			} 
			dispSummary();
		} 
	}
  
	public String chooseSortOrder() {
		while (true) {
			try {
				System.out.print("Which order (ASCENDING (A), DESCENDING (D))? ");
				String choice = nextLine("END");
				if (choice.equals(""))
					throw new InputEmptyException(); 
				try {
					OrderType orderType = OrderType.valueOf(choice).replaceFullName();
					for (int i = 0; i < OrderType.size(); i++) {
						if (orderType == OrderType.values()[i])
							return choice; 
					} 
					throw new InputRangeException();
				} catch (IllegalArgumentException e) {
					System.out.println("입력 타입이 잘못되었습니다.");
				} 
			} catch (InputEmptyException e) {
				System.out.println("입력해주세요.");
			} catch (InputRangeException e) {
				System.out.println("입력 범위가 잘못되었습니다.");
			} catch (InputEndException e) {
				System.out.println("이 메뉴를 종료합니다.");
				return null;
			} 
		} 
	}
}
