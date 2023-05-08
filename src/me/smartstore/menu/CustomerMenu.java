package me.smartstore.menu;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;
import me.smartstore.util.Message;

public class CustomerMenu implements Menu{
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
			int choice = chooseMenu(new String[]{
				"Add Customer Data",
				"View Customer Data",
				"Update Customer Data",
				"Delete Customer Data",
				"Back"
			});

			if (choice == 1) {
				addCustomer();
			} else if (choice == 2) {

			} else if (choice == 3) {

			} else if (choice == 4) {

			} else if (choice == 5) {
				break;
			}
		}
	}

	private void addCustomer() {
		while(true) {
			try {
				System.out.println("How many customers to input?");
				int addCnt = Integer.parseInt(nextLine(Message.END_MSG));

				if (addCnt <= 0) throw new InputRangeException();

				for (int i = 0; i < addCnt; i++) {
					System.out.println("====== Customer " + (i + 1) + " Info. ======");

					Customer newCustomer = new Customer();
					allCustomers.add(newCustomer);

					showParameterMenu(newCustomer);

					allCustomers.refresh(newCustomer);
				}

				break;
			} catch(NumberFormatException e) {
				System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
			} catch (InputRangeException e) {
				System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
			} catch (InputEndException e) {
				System.out.println(Message.ERR_MSG_INPUT_END);
				break;
			}
		}
	}

	private void showParameterMenu(Customer customer) {
		while(true) {
			int choice = chooseMenu(new String[]{
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
		try {
			System.out.println("Input Customer's Name:");
			String customerName = nextLine(Message.END_MSG);

			customer.setCustomerName(customerName);
		} catch(InputEndException e) {
			System.out.println(Message.ERR_MSG_INPUT_END);
		}
	}

	private void setCustomerID(Customer customer) {
		try {
			System.out.println("Input Customer's ID:");
			String customerId = nextLine(Message.END_MSG);

			customer.setCustomerId(customerId);
		} catch(InputEndException e) {
			System.out.println(Message.ERR_MSG_INPUT_END);
		}
	}

	private void setTimeParameter(Customer customer) {
		try {
			System.out.println("Input Customer's Spent Time:");

			int time = Integer.parseInt(nextLine(Message.END_MSG));
			customer.setUseHours(time);
		} catch (NumberFormatException e){
			System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
		} catch (InputEndException e) {
			System.out.println(Message.ERR_MSG_INPUT_END);
		}
	}

	private void setPayParameter(Customer customer) {
		try {
			System.out.println("Input Customer's Total Pay:");

			int pay = Integer.parseInt(nextLine(Message.END_MSG));
			customer.setCustomerPay(pay);
		} catch (NumberFormatException e){
			System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
		} catch (InputEndException e) {
			System.out.println(Message.ERR_MSG_INPUT_END);
		}
	}
}
