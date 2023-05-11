package me.smartstore.menu;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import me.smartstore.customer.ComparatorByName;
import me.smartstore.customer.ComparatorBySpentTime;
import me.smartstore.customer.ComparatorByTotalPayment;
import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.InputExitException;
import me.smartstore.group.Group;
import me.smartstore.group.Groups;
import me.smartstore.util.Message;
import me.smartstore.util.SortOrder;

public class SummaryMenu implements Menu {
	private static final Groups groups = Groups.getInstance();
	private static final Customers customers = Customers.getInstance();

	private static SummaryMenu summaryMenu;

	private SummaryMenu() {
	}

	public static SummaryMenu getInstance() {
		if (summaryMenu == null) {
			summaryMenu = new SummaryMenu();
		}
		return summaryMenu;
	}

	/***** Menu 선택 loop *****/
	@Override
	public void root() {
		while (true) {
			try {
				int choice = chooseMenu(Stream.of(RootMenu.values()).map(RootMenu::toString).toArray(String[]::new));

				Boolean isBack = RootMenu.findByCode(choice).run();
				if (isBack) {
					break;
				}
			} catch (NoSuchElementException e) {
				System.out.println(Message.ERR_INVALID_INPUT_RANGE);
			}
		}
	}

	protected SortOrder chooseSortOrder() {
		while (true) {
			try {
				System.out.println("\nWhich order (ASCENDING (A), DESCENDING (D))?");
				String choice = nextUpperLine(Message.EXIT_FLAG);

				return SortOrder.findByInput(choice);
			} catch (InputExitException e) {
				System.out.println(Message.INFO_INPUT_EXIT_FLAG);
				return null;
			} catch (NoSuchElementException e) {
				System.out.println(Message.ERR_INVALID_INPUT_RANGE);
			}
		}
	}

	/*****RootMenu 수행 메서드 *****/
	public void summary() {
		for (int i = 0; i < groups.size(); i++) {
			Group group = groups.get(i);
			System.out.println(group.toSummaryTitle());

			// Summary
			Customer[] customerArray = customers.arrayByGroupType(group.getGroupType());

			// Null Print
			if (customerArray.length == 0) {
				System.out.println("Null.");
				continue;
			}

			// Summary Print
			Arrays.stream(customerArray).forEach(System.out::println);
		}
	}

	public void sortedSummary(Comparator comparator) {
		for (int i = 0; i < groups.size(); i++) {
			Group group = groups.get(i);
			System.out.println(group.toSummaryTitle());

			// Summary
			Customer[] customerArray = customers.arrayByGroupType(group.getGroupType());

			// Null Print
			if (customerArray.length == 0) {
				System.out.println("Null.");
				continue;
			}

			// Summary Sort&Print
			Arrays.stream(customerArray)
				.sorted(comparator)
				.forEach(System.out::println);
		}
	}

	/***** Menu enum *****/
	enum RootMenu {
		BY_GROUP(1, "Summary") {
			@Override
			public Boolean run() {
				summaryMenu.summary();
				return false;
			}
		},
		BY_NAME(2, "Summary (Sorted By Name)") {
			@Override
			public Boolean run() {
				while (true) {
					SortOrder choosedOrder = summaryMenu.chooseSortOrder();
					if (choosedOrder == null) {    // input 'end'
						break;
					}

					summaryMenu.sortedSummary(new ComparatorByName(choosedOrder));
				}

				return false;
			}
		},
		BY_SPENT_TIME(3, "Summary (Sorted By Spent Time)") {
			@Override
			public Boolean run() {
				while (true) {
					SortOrder choosedOrder = summaryMenu.chooseSortOrder();
					if (choosedOrder == null) {    // input 'end'
						break;
					}

					summaryMenu.sortedSummary(new ComparatorBySpentTime(choosedOrder));
				}

				return false;
			}
		},
		BY_TOTAL_PAYMENT(4, "Summary (Sorted By Total Payment)") {
			@Override
			public Boolean run() {
				while (true) {
					SortOrder choosedOrder = summaryMenu.chooseSortOrder();
					if (choosedOrder == null) {    // input 'end'
						break;
					}

					summaryMenu.sortedSummary(new ComparatorByTotalPayment(choosedOrder));
				}

				return false;
			}
		},
		BACK(5, "Back") {
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
			return " " + code + ". " + label;
		}

		public abstract Boolean run();
	}
}
