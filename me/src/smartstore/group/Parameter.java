package smartstore.group;

import java.util.Objects;

import smartstore.exception.InputEndException;
import smartstore.exception.InputTypeException;
import smartstore.menu.Menu;
import smartstore.util.Message;

//등급 나누는 기준.
public class Parameter implements Menu { 
    private Integer minTime;
    private Integer minPay;


    public Parameter() {
    }

    public Parameter(Integer minTime, Integer minPay) {
        this.minTime = minTime;
        this.minPay = minPay;
    }

    public Integer getMinTime() {
        return minTime;
    }

    public void setMinTime(Integer minTime) {
        this.minTime = minTime;
    }

    public Integer getMinPay() {
        return minPay;
    }

    public void setMinPay(Integer minPay) {
        this.minPay = minPay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameter parameter = (Parameter) o;
        return Objects.equals(minTime, parameter.minTime) && Objects.equals(minPay, parameter.minPay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minTime, minPay);
    }

	@Override
    public String toString() {
        return "Parameter{" +
                "minTime=" + minTime +
                ", minPay=" + minPay +
                '}';
    }

	@Override
	public void manage() {
		while ( true ) {
			try {
				int choice = chooseMenu(new String[]{
	                    "Minimum Spent Time",
	                    "Minimum Total Pay",
	                    "Back"});

	            if (choice == 1) setParameterTime(choice);
	            else if (choice == 2) setParameterPay(choice);
	            else break; // choice == 3
			} catch (InputTypeException e) {
				System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
			} catch (IllegalArgumentException e) {
				System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
			}
            
        }
	}
	
	public void setParameterTime(int choiceMenu) {
		this.setMinTime(chooseGroup(choiceMenu));
		
	}
	
	private void setParameterPay(int choiceMenu) {
		this.setMinPay(chooseGroup(choiceMenu));
		
	}
	
	public int chooseGroup(int choiceMenu) {
		int result = 0;
        while ( true ) {
            try {
            	if (choiceMenu == 1) { // 이부분을 배열구조로 만들어두면..
            		System.out.print("Input Minimum Spent Time: ");
            	} else if (choiceMenu == 2) {
            		System.out.print("Input Minimum Total Pay: ");
            	}
                String choice = nextLine(Message.END_MSG);
                if(isNumeric(choice)) {
                	result = Integer.parseInt(choice);
                }
                break;
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
        return result;
    }
	
	public static boolean isNumeric(String arg) {
    	for (int i = 0; i < arg.length(); i++) {
    		if (!Character.isDigit(arg.charAt(i))) {
    			return false;
    		}
    	}
    	return true;
    }
}
