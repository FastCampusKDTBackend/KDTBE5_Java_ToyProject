package menu;

import customer.Customer;
import customer.Customers;
import exception.InputEndException;
import exception.InputRangeException;
import group.Groups;
import util.Message;

import static util.Message.END_MSG;

public class CustomerMenu implements Menu{
    private final Customers allCustomers = Customers.getInstance();
    private final Groups allGroups = Groups.getInstance();
    private static CustomerMenu customerMenu;

    public static CustomerMenu getInstance(){
        if(customerMenu == null){
            customerMenu = new CustomerMenu();
        }
        return customerMenu;
    }
    private CustomerMenu(){}

    @Override
    public void manage() {
        while (true) {
            int choice = chooseMenu(new String[]{
                    "Add Customer",
                    "View Customer",
                    "Update Customer",
                    "Delete Customer",
                    "Back"
            });
            if(choice == 1) addCustomer();
            else if(choice == 2) viewCustomer();
            else if(choice == 3) updateCustomer();
            else if(choice == 4) deleteCustomer();
            else break;
        }

    }


    /**
     * 고객 추가 메소드 end 입력시 종료
     * */
    public void addCustomer(){
        while(true){
            System.out.println("How many customers to input?");
            try{
                String num = nextLine(Message.END_MSG);
                for(int i=0; i<Integer.parseInt(num); i++){
                    System.out.printf("====== Customer %d Info. ====== \n", i+1);
                    Customer customer = new Customer();
                    addCustomerManage(customer);
                    allCustomers.refresh();
                }
                break;
            } catch (InputEndException e){
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            }
        }
    }

    /**
     * 고객 추가 메소드에서 호출 이름, 아이디, 시간, 돈중에 추가할 정보를 선택
     * */
    private void addCustomerManage(Customer customer) {
        while (true) {
            int choice = chooseMenu(new String[]{
                    "Customer Name",
                    "Customer ID",
                    "Customer Spent Time",
                    "Customer Total Pay",
                    "Back"
            });
            if(choice == 1) addCustomerName(customer);
            else if(choice == 2) addCustomerID(customer);
            else if(choice == 3) addCustomerSpentTime(customer);
            else if(choice == 4) addCustomerTotalPay(customer);
            else break;
        }
    }
    //이름 추가 end입력시 종료
    private void addCustomerName(Customer customer) {
        System.out.println("Input Customer's Name:");
        try{
            String name = nextLine(Message.END_MSG);
            customer.setcName(name);
        } catch (InputEndException e){
            System.out.println(Message.ERR_MSG_INPUT_END);
        }
    }
    //ID추가 end입력시 종료
    private void addCustomerID(Customer customer) {
        System.out.println("Input Customer's ID:");
        try{
            String id = nextLine(Message.END_MSG);
            customer.setcId(id);
        } catch (InputEndException e){
            System.out.println(Message.ERR_MSG_INPUT_END);
        }
    }
    //시간추가 end입력시 종료
    private void addCustomerSpentTime(Customer customer) {
        System.out.println("Input Customer's Spent Time:");
        try{
            String time = nextLine(Message.END_MSG);
            customer.setTotalTime(Integer.parseInt(time));
        } catch (InputEndException e){
            System.out.println(Message.ERR_MSG_INPUT_END);
        }
    }
    //pay추가 end입력시 종료
    private void addCustomerTotalPay(Customer customer) {
        System.out.println("Input Customer's Total Pay:");
        try{
            String pay = nextLine(Message.END_MSG);
            customer.setTotalPay(Integer.parseInt(pay));
        } catch (InputEndException e){
            System.out.println(Message.ERR_MSG_INPUT_END);
        }
    }
    /**
     * 모든 고객의 정보를 출력
     * */
    public void viewCustomer(){
        System.out.println("======= Customer Info. =======");
        for(int i=0; i<allCustomers.size(); i++){
            Customer customer = allCustomers.get(i);
            System.out.println(String.format("No. %d => ", i+1)+ customer);
        }
    }
    /**
     * 고객을 선택하여 정보를 업데이트
     * */
    private void updateCustomer() {
        try {
            viewCustomer();
            System.out.printf("Which customer ( 1 ~ %d )? ", allCustomers.size());
            Integer index = scanner.nextInt();
            scanner.nextLine(); //버퍼에 남아있는 엔터값 처리
            if(index <= 0 || index > allCustomers.size()){
                throw new InputRangeException();
            }
            Customer customer = allCustomers.get(index - 1);
            addCustomerManage(customer);
            allCustomers.refresh();
        } catch (InputRangeException e){
            System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
        }
    }

    /**
     * 고객 정보 삭제
     * */
    private void deleteCustomer() {
        try {
            viewCustomer();
            System.out.printf("Which customer ( 1 ~ %d )? ", allCustomers.size());
            Integer index = scanner.nextInt();
            scanner.nextLine(); //버퍼에 남아있는 엔터값 처리
            if(index <= 0 || index > allCustomers.size()){
                throw new InputRangeException();
            }
            System.out.println(allCustomers.get(index - 1));
            allCustomers.pop(index - 1);
            viewCustomer();
        } catch (InputRangeException e){
            System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
        }
    }

}
