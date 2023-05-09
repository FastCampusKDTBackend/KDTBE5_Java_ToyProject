package smartstore.menu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import smartstore.customer.Customer;
import smartstore.customer.Customers;
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
    			List<Customer> customersBuilder = new ArrayList<>(); 
	    		for(int c = 0; c < allCustomers.size(); c++) {
	    			customersBuilder.add(allCustomers.get(c));
	    		}
	    		
    			for(int i = 0; i < GroupType.values().length/2; i++) { 
    				Integer minTime = null;
    				Integer minPay = null;
    				
    				GroupType groupType = GroupType.values()[i];
    				
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
    	    		
    	    		// 그룹타입에 따른 분류 스트림
    	    		List<Customer> customerList = customersBuilder.stream()
			 			 			.filter(customer -> customer.getGroup().getGroupType() == groupType)
			 			 			.collect(Collectors.toList());
    	    		
    	    		// 그룹에 해당하는 Customer 없으면 Null. 출력
    	    		if(customerList.size() != 0) {
    	    			for (Customer customer : customerList) {
        	    			System.out.println(customer);
        	    		}
    	    			System.out.println("==============================\n");
    	    		} else {
    	    			System.out.println("Null.\n");
    	    		}
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
    	
    }
    
    // Summary (Sorted By Time)
//    public void summarySortedByTime() {
//    	
//    }
//    
    // Summary (Sorted By Pay)
//    public void summarySortedByPay() {
//    	
//    }
}
