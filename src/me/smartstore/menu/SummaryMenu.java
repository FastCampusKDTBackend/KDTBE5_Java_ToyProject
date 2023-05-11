package me.smartstore.menu;

import java.util.Comparator;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputValidException;
import me.smartstore.group.Group;
import me.smartstore.group.Groups;
import me.smartstore.util.Message;

public class SummaryMenu implements Menu {
	private static SummaryMenu summaryMenu;
	private final Groups allGroups = Groups.getInstance();
	private final Customers allCustomers = Customers.getInstance();

	private SummaryMenu() {

	}

	public static SummaryMenu getInstance() {
		if (summaryMenu == null) {
			summaryMenu = new SummaryMenu();
		}

		return summaryMenu;
	}

	@Override
	public void show() {
		while (true) {
			int choice = chooseMenu(new String[] {
				"Summary",
				"Summary (Sorted By Name)",
				"Summary (Sorted By Spent Time)",
				"Summary (Sorted By Total Payment)",
				"Back"
			});

			if (choice == 1) {
				summary();
			} else if (choice == 2) {
				summaryToSort(allCustomers.comparatorByName());
			} else if (choice == 3) {
				summaryToSort(allCustomers.comparatorByTime());
			} else if (choice == 4) {
				summaryToSort(allCustomers.comparatorByPay());
			} else if (choice == 5) {
				break;
			}
		}
	}

	private void summary() {
		Group selectGroup;
		for (int i = 0; i < allGroups.size(); i++) {
			selectGroup = allGroups.get(i);

			System.out.println("==============================");
			System.out.println(selectGroup.toStringByOneLine());
			System.out.println("==============================");
			System.out.println(allCustomers.findByGroup(selectGroup));
			System.out.println();
		}
	}

	private void summaryToSort(Comparator<Customer> comparator) {
		while (true) {
			try {
				System.out.println("Which order (ASCENDING(A), DESCENDING (D))?");
				String sortType = nextLine(Message.END_MSG);

				if (!sortType.equals("A") && !sortType.equals("D") && !sortType.equals("ASCENDING") &&
					!sortType.equals("DESCENDING"))
					throw new InputValidException();

				Group selectGroup;
				for (int i = 0; i < allGroups.size(); i++) {
					selectGroup = allGroups.get(i);

					System.out.println("==============================");
					System.out.println(selectGroup.toStringByOneLine());
					System.out.println("==============================");
					System.out.println(allCustomers.sort(allCustomers.findByGroup(selectGroup), comparator, sortType));
					System.out.println();
				}

				break;
			} catch (InputEndException e) {
				System.out.println(e.getMessage());
				break;
			} catch (InputValidException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
