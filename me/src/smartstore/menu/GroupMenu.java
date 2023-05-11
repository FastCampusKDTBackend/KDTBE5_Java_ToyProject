package smartstore.menu;

import smartstore.customer.Customers;
import smartstore.exception.InputEndException;
import smartstore.exception.InputTypeException;
import smartstore.group.Group;
import smartstore.group.GroupType;
import smartstore.group.Groups;
import smartstore.group.Parameter;
import smartstore.util.Message;

public class GroupMenu implements Menu {
    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();
    // singleton
    private static GroupMenu groupMenu;

    public static GroupMenu getInstance() {
        if (groupMenu == null) {
            groupMenu = new GroupMenu();
        }
        return groupMenu;
    }

    private GroupMenu() {}

    @Override
    public void manage() {
        while ( true ) { // 서브 메뉴 페이지를 유지하기 위한 while
        	try {
        		int choice = chooseMenu(new String[]{
                        "Set Parameter",
                        "View Parameter",
                        "Update Parameter",
                        "Back"});

                if (choice == 1) setParameter();
                else if (choice == 2) viewParameter();
                else if (choice == 3) updateParameter();
                else break; // choice == 4
        	} catch (InputTypeException e) {
				System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
			} catch (IllegalArgumentException e) {
				System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
			}
        }
    }

    private void setParameter() { // 초기화할 때만 호출 가능
        while ( true ) {
    		GroupType groupType = chooseGroup();
    		if (groupType == null) break;
            // GroupType에 해당하는 group 객체를 찾아야 함
    		
            Group group = allGroups.find(groupType); // 고객 등급 찾음.
            
            if (group == null) group.setGroupType(groupType);
            
        	if (group.getParameter() != null) { // group.getParameter()이 null이 아니면 이미 초기화됨
                System.out.println("\n" + group.getGroupType() + " group already exists.");
                
                printParameters(group);
            } else {
            	Parameter parameter = new Parameter();
                // time, pay 사용자 입력받은 후, 설정 필요
            	System.out.println();
                parameter.manage();
                
                group.setParameter(parameter);
                
                if (allCustomers.refresh(allGroups) == -1) break;
                
                if (group.getParameter() != null) {
                	
                	printParameters(group);
                }
            }
        }
    }

	private void viewParameter() {
    	 while ( true ) {
     		GroupType groupType = chooseGroup();
     		if (groupType == null) break;
     		
     		Group group = allGroups.find(groupType); // 고객 등급 찾음.
     		
     		if (group == null) break;
     		
     		printParameters(group);
     		
    	 }
	}
	
	private void updateParameter() {
		 while ( true ) {
	     		GroupType groupType = chooseGroup();
	     		if (groupType == null) break;
	     		Group group = allGroups.find(groupType); // 고객 등급 찾음.
	     		if (group == null) break;
	     		if (group.getParameter() != null) { // group.getParameter()이 null이 아니면 이미 초기화됨
	     			
	     			printParameters(group);
	                
	                Parameter parameter = new Parameter();
	                parameter.manage();
	                
	                group.setParameter(parameter);
	                
	                if (allCustomers.refresh(allGroups) == -1) break;
	                
	                if (group.getParameter() != null) {
	                	printParameters(group);
	                }
	            } else {
	            	System.out.println("\nNo parameter. Set the parameter first.\n");
	            	break;
	            }
    	 }
	}
	
	private void printParameters(Group group) {
		System.out.println("\nGroupType: " + group.getGroupType() +
				   "\nParameter: " + group.getParameter() + "\n");
	}
    
    private GroupType chooseGroup() {
        while ( true ) {
            try {
                System.out.print("Which group (GENERAL (G), VIP (V), VVIP (VV))? ");
                String choice = nextLine(Message.END_MSG);
                // group (str) -> GroupType (enum)
                // "VIP" -> GroupType.VIP
                
                GroupType groupType = GroupType.valueOf(choice).replaceFullName();
                return groupType;
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                return null;
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
			}
        }
    }
}