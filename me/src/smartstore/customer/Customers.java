package smartstore.customer;

import smartstore.arrays.DArray;
import smartstore.group.GroupType;
import smartstore.group.Groups;

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
    public void refresh(Groups groups) {
    	for(int i = 0; i < this.size; i++) {
    		for(int j = 1; j < groups.size(); j++) {
    			if(this.get(i).getCusTotalTime() == null || this.get(i).getCusTotalPay() == null) break;
    			
    			if (this.get(i).getCusTotalTime() >= groups.find(GroupType.values()[3]).getParameter().getMinTime() &&
					this.get(i).getCusTotalPay() >= groups.find(GroupType.values()[3]).getParameter().getMinPay()) {
    				
    				this.get(i).setGroup(groups.find(GroupType.values()[3]));
    				
        		} else if (this.get(i).getCusTotalTime() >= groups.find(GroupType.values()[2]).getParameter().getMinTime() &&
    					this.get(i).getCusTotalPay() >= groups.find(GroupType.values()[2]).getParameter().getMinPay()) {
        			
        			this.get(i).setGroup(groups.find(GroupType.values()[2]));
        			
        		} else if (this.get(i).getCusTotalTime() >= groups.find(GroupType.values()[1]).getParameter().getMinTime() &&
    					this.get(i).getCusTotalPay() >= groups.find(GroupType.values()[1]).getParameter().getMinPay()) {
        			
        			this.get(i).setGroup(groups.find(GroupType.values()[1]));
        			
        		} else {
        			
        			this.get(i).setGroup(groups.find(GroupType.values()[0]));
        		}
    		}
    	}
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
