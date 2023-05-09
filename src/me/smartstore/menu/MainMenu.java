package me.smartstore.menu;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import me.smartstore.util.Message;

public class MainMenu implements Menu {
	private static final GroupMenu groupMenu = GroupMenu.getInstance();
	private static final CustomerMenu customerMenu = CustomerMenu.getInstance();
	private static final SummaryMenu summaryMenu = SummaryMenu.getInstance();

	private static MainMenu menu;

	private MainMenu() {
	}

	public static MainMenu getInstance() {
		if (menu == null) {
			menu = new MainMenu();
		}
		return menu;
	}

	@Override
	public void root() {
		while (true) {
			try {
				int choice = chooseMenu(Stream.of(RootMenu.values()).map(RootMenu::toString).toArray(String[]::new));

				Boolean isQuit = RootMenu.findByCode(choice).run();
				if (isQuit) {
					System.out.println("\nProgram Finished");
					break;
				}
			} catch (NoSuchElementException e) {
				System.out.println(Message.ERR_INVALID_INPUT_RANGE);
			}
		}
	}

	enum RootMenu {
		GROUP(1, "Group") {
			@Override
			public Boolean run() {
				groupMenu.root();
				return false;
			}
		},
		CUSTOMER(2, "Customer") {
			@Override
			public Boolean run() {
				customerMenu.root();
				return false;
			}
		},
		SUMMARY(3, "Summary") {
			@Override
			public Boolean run() {
				summaryMenu.root();
				return false;
			}
		},
		QUIT(4, "Quit") {
			@Override
			public Boolean run() {
				return Menu.back();
			}
		};

		private int code;
		private String label;

		RootMenu(int code, String label) {
			this.code = code;
			this.label = label;
		}

		public int getCode() {
			return code;
		}

		public static RootMenu findByCode(int code) {
			return Arrays.stream(RootMenu.values())
				.filter(t -> t.getCode() == code)
				.findAny()
				.orElseThrow(NoSuchElementException::new);
		}

		@Override
		public String toString() {
			return " " + this.code + ". " + this.label;
		}

		public abstract Boolean run();
	}
}
