package me.smartstore.menu;

import me.smartstore.domain.customer.*;
import me.smartstore.exception.ArrayEmptyException;
import me.smartstore.exception.InputEmptyException;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;
import me.smartstore.domain.group.Group;
import me.smartstore.domain.group.Groups;

public class CustomerMenu extends Menu {
	private static CustomerMenu customerMenu;
	
	public static CustomerMenu getInstance() {
		if (customerMenu == null)
			customerMenu = new CustomerMenu(); 
	    return customerMenu;
    }
  
	private Groups allGroups = Groups.getInstance();
  
	private Customers allCustomers = Customers.getInstance();
  
	public void manageCustomerMenu() {
		while (true) {
			int choice = dispMenus(new String[] { "Add Customer Data", "View Customer Data", "Update Customer Data", "Delete Customer Data", "Back" });
			if (choice == 1) {
				int size = 0;
				size = getCustomerSizeToAdd();
				setCustomerData(size);
				continue;
			} 
			if (choice == 2) {
				viewCustomerData();
				continue;
			} 
			if (choice == 3) {
				updateCustomerData();
				continue;
			} 
			if (choice == 4) {
				deleteCustomerData();
				continue;
			} 
			if (choice == 5)
				break; 
		} 
	}
	
	public void setCustomerData(int size) {
		for (int i = 0; i < size; i++) {
			Customer customer = new Customer();
			System.out.println("\n====== Customer " + i + 1 + " Info. ======");
			while (true) {
				int choice = dispMenus(new String[] { "Customer Name", "Customer ID", "Customer Spent Time", "Customer Total Pay", "Back" });
				if (choice == 1) {
					setCustomerName(customer);
					continue;
				} 
				if (choice == 2) {
					setCustomerUserId(customer);
					continue;
				} 
				if (choice == 3) {
					setCustomerSpentTime(customer);
					continue;
				} 
				if (choice == 4) {
					setCustomerTotalPay(customer);
					continue;
				} 
				if (choice == 5)
					break; 
			} 
			Group grp = this.allGroups.findGroupFor(customer);
			if (grp == null) {
				customer.setGroup(null);
			} else if (!grp.equals(customer.getGroup())) {
				customer.setGroup(grp);
			}
		this.allCustomers.add(customer);
		}
	}
	
	public void viewCustomerData() {
		if (this.allCustomers.size() == 0) {
			System.out.println("사용자가 없습니다. 사용자를 추가해주세요.");
			return;
		} 
		System.out.println("\n======= Customer Info. =======");
		for (int i = 0; i < this.allCustomers.size(); i++) {
			Customer cust = this.allCustomers.get(i);
			if (cust != null) {
				System.out.println("No. " + i + 1 + " => " + cust);
			} else {
				System.out.println("null");
			} 
		} 
	}
	  
	public void updateCustomerData() {
		viewCustomerData();
		int custNo = 0;
		try {
			custNo = findCustomer();
		} catch (ArrayEmptyException|InputEndException e) {
			return;
		} 
		Customer customer = this.allCustomers.get(custNo - 1);
		if (customer == null)
			return; 
		while (true) {
			int choice = dispMenus(new String[] { "Customer Name", "Customer ID", "Customer Spent Time", "Customer Total Pay", "Back" });
			if (choice == 1) {
				setCustomerName(customer);
				continue;
			} 
			if (choice == 2) {
				setCustomerUserId(customer);
				continue;
			} 
			if (choice == 3) {
				setCustomerSpentTime(customer);
				continue;
			} 
			if (choice == 4) {
				setCustomerTotalPay(customer);
				continue;
			} 
			if (choice == 5)
				break; 
		} 
		Group grp = this.allGroups.findGroupFor(customer);
		if (grp == null) {
			customer.setGroup(null);
		} else if (!grp.equals(customer.getGroup())) {
			customer.setGroup(grp);
		} 
	}
	  
	public void deleteCustomerData() {
		viewCustomerData();
		int custNo = 0;
		try {
			custNo = findCustomer();
		} catch (ArrayEmptyException|InputEndException e) {
			return;
		} 
		Customer customer = this.allCustomers.get(custNo - 1);
		System.out.println(customer);
		this.allCustomers.pop(custNo - 1);
		viewCustomerData();
	}
	  
	private int getCustomerSizeToAdd() {
		while (true) {
			try {
				System.out.print("How many customers to input? ");
				int size = Integer.parseInt(nextLine("END"));
				if (size < 0)
					throw new InputRangeException(); 
				return size;
			} catch (NumberFormatException e) {
				System.out.println("입력 형식이 잘못되었습니다.");
			} catch (InputRangeException e) {
				System.out.println("입력 범위가 잘못되었습니다.");
			} catch (InputEndException e) {
				System.out.println("이 메뉴를 종료합니다.");
				return -1;
			} 
		} 
	}
  
	public void setCustomerName(Customer customer) {
		while (true) {
			try {
				System.out.print("\nInput Customer's Name: ");
				String name = nextLine("END");
				if (name == null || name.equals(""))
					throw new InputEmptyException(); 
				customer.setName(name);
				return;
				} catch (InputEmptyException e) {
					System.out.println("입력해주세요.");
				} catch (InputEndException e) {
					System.out.println("이 메뉴를 종료합니다.");
					break;
				} 
		} 
	}
	
	public void setCustomerUserId(Customer customer) {
		while (true) {
			try {
				System.out.print("\nInput Customer's ID: ");
				String userId = nextLine("END");
				if (userId == null || userId.equals(""))
					throw new InputEmptyException(); 
				customer.setUserId(userId);
				return;
			} catch (InputEmptyException e) {
				System.out.println("입력해주세요.");
			} catch (InputEndException e) {
				System.out.println("이 메뉴를 종료합니다.");
				break;
			} 
		} 
	}
	  
	public void setCustomerSpentTime(Customer customer) {
		while (true) {
			try {
				System.out.print("\nInput Customer's Spent Time: ");
				int spentTime = Integer.parseInt(nextLine("END"));
				if (spentTime < 0)
					throw new InputRangeException(); 
				customer.setSpentTime(Integer.valueOf(spentTime));
				return;
			} catch (InputRangeException e) {
				System.out.println("입력 범위가 잘못되었습니다.");
			} catch (InputEndException e) {
				System.out.println("이 메뉴를 종료합니다.");
				break;
			} 
		} 
	}
	  
	public void setCustomerTotalPay(Customer customer) {
		while (true) {
			try {
				System.out.print("\nInput Customer's Total Payment: ");
				int totalPay = Integer.parseInt(nextLine("END"));
				if (totalPay < 0)
					throw new InputRangeException(); 
				customer.setTotalPay(Integer.valueOf(totalPay));
				return;
			} catch (NumberFormatException e) {
				System.out.println("입력 형식이 잘못되었습니다.");
			} catch (InputRangeException e) {
				System.out.println("입력 범위가 잘못되었습니다.");
			} catch (InputEndException e) {
				System.out.println("이 메뉴를 종료합니다.");
				break;
			} 
		} 
	  }
	  
	public int findCustomer() throws ArrayEmptyException, InputEndException {
		int size = this.allCustomers.size();
	    if (size == 0)
	    	throw new ArrayEmptyException(); 
	    while (true) {
	    	try {
	    		System.out.print("\nWhich customer ( 1 ~ " + size + " )? ");
	    		int custNo = Integer.parseInt(nextLine());
	    		if (custNo < 1 || custNo > size)
	    			throw new InputRangeException(); 
	    		return custNo;
	    	} catch (NumberFormatException e) {
	    		System.out.println("\n입력 형식이 잘못되었습니다.");
	    	} catch (InputRangeException e) {
	    		System.out.println("\n입력 범위가 잘못되었습니다.");
	    	} 
	    } 
	}

}
