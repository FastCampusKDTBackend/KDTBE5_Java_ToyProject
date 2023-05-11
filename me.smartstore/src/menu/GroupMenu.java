package menu;

import customer.Customers;
import exception.InputEndException;
import group.Group;
import group.GroupType;
import group.Groups;
import group.Parameter;
import util.Message;


public class GroupMenu implements Menu{
    private static GroupMenu groupMenu;
    private final Customers allCustomers = Customers.getInstance();
    private final Groups allGroups = Groups.getInstance();

    public static GroupMenu getInstance(){
        if(groupMenu == null){
            groupMenu = new GroupMenu();
        }
        return groupMenu;
    }
    private GroupMenu(){}

    @Override
    public void manage() {
        while (true) {
            int choice = chooseMenu(new String[]{
                    "Set Parameter",
                    "View Parameter",
                    "Update Parameter",
                    "Back"
            });
            if(choice == 1) setParameter();
            else if(choice == 2) viewParameter();
            else if(choice == 3) updateParameter();
            else break;
        }
    }
    public GroupType chooseGroup(){
        while(true){
            try {
                System.out.println("Which group (GENERAL (G), VIP (V), VVIP (VV))? ");
                String choice = nextLine(Message.END_MSG);
                // group (str) -> GroupType (enum)
                // "VIP" -> GroupType.VIP
                GroupType groupType = GroupType.valueOf(choice).replaceFullName(); // String -> enum
                return groupType;
            }catch (InputEndException e){
                System.out.println(Message.ERR_MSG_INPUT_END);
                return null;
            } catch (IllegalArgumentException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    /**
     * groupType의 시간과 돈울 입력받은 값으로 설정해준다.
     * */
    public void setParameter(){
        while (true){
            GroupType groupType = chooseGroup();
            if(groupType == null){
                System.out.println("break란다");
                break;
            }
            //GroupType에 해당하는 group객체를 찾아야함
            Group group = allGroups.find(groupType);
            if (group != null && group.getParameter() != null) { // group.getParameter()이 null이 아니면 이미 초기화됨
                System.out.println("\n" + group.getGroupType() + " group already exists.");
                System.out.println("\n" + group);
            } else {
                group = new Group(new Parameter(), groupType);
                allGroups.add(group);
                Setmanage(group);
                allCustomers.refresh(); // 파라미터가 변경되었거나 추가되는 경우, 고객 분류를 다시 해야함
            }
        }
    }
    /**
     * 어떤 파라미터를 추가할건지 선택
     * */
    public void Setmanage(Group group) {
        while (true) {
            int choice = chooseMenu(new String[]{
                    "Minimum Spent Time",
                    "Minimum Total Pay",
                    "Back"
            });
            if(choice == 1) setMinTime(group);
            else if(choice == 2) setMinPay(group);
            else break;
        }
    }

    /**
     * groupType의 해당하는 파라미터값을 출력함
     * */
    public void viewParameter(){
        while(true){
            GroupType groupType = chooseGroup();
            if(groupType == null){
                break;
            }
            //GroupType에 해당하는 group객체를 찾아야함
            Group group = allGroups.find(groupType);
            System.out.println(groupType);
            System.out.println(group.getParameter());
        }
    }

    /**
     * groupType의 파라미터값을 수정함
     * */
    public void updateParameter(){
        while(true){
            GroupType groupType = chooseGroup();
            if(groupType == null){
                break;
            }
            //GroupType에 해당하는 group객체를 찾아야함
            Group group = allGroups.find(groupType);
            System.out.println(groupType);
            System.out.println(group.getParameter());
            updateParameterManage(group);
        }
    }
    /**
     * 어떤 파라미터 값을 수정할 것인지 선택
     * */
    public void updateParameterManage(Group group){
        while (true) {
            int choice = chooseMenu(new String[]{
                    "Minimum Spent Time",
                    "Minimum Total Pay",
                    "Back"
            });
            if(choice == 1) setMinTime(group);
            else if(choice == 2) setMinPay(group);
            else break;
        }
    }

    // 시간을 수정 end입력시 종료
    public void setMinTime(Group group){
        while (true){
            try {
                System.out.printf("Input Minimum Spent Time:");
                String spentTime = nextLine(Message.END_MSG);
                Parameter parameter = group.getParameter();
                parameter.setMinTime(Integer.parseInt(spentTime));
                group.setParameter(parameter);
                System.out.println(group.getParameter());
                allCustomers.refresh();// 파라미터가 변경되었거나 추가되는 경우, 고객 분류를 다시 해야함
                break;
            } catch (InputEndException e){
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            }
        }
    }
    //돈을 수정 end입력시 종료
    public void setMinPay(Group group){
        while (true){
            try {
                System.out.printf("Input Minimum Total Pay:");
                String totalPay = nextLine(Message.END_MSG);
                Parameter parameter = group.getParameter();
                parameter.setMinPay(Integer.parseInt(totalPay));
                group.setParameter(parameter);
                System.out.println(group.getParameter());
                allCustomers.refresh();// 파라미터가 변경되었거나 추가되는 경우, 고객 분류를 다시 해야함
                break;
            }catch (InputEndException e){
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            }
        }
    }
}

