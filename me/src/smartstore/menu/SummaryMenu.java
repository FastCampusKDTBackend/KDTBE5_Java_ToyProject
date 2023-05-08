package smartstore.menu;

import smartstore.customer.Customers;
import smartstore.exception.InputTypeException;
import smartstore.group.GroupType;
import smartstore.group.Groups;
import smartstore.util.Message;

public class SummaryMenu implements Menu {
	
	private final CustomerMenu customerMenu = CustomerMenu.getInstance();
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
//                else if (choice == 2) summary(Sorted By Name)();
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
    			for(int i = 0; i < GroupType.values().length/2; i++) { // 4
    				Integer minTime = null;
    				Integer minPay = null;
//    				System.out.println(i); // 0 1 2 3
    				
    				if(allGroups.get(i).getParameter().getMinTime() != null) {
    					minTime = allGroups.get(i).getParameter().getMinTime(); // 0 1 2
    				}
    				
    				if(allGroups.get(i).getParameter().getMinPay() != null) {
    					minPay = allGroups.get(i).getParameter().getMinPay(); // 0 1 2
    				}
    				
    	    		System.out.println("==============================");
    	    		System.out.println("Group : " + GroupType.values()[i] +
    	    						   " ( Time : " + minTime +
    	    						   ", Pay : " + minPay + " )");
    	    		System.out.println("==============================");
    	    		
    	    		for(int j = 0; j < allCustomers.size(); j++) { // 26
    	    			if (GroupType.values()[i] == allCustomers.get(j).getGroup().getGroupType()) {
    	    				System.out.println("No. " + j + " => " + allCustomers.get(j));
    	    			}
    	    		}
    	    		System.out.println("==============================\n");
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
    
//    // Summary (Sorted By Name)
//    public void summary(String name) {
//    	
//    }
//    
//    // Summary (Sorted By Time)
//    public void summary(int time) {
//    	
//    }
//    
// // Summary (Sorted By Pay)
//    public void summary(int pay) {
//    	
//    }
}
