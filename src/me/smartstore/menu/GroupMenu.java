package me.smartstore.menu;

import me.smartstore.customer.Customers;
import me.smartstore.group.Groups;

public class GroupMenu implements Menu{
	private static GroupMenu groupMenu;

	private GroupMenu() {}

	public static GroupMenu getInstance() {
		if (groupMenu == null) {
			groupMenu = new GroupMenu();
		}

		return groupMenu;
	}

	@Override
	public void show() {
		while (true) {
			int choice = chooseMenu(new String[]{
				"Set Parameter",
				"View Parameter",
				"Update Parameter",
				"Back"
			});

			if (choice == 1) {

			} else if (choice == 2) {

			} else if (choice == 3) {

			} else if (choice == 4) {
				break;
			}
		}
	}
}
