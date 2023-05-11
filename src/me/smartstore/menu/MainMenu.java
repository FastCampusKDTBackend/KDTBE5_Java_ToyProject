package me.smartstore.menu;

public class MainMenu implements Menu {
	private static MainMenu mainMenu;
	private final GroupMenu groupMenu = GroupMenu.getInstance();
	private final CustomerMenu customerMenu = CustomerMenu.getInstance();
	private final SummaryMenu summaryMenu = SummaryMenu.getInstance();

	private MainMenu() {
	}

	public static MainMenu getInstance() {
		if (mainMenu == null) {
			mainMenu = new MainMenu();
		}

		return mainMenu;
	}

	@Override
	public void show() {
		while (true) {
			int choice = chooseMenu(new String[] {
				"Parameter",
				"Customer Data",
				"Classification Summary",
				"Quit"
			});

			if (choice == 1) {
				groupMenu.show();
			} else if (choice == 2) {
				customerMenu.show();
			} else if (choice == 3) {
				summaryMenu.show();
			} else if (choice == 4) {
				break;
			}
		}
	}
}
