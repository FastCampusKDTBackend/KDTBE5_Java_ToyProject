package smartstore.menu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import smartstore.customer.Customer;
import smartstore.customer.Customers;
import smartstore.exception.InputEndException;
import smartstore.exception.InputTypeException;
import smartstore.group.GroupType;
import smartstore.group.Groups;
import smartstore.util.Message;

public class SummaryMenu implements Menu {
	
	private final Groups allGroups = Groups.getInstance();
	private final Customers allCustomers = Customers.getInstance();
	
    // singleton
    private static SummaryMenu summaryMenu;

    public static SummaryMenu getInstance() {
        if (summaryMenu == null) {
            summaryMenu = new SummaryMenu();
        }
        return summaryMenu;
    }

    private SummaryMenu() {}

    @Override
    public void manage() {
        while ( true ) { // 서브 메뉴 페이지를 유지하기 위한 while
        	try {
        		int choice = chooseMenu(new String[]{
                        "Summary",
                        "Summary (Sorted By Name)",
                        "Summary (Sorted By Time)",
                        "Summary (Sorted By Pay)",
                        "Back"});
                
                if (choice == 1) summary();
                else if (choice == 2) summarySortedByName();
//                else if (choice == 3) summary(Sorted By Time)();
//                else if (choice == 4) summary(Sorted By Pay)();
                else break; // choice == 4
        	} catch (InputTypeException e) {
				System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
			} catch (IllegalArgumentException e) {
				System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
			}
        }
    }
    
    public void summary() {
    	while(true) {
    		try {
    			// 스트림 만들기 위함.
    			List<Customer> customersBuilder = new ArrayList<>(); 
	    		for(int c = 0; c < allCustomers.size(); c++) {
	    			if (allCustomers.get(c).getGroup() == null) break;
	    			customersBuilder.add(allCustomers.get(c));
	    		}
	    		
    			for(int i = 0; i < GroupType.values().length/2; i++) { 
    				
    				GroupType groupType = GroupType.values()[i];
    				
    				printSummaryList(allGroups, i, groupType);
    				
    	    		// 그룹타입에 따른 분류 스트림
    	    		List<Customer> customerList = customersBuilder.stream()
			 			 			.filter(customer -> customer.getGroup().getGroupType() == groupType)
			 			 			.collect(Collectors.toList());
    	    		
    	    		printNullGroups(customerList);
    	    	}
    	    	System.out.println();
    			break;
    		} catch (InputTypeException e) {
				System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
			} catch (IllegalArgumentException e) {
				System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
			} catch (IndexOutOfBoundsException e) {
				System.out.println(Message.ERR_MSG_IDEX_OUT_OF_BOUNDS);
				break;
			} 
    	}
    }
    
    // Summary (Sorted By Name)
    public void summarySortedByName() {
    	while(true) {
    		try {
    			List<Customer> customersBuilder = new ArrayList<>(); 
	    		for(int c = 0; c < allCustomers.size(); c++) {
	    			customersBuilder.add(allCustomers.get(c));
	    		}
	    		
	    		SortType sortType = chooseGroup();
	    		
    			for(int i = 0; i < GroupType.values().length/2; i++) { 
    				GroupType groupType = GroupType.values()[i];
    				
    				printSummaryList(allGroups, i, groupType);
    				
    				List<Customer> customerList = customersBuilder.stream()
	 			 			.filter(customer -> customer.getGroup().getGroupType() == groupType)
	 			 			.collect(Collectors.toList());
    				
    				printNullGroups(sortedStream(customerList, sortType));
    				
    	    	}
    	    	System.out.println();
    			break;
    		} catch (InputTypeException e) {
				System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
			} catch (IllegalArgumentException e) {
				System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
			} catch (IndexOutOfBoundsException e) {
				System.out.println(Message.ERR_MSG_IDEX_OUT_OF_BOUNDS);
				break;
			}
    	}
    }
    
    // Summary (Sorted By Time)
//    public void summarySortedByTime() {
//    	
//    }
    
    // Summary (Sorted By Pay)
//    public void summarySortedByPay() {
//    	
//    }
    
    private SortType chooseGroup() {
        while ( true ) {
            try {
                System.out.print("Which order (ASCENDING (A), DESCENDING (D))? ");
                String choice = nextLine(Message.END_MSG);
                
                SortType sortType = SortType.valueOf(choice).replaceFullName();
                return sortType;
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                return null;
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
			}
        }
    }
    
    private void printSummaryList(Groups allGroups, int i, GroupType groupType) {
    	
    	Integer minTime = null;
		Integer minPay = null;
		
		if(allGroups.get(i).getParameter().getMinTime() != null) {
			minTime = allGroups.get(i).getParameter().getMinTime();
		}
		
		if(allGroups.get(i).getParameter().getMinPay() != null) {
			minPay = allGroups.get(i).getParameter().getMinPay(); 
		}
		
		System.out.println("==============================");
		System.out.println("Group : " + groupType +
						   " ( Time : " + minTime +
						   ", Pay : " + minPay + " )");
		System.out.println("==============================");
    }
    
    // 오름차순 내림차순 정렬
    private List<Customer> sortedStream(List<Customer> customerList, SortType sortType) {
    	List<Customer> sortedList;
    	
		if (sortType == SortType.DESCENDING) {
			sortedList = customerList.stream().sorted(Comparator.comparing(Customer::getCusName).reversed())
					.collect(Collectors.toList());
		} else {
			sortedList = customerList.stream().sorted(Comparator.comparing(Customer::getCusName))
					.collect(Collectors.toList());
		}
		return sortedList;
    }
    
    
    private void printNullGroups(List<Customer> customerList) {
    	if(customerList.size() != 0) {
			int count = 0;
			for (Customer customer : customerList) {
				count++;
    			System.out.println("No.\t" + count + " => " + customer);
    		}
			System.out.println("==============================\n");
		} else {
			System.out.println("Null.\n");
		}
    }
    
}
