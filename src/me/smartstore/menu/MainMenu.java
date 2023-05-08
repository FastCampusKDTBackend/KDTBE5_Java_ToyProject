package me.smartstore.menu;

public class MainMenu implements Menu{
	private static MainMenu mainMenu;
	private static GroupMenu groupMenu = GroupMenu.getInstance();


	private MainMenu() {}

	public static MainMenu getInstance() {
		if (mainMenu == null) {
			mainMenu = new MainMenu();
		}

		return mainMenu;
	}

	@Override
	public void show() {
		while(true) {
			int choice = chooseMenu(new String[]{
				"Parameter",
				"Customer Data",
				"Classification Summary",
				"Quit"
			});

			if (choice == 1) {
				groupMenu.show();
			} else if (choice == 2) {

			} else if (choice == 3) {

			} else if (choice == 4) {
				break;
			}
		}
	}
}
