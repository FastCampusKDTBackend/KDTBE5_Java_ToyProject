package me.smartstore.menu;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.ElementDuplicateException;
import me.smartstore.exception.InputExitException;
import me.smartstore.exception.InputFormatException;
import me.smartstore.group.Groups;
import me.smartstore.util.Message;

public class CustomerMenu implements Menu {
	private static final Groups groups = Groups.getInstance();
	private static final Customers customers = Customers.getInstance();

	private static CustomerMenu customerMenu;

	private CustomerMenu() {
	}

	public static CustomerMenu getInstance() {
		if (customerMenu == null) {
			customerMenu = new CustomerMenu();
		}
		return customerMenu;
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

	/*****RootMenu 수행 메서드(add, view, update, delete) *****/
	private void addCustomer() {
		while (true) {
			try {
				System.out.println("\nHow many customers to input?");
				int count = customerMenu.nextInt(Message.EXIT_FLAG);

				int num = 1;
				while (num <= count) {
					System.out.println("\n====== Customer " + num + " Info. ======");
					Customer customer = new Customer();

					Boolean isSet = setCustomer(customer);
					if (isSet) {
						customers.add(customer);
					}

					num++;
				}
				break;
			} catch (InputExitException e) {
				System.out.println(Message.INFO_INPUT_EXIT_FLAG);
				break;
			} catch (InputFormatException e) {
				System.out.println(Message.ERR_INVALID_INPUT_FORMAT);
			}
		}
	}

	private void viewCustomer() {
		if (customers.size() == 0) {
			System.out.println(Message.ERR_INVALID_ARR_EMPTY);
			return;
		}

		System.out.println("\n======= Customer Info. =======");
		System.out.print(customers);
	}

	private void updateCustomer() {
		if (customers.size() == 0) {
			System.out.println(Message.ERR_INVALID_ARR_EMPTY);
			return;
		}

		viewCustomer();

		while (true) {
			try {
				System.out.println("\nWhich customer ( 1 ~ " + customers.size() + " )?");
				int input = nextInt(Message.EXIT_FLAG);

				Customer customer = customers.get(input - 1);
				setCustomer(customer);
				break;
			} catch (InputExitException e) {
				System.out.println(Message.INFO_INPUT_EXIT_FLAG);
				break;
			} catch (InputFormatException e) {
				System.out.println(Message.ERR_INVALID_INPUT_FORMAT);
			} catch (IndexOutOfBoundsException e) {
				System.out.println(Message.ERR_INVALID_INPUT_RANGE);
			}
		}
	}

	private void deleteCustomer() {
		if (customers.size() == 0) {
			System.out.println(Message.ERR_INVALID_ARR_EMPTY);
			return;
		}

		viewCustomer();

		while (true) {
			try {
				System.out.println("\nWhich customer ( 1 ~ " + customers.size() + " )?");
				int input = nextInt(Message.EXIT_FLAG);

				customers.pop(input - 1);
				break;
			} catch (InputExitException e) {
				System.out.println(Message.INFO_INPUT_EXIT_FLAG);
				break;
			} catch (InputFormatException e) {
				System.out.println(Message.ERR_INVALID_INPUT_FORMAT);
			} catch (IndexOutOfBoundsException e) {
				System.out.println(Message.ERR_INVALID_INPUT_RANGE);
			}
		}

	}

	/***** 고객 정보 Setting 메서드 (add, update 시 사용) *****/
	private Boolean setCustomer(Customer customer) {
		while (true) {
			try {
				int choice = chooseMenu(Stream.of(SetInfoMenu.values())
					.map(SetInfoMenu::toString)
					.toArray(String[]::new));

				Boolean isBack = SetInfoMenu.findByCode(choice).run(customer);
				if (isBack) {
					break;
				}
			} catch (InputExitException e) {
				System.out.println(Message.INFO_INPUT_EXIT_FLAG);
				break;
			} catch (InputFormatException e) {
				System.out.println(Message.ERR_INVALID_INPUT_FORMAT);
			}
		}

		try {
			if (customer.isEmpty()) {    // 아무 입력없이 바로 back 할 경우 고객 등록 x
				return false;
			}
		} catch (IllegalAccessException e) {
			System.out.println(e);
		}

		customer.assignGroupType(groups);    // 그룹 지정
		return true;
	}

	private void setCustomerName(Customer customer) {
		try {
			System.out.println("\nInput Customer's Name:");
			String input = nextLine(Message.EXIT_FLAG);
			customer.setName(input);
		} catch (InputExitException e) {
			System.out.println(Message.INFO_INPUT_EXIT_FLAG);
			return;
		}
	}

	private void setCustomerID(Customer customer) {
		while (true) {
			try {
				System.out.println("\nInput Customer's ID:");
				String input = nextLine(Message.EXIT_FLAG);

				// id 중복 검사
				if (customers.ispresent(input)) {
					throw new ElementDuplicateException();
				}

				customer.setId(input);
				break;
			} catch (InputExitException e) {
				System.out.println(Message.INFO_INPUT_EXIT_FLAG);
				break;
			} catch (ElementDuplicateException e) {
				System.out.println(Message.ERR_ELEMENT_DUPLICATE);
			}
		}
	}

	private void setCustomerSpentTime(Customer customer) {
		while (true) {
			try {
				System.out.println("\nInput Customer's Spent Time:");
				int input = nextInt(Message.EXIT_FLAG);

				customer.setSpentTime(input);
				break;
			} catch (InputExitException e) {
				System.out.println(Message.INFO_INPUT_EXIT_FLAG);
				break;
			} catch (InputFormatException e) {
				System.out.println(Message.ERR_INVALID_INPUT_FORMAT);
			}
		}
	}

	private void setCustomerTotalPay(Customer customer) {
		while (true) {
			try {
				System.out.println("\nInput Customer's Total Payment:");
				int input = nextInt(Message.EXIT_FLAG);

				customer.setTotalPay(input);
				break;
			} catch (InputExitException e) {
				System.out.println(Message.INFO_INPUT_EXIT_FLAG);
				break;
			} catch (InputFormatException e) {
				System.out.println(Message.ERR_INVALID_INPUT_FORMAT);
			}
		}
	}

	/***** Menu enum *****/
	enum RootMenu {
		ADD(1, "Add Customer") {
			@Override
			public Boolean run() {
				customerMenu.addCustomer();
				return false;
			}
		},
		VIEW(2, "View Customer") {
			@Override
			public Boolean run() {
				customerMenu.viewCustomer();
				return false;
			}
		},
		UPDATE(3, "Update Customer") {
			@Override
			public Boolean run() {
				customerMenu.updateCustomer();
				return false;
			}
		},
		DELETE(4, "Delete Customer") {
			@Override
			public Boolean run() {
				customerMenu.deleteCustomer();
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

	enum SetInfoMenu {
		NAME(1, "Customer Name") {
			@Override
			public Boolean run(Customer customer) {
				customerMenu.setCustomerName(customer);
				return false;
			}
		},
		ID(2, "Customer ID") {
			@Override
			public Boolean run(Customer customer) {
				customerMenu.setCustomerID(customer);
				return false;
			}
		},
		SPENT_TIME(3, "Customer Spent Time") {
			@Override
			public Boolean run(Customer customer) {
				customerMenu.setCustomerSpentTime(customer);
				return false;
			}
		},
		TOTAL_PAY(4, "Customer Total Pay") {
			@Override
			public Boolean run(Customer customer) {
				customerMenu.setCustomerTotalPay(customer);
				return false;
			}
		},
		BACK(5, "Back") {
			@Override
			public Boolean run(Customer customer) {
				return Menu.back();
			}
		};

		private int code;
		private String label;

		SetInfoMenu(int code, String label) {
			this.code = code;
			this.label = label;
		}

		public int getCode() {
			return code;
		}

		public static SetInfoMenu findByCode(int code) {
			return Arrays.stream(SetInfoMenu.values())
				.filter(t -> t.getCode() == code)
				.findAny()
				.orElseThrow(NoSuchElementException::new);
		}

		@Override
		public String toString() {
			return " " + code + ". " + label;
		}

		public abstract Boolean run(Customer customer);
	}
}
