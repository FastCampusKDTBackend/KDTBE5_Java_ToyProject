package me.smartstore.menu;

import me.smartstore.customer.Customers;
import me.smartstore.group.Group;
import me.smartstore.group.Groups;

public class SummaryMenu implements Menu{
	private static SummaryMenu summaryMenu;
	private static Groups allGroups = Groups.getInstance();
	private static Customers allCustomers = Customers.getInstance();

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
		while(true) {
			int choice = chooseMenu(new String[]{
				"Summary",
				"Summary (Sorted By Name)",
				"Summary (Sorted By Spent Time)",
				"Summary (Sorted By Total Payment)",
				"Back"
			});

			if (choice == 1) {
				summary();
			} else if (choice == 2) {

			} else if (choice == 3) {

			} else if (choice == 4) {

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
			System.out.println("Group : " + selectGroup.getGroupType() +
				"( Time : " + selectGroup.getMinHours() + ", Pay : " + selectGroup.getMinPay());
			System.out.println("==============================");
			System.out.println(allCustomers.findByGroup(selectGroup));
			System.out.println();
		}
	}
}
