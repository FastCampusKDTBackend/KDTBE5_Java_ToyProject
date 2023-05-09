package me.smartstore.menu;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.DuplicateValueException;
import me.smartstore.exception.ElementEmptyException;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;
import me.smartstore.util.Message;

public class CustomerMenu implements Menu {
	private static Customers allCustomers = Customers.getInstance();

	private static CustomerMenu customerMenu;

	private CustomerMenu() {

	}

	public static CustomerMenu getInstance() {
		if (customerMenu == null) {
			customerMenu = new CustomerMenu();
		}

		return customerMenu;
	}

	@Override
	public void show() {
		while (true) {
			int choice = chooseMenu(new String[] {
				"Add Customer Data",
				"View Customer Data",
				"Update Customer Data",
				"Delete Customer Data",
				"Back"
			});

			if (choice == 1) {
				addCustomer();
			} else if (choice == 2) {
				viewCustomers();
			} else if (choice == 3) {
				updateCustomer();
			} else if (choice == 4) {
				deleteCustomer();
			} else if (choice == 5) {
				break;
			}
		}
	}

	private void addCustomer() {
		while (true) {
			try {
				System.out.println("How many customers to input?");
				int addCnt = Integer.parseInt(nextLine(Message.END_MSG));

				if (addCnt <= 0)
					throw new InputRangeException();

				for (int i = 0; i < addCnt; i++) {
					System.out.println("====== Customer " + (i + 1) + " Info. ======");

					Customer newCustomer = new Customer();
					allCustomers.add(newCustomer);

					showParameterMenu(newCustomer);

					allCustomers.refresh(newCustomer);
				}

				break;
			} catch (NumberFormatException | InputRangeException e) {
				System.out.println(e.getMessage());
			} catch (InputEndException e) {
				System.out.println(e.getMessage());
				break;
			}
		}
	}

	private void showParameterMenu(Customer customer) {
		while (true) {
			int choice = chooseMenu(new String[] {
				"Customer Name",
				"Customer ID",
				"Customer Spent Time",
				"Customer Total Pay",
				"Back"
			});

			if (choice == 1) {
				setCustomerName(customer);
			} else if (choice == 2) {
				setCustomerID(customer);
			} else if (choice == 3) {
				setTimeParameter(customer);
			} else if (choice == 4) {
				setPayParameter(customer);
			} else if (choice == 5) {
				break;
			}
		}
	}

	private void setCustomerName(Customer customer) {
		while (true) {
			try {
				System.out.println("Input Customer's Name:");
				String customerName = nextLine(Message.END_MSG);

				customer.setCustomerName(customerName);
				break;
			} catch (InputEndException e) {
				System.out.println(e.getMessage());
				break;
			}
		}
	}

	private void setCustomerID(Customer customer) {
		while (true) {
			try {
				System.out.println("Input Customer's ID:");
				String customerId = nextLine(Message.END_MSG);

				customer.setCustomerId(customerId);
				break;
			} catch (InputEndException e) {
				System.out.println(e.getMessage());
				break;
			} catch (DuplicateValueException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void setTimeParameter(Customer customer) {
		while (true) {
			try {
				System.out.println("Input Customer's Spent Time:");

				int time = Integer.parseInt(nextLine(Message.END_MSG));

				customer.setUseHours(time);
				break;
			} catch (NumberFormatException | InputRangeException e) {
				System.out.println(e.getMessage());
			} catch (InputEndException e) {
				System.out.println(e.getMessage());
				break;
			}
		}
	}

	private void setPayParameter(Customer customer) {
		while (true) {
			try {
				System.out.println("Input Customer's Total Pay:");

				int pay = Integer.parseInt(nextLine(Message.END_MSG));

				customer.setCustomerPay(pay);
				break;
			} catch (NumberFormatException | InputRangeException e) {
				System.out.println(e.getMessage());
			} catch (InputEndException e) {
				System.out.println(e.getMessage());
				break;
			}
		}
	}

	private boolean viewCustomers() {
		try {
			if (allCustomers.isEmpty())
				throw new ElementEmptyException();

			System.out.println("======= Customer Info. =======");
			System.out.println(allCustomers);
			System.out.println();
			return true;
		} catch (ElementEmptyException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	private void updateCustomer() {
		if (!viewCustomers())
			return;

		while (true) {
			try {
				int customerIndex = selectCustomer();

				showParameterMenu(allCustomers.get(customerIndex));
				allCustomers.refresh(allCustomers.get(customerIndex));

				break;
			} catch (NumberFormatException | InputRangeException e) {
				System.out.println(e.getMessage());
			} catch (InputEndException e) {
				System.out.println(e.getMessage());
				break;
			}
		}
	}

	private void deleteCustomer() {
		if (!viewCustomers())
			return;

		while (true) {
			try {
				int customerIndex = selectCustomer();

				Customer deleteCustomer = allCustomers.remove(customerIndex);

				System.out.println(deleteCustomer + "\n");
				viewCustomers();
				break;
			} catch (NumberFormatException | InputRangeException e) {
				System.out.println(e.getMessage());
			} catch (InputEndException e) {
				System.out.println(e.getMessage());
				break;
			}
		}
	}

	private int selectCustomer() {
		System.out.println("Which Customer ( 1 ~ " + allCustomers.size() + " )?");
		int customerIndex = Integer.parseInt(nextLine(Message.END_MSG)) - 1;

		if (customerIndex < 0 || customerIndex > allCustomers.size())
			throw new InputRangeException();

		return customerIndex;
	}
}
