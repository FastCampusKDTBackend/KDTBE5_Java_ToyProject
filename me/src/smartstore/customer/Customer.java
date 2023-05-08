package smartstore.customer;

import smartstore.exception.InputEndException;
import smartstore.exception.InputTypeException;
import smartstore.group.Group;
import smartstore.menu.Menu;
import smartstore.util.Message;

import java.util.Objects;

public class Customer implements Menu {
    private String cusName;
    private String cusId;
    private Integer cusTotalTime;
    private Integer cusTotalPay;
    private Group group; // 현재 분류 기준에 의해 각 고객을 분류된 결과

    public Customer() {}

    public Customer(String cusId) {
        this.cusId = cusId;
    }

    public Customer(String cusName, String cusId) {
        this.cusName = cusName;
        this.cusId = cusId;
    }

    public Customer(String cusName, String cusId, Integer cusTotalTime, Integer cusTotalPay) {
        this.cusName = cusName;
        this.cusId = cusId;
        this.cusTotalTime = cusTotalTime;
        this.cusTotalPay = cusTotalPay;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public Integer getCusTotalTime() {
        return cusTotalTime;
    }

    public void setCusTotalTime(Integer cusTotalTime) {
        this.cusTotalTime = cusTotalTime;
    }

    public Integer getCusTotalPay() {
        return cusTotalPay;
    }

    public void setCusTotalPay(Integer cusTotalPay) {
        this.cusTotalPay = cusTotalPay;
    }
    
    public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(cusId, customer.cusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cusId);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cusName='" + cusName + '\'' +
                ", cusId='" + cusId + '\'' +
                ", cusTotalTime=" + cusTotalTime +
                ", cusTotalPay=" + cusTotalPay +
                ", group=" + group +
                '}';
    }

	@Override
	public void manage() {
		while ( true ) {
			try {
				int choice = chooseMenu(new String[]{
	                    "Customer Name",
	                    "Customer ID",
	                    "Customer Spent Time",
	                    "Customer Total Pay",
	                    "Back"});

	            if (choice == 1) setCustomerName();
	            else if (choice == 2) setCustomerID();
	            else if (choice == 3) setCustomerTime();
	            else if (choice == 4) setCustomerPay();
	            else break; // choice == 5
			} catch (InputTypeException e) {
				System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
			} catch (IllegalArgumentException e) {
				System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
			} 
        }
		
	}

	private void setCustomerName() {
		while(true) {
			try {
				this.setCusName(chooseGroupString(Message.MSG_Input_Customer_Name));
				break;
			} catch (IndexOutOfBoundsException e) {
				System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
			} catch (IllegalArgumentException e) {
				System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
			} 
		}
	}

	private void setCustomerID() {
		while(true) {
			try {
				this.setCusId(chooseGroupString(Message.MSG_Input_Customer_ID));
				break;
			} catch (IndexOutOfBoundsException e) {
				System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
			} catch (IllegalArgumentException e) {
				System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
			} 
		}
	}

	private void setCustomerTime() {
		while(true) {
			try {
				this.setCusTotalTime(chooseGroupInt(Message.MSG_Input_Customer_Time));
				break;
			} catch (IndexOutOfBoundsException e) {
				System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
			} catch (IllegalArgumentException e) {
				System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
			} 
		}
	}

	private void setCustomerPay() {
		while(true) {
			try {
				this.setCusTotalPay(chooseGroupInt(Message.MSG_Input_Customer_Pay));;
				break;
			} catch (IndexOutOfBoundsException e) {
				System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
			} catch (IllegalArgumentException e) {
				System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
			} 
		}
	}
	
	private int chooseGroupInt(String msg) {
		int resultInt = -1;
        while ( true ) {
            try {
            	System.out.print(msg);
            	String choice = nextLine(Message.END_MSG);
        		if(isNumeric(choice)) {
                	resultInt = Integer.parseInt(choice);
                } 
                break;
            
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
        return resultInt;
    }
	
	private String chooseGroupString(String msg) {
		String resultStr = null;
        while ( true ) {
            try {
            	System.out.print(msg);
            	String choice = nextLine(Message.END_MSG);
            	resultStr = choice;
                break;
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
        return resultStr;
    }
	
	private static boolean isNumeric(String arg) {
    	for (int i = 0; i < arg.length(); i++) {
    		if (!Character.isDigit(arg.charAt(i))) {
    			return false;
    		}
    	}
    	return true;
    }
}
