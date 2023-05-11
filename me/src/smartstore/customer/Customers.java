package smartstore.customer;

import smartstore.arrays.DArray;
import smartstore.group.GroupType;
import smartstore.group.Groups;
import smartstore.util.Message;

public class Customers extends DArray<Customer> {
	
    // singleton
    private static Customers allCustomers;

    public static Customers getInstance() {
        if (allCustomers == null) {
            allCustomers = new Customers();
        }
        return allCustomers;
    }

    private Customers() {}

    // refresh 함수가 호출되는 경우
    // 1. 분류기준 바뀔 때
    // 2. 새로운 고객이 들어올 때
    public int refresh(Groups groups) {
    	
    	if (groups.size() == 0) {
    		System.out.println(Message.ERR_MSG_INVALID_GROUP_ARR_EMPTY);
    		return -1;
    	}
    	
    	for(int i = 0; i < groups.size(); i++) {
    		
    		if (groups.get(i).getParameter() == null) {
    			System.out.println(Message.ERR_MSG_EMPTY_PARAMETER);
    			break;
    		}
    		
    		for(int j = 0; j < this.size; j++) {
    			
    			// 파라미터 값이 없으면 NONE으로 초기화
    			if (groups.get(i).getParameter().getMinPay() == null || groups.get(i).getParameter().getMinTime() == null) {
    				this.get(j).setGroup(groups.find(GroupType.NONE));
    				continue;
        		}
    			
    			if ((groups.find(GroupType.VVIP).getParameter().getMinTime() == null && 
    					groups.find(GroupType.VVIP).getParameter().getMinPay() == null) ||
    					(groups.find(GroupType.VIP).getParameter().getMinTime() == null && 
    					groups.find(GroupType.VIP).getParameter().getMinPay() == null) ||
    					(groups.find(GroupType.GENERAL).getParameter().getMinTime() == null && 
    					groups.find(GroupType.GENERAL).getParameter().getMinPay() == null)) {
    				continue;
    			}
    			
    			this.get(j).setGroup(groups.find(GroupType.NONE));
    			
    			if (this.get(j).getCusTotalTime() >= groups.find(GroupType.VVIP).getParameter().getMinTime() &&
    				this.get(j).getCusTotalPay() >= groups.find(GroupType.VVIP).getParameter().getMinPay()) {
    				
    				this.get(j).setGroup(groups.find(GroupType.VVIP));
    				
        		} else if (this.get(j).getCusTotalTime() >= groups.find(GroupType.VIP).getParameter().getMinTime() &&
    					this.get(j).getCusTotalPay() >= groups.find(GroupType.VIP).getParameter().getMinPay()) {
        			
        			this.get(j).setGroup(groups.find(GroupType.VIP));
        			
        		} else if (this.get(j).getCusTotalTime() >= groups.find(GroupType.GENERAL).getParameter().getMinTime() &&
    					this.get(j).getCusTotalPay() >= groups.find(GroupType.GENERAL).getParameter().getMinPay()) {
        			
        			this.get(j).setGroup(groups.find(GroupType.GENERAL));
        			
        		} 
        	}
    	}
    	return 0;
    }
    
    public int refreshForOne(Groups groups) {
    	
    	if (groups.size() == 0) {
    		System.out.println(Message.ERR_MSG_INVALID_GROUP_ARR_EMPTY);
    		return -1;
    	}
    	
    	Customer customer = this.get(this.size - 1);
    	
    	for(int i = 0; i < groups.size(); i++) {
    		
    		if (groups.get(i).getParameter() == null) {
    			System.out.println(Message.ERR_MSG_EMPTY_PARAMETER);
    			break;
    		}
    		
    			
			// 파라미터 값이 없으면 NONE으로 초기화
			if (groups.get(i).getParameter().getMinPay() == null || groups.get(i).getParameter().getMinTime() == null) {
				customer.setGroup(groups.find(GroupType.NONE));
				continue;
    		}
			
			if ((groups.find(GroupType.VVIP).getParameter().getMinTime() == null && 
					groups.find(GroupType.VVIP).getParameter().getMinPay() == null) ||
					(groups.find(GroupType.VIP).getParameter().getMinTime() == null && 
					groups.find(GroupType.VIP).getParameter().getMinPay() == null) ||
					(groups.find(GroupType.GENERAL).getParameter().getMinTime() == null && 
					groups.find(GroupType.GENERAL).getParameter().getMinPay() == null)) {
				continue;
			}
			
			customer.setGroup(groups.find(GroupType.NONE));
			
			if (customer.getCusTotalTime() >= groups.find(GroupType.VVIP).getParameter().getMinTime() &&
					customer.getCusTotalPay() >= groups.find(GroupType.VVIP).getParameter().getMinPay()) {
				
				customer.setGroup(groups.find(GroupType.VVIP));
				
    		} else if (customer.getCusTotalTime() >= groups.find(GroupType.VIP).getParameter().getMinTime() &&
    				customer.getCusTotalPay() >= groups.find(GroupType.VIP).getParameter().getMinPay()) {
    			
    			customer.setGroup(groups.find(GroupType.VIP));
    			
    		} else if (customer.getCusTotalTime() >= groups.find(GroupType.GENERAL).getParameter().getMinTime() &&
    				customer.getCusTotalPay() >= groups.find(GroupType.GENERAL).getParameter().getMinPay()) {
    			
    			customer.setGroup(groups.find(GroupType.GENERAL));
    		} 
    	}
    	return 0;
    }
    
    public Customer deleteCustomer(int choice) {
    	return pop(choice - 1);
    }
    
    public void customerInfo() {
		System.out.println("======= Customer Info. =======");
		for(int i = 0; i < allCustomers.size(); i++) {
			System.out.println("No. " + (i + 1) + " => " + allCustomers.get(i));
		}
		System.out.println();
	}

}
