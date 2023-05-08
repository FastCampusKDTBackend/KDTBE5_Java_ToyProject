package me.smartstore.menu;

import me.smartstore.customer.Customers;
import me.smartstore.group.Groups;

public class SummaryMenu implements Menu{
	private static SummaryMenu summaryMenu;

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

			} else if (choice == 2) {

			} else if (choice == 3) {

			} else if (choice == 4) {

			} else if (choice == 5) {
				break;
			}
		}
	}
}
