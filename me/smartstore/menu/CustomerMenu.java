package me.smartstore.menu;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEmptyException;
import me.smartstore.exception.InputEndException;
import me.smartstore.group.Groups;
import me.smartstore.util.Message;

public class CustomerMenu implements Menu {
    // singleton

    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();
    private static CustomerMenu customerMenu;

    public static CustomerMenu getInstance() {
        if (customerMenu == null) {
            customerMenu = new CustomerMenu();
        }
        return customerMenu;
    }

    private CustomerMenu() {}

    @Override
    public void manage() {
        while ( true ) { // 서브 메뉴 페이지를 유지하기 위한 while
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

    private void addCustomer() {
        while(true) {
            try {
                System.out.println("How many customers to input : ");
                int inputCount = Integer.parseInt(nextLine(Message.END_MSG));
                for(int i = 0; i < inputCount; i++) {
                    customerInfoInput(i + 1);
                }
                break;
            }catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                System.out.println();
                break;
            }
        }
    }

    private void customerInfoInput(int count) {
        Customer addCustomer = new Customer();
        while(true) {
            try {
                System.out.printf("=============== Customer %d Info. ===============\n", count);
                int choice = chooseMenu(new String[]{
                        "Customer Name",
                        "Customer ID",
                        "Customer Spent Time",
                        "Customer Total Pay",
                        "Back"
                });
                if(choice == 1) {
                    System.out.print("Input Customer Name : ");
                    String customerName = nextLine(Message.END_MSG);
                    addCustomer.setCusName(customerName);
                }else if (choice == 2) {
                    System.out.print("Input Customer ID : ");
                    String customerId = nextLine(Message.END_MSG);
                    addCustomer.setCusId(customerId);
                }else if(choice == 3) {
                    System.out.print("Input Customer Spent Time : ");
                    int customerSpentTime = Integer.parseInt(nextLine(Message.END_MSG));
                    addCustomer.setCusTotalTime(customerSpentTime);
                }else if(choice == 4) {
                    System.out.print("Input Customer Total Pay : ");
                    int customerTotalPay = Integer.parseInt(nextLine(Message.END_MSG));
                    addCustomer.setCusTotalPay(customerTotalPay);
                }else {
                    if(addCustomer.getCusId() == null || addCustomer.getCusName() == null ||
                            addCustomer.getCusTotalTime() == null || addCustomer.getCusTotalPay() == null) {
                        System.out.println(Message.ERR_MSG_INVALID_INPUT_NULL);
                    }else {
                        allCustomers.add(addCustomer);
                        allCustomers.refresh(allGroups);
                        System.out.printf(
                                "＞　Success !!! %s  ＜\n" +
                                        "           .A__A    ✨\uD83C\uDF82✨    A__A\n" +
                                        "           ( •⩊•)  _______ (•⩊• )\n" +
                                        "           (>\uD83C\uDF70>)   |   |   (<\uD83D\uDD2A<)\n", addCustomer);
                        break;
                    }
                }
            }catch (InputEmptyException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_EMPTY);
            }catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
                System.out.println();
            }
        }
    }

    private void viewCustomer() {
        if(allCustomers.size() == 0) {
            System.out.println(Message.ERR_MSG_INVALID_ARR_EMPTY);
            System.out.println();
        }else {
            System.out.println("======= Customer Info. =======");
            for(int i = 0; i < allCustomers.size(); i++) {
                System.out.printf("No. %3d => ", i + 1 );
                System.out.println(allCustomers.get(i));
                System.out.println();
            }
        }
    }

    private void updateCustomer() {
        if(allCustomers.size() == 0) {
            System.out.println(Message.ERR_MSG_INVALID_ARR_EMPTY);
            System.out.println();
        }else {
            while(true) {
                try {
                    viewCustomer();
                    System.out.printf("Which customer ( 1 ~ %d ) ? : ", allCustomers.size());
                    int updateIndex = Integer.parseInt(nextLine(Message.END_MSG));
                    Customer updateCustomer = allCustomers.get(updateIndex - 1);
                    while(true) {
                        int choice = chooseMenu(new String[]{
                                "Customer Name",
                                "Customer ID",
                                "Customer Spent Time",
                                "Customer Total Pay",
                                "Back"
                        });
                        if(choice == 1) {
                            System.out.print("Input Customer Name : ");
                            String customerName = nextLine();
                            updateCustomer.setCusName(customerName);
                        }else if (choice == 2) {
                            System.out.print("Input Customer ID : ");
                            String customerId = nextLine();
                            updateCustomer.setCusId(customerId);
                        }else if(choice == 3) {
                            System.out.print("Input Customer Spent Time : ");
                            int customerSpentTime = Integer.parseInt(nextLine());
                            updateCustomer.setCusTotalTime(customerSpentTime);
                        }else if(choice == 4) {
                            System.out.print("Input Customer Total Pay : ");
                            int customerTotalPay = Integer.parseInt(nextLine());
                            updateCustomer.setCusTotalPay(customerTotalPay);
                        }else {
                            allCustomers.refresh(allGroups);
                            System.out.printf(
                                    "SUCCESS UPDATE!!\n" +
                                    "%s\n" +
                                    "두다다다다다다다\n" +
                                    "　(∩`・ω・)\n" +
                                    "＿/_ミつ/￣￣￣/\n" +
                                    "　　＼/＿＿＿/\n", updateCustomer);
                            break;
                        }
                    }
                }catch (InputEndException e ) {
                    System.out.println(Message.ERR_MSG_INPUT_END);
                    break;
                }catch (NumberFormatException e){
                    System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
                }catch (IndexOutOfBoundsException e) {
                    System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
                }
            }
        }
    }

    private void deleteCustomer() {
        if(allCustomers.size() == 0) {
            System.out.println(Message.ERR_MSG_INVALID_ARR_EMPTY);
            System.out.println();
        }else {
            while(true) {
                try {
                    viewCustomer();
                    if(allCustomers.size() == 0) {
                        System.out.println(Message.ERR_MSG_INVALID_ARR_EMPTY);
                        break;
                    }
                    System.out.printf("Which customer ( 1 ~ %d ) ? : ", allCustomers.size());
                    int updateIndex = Integer.parseInt(nextLine(Message.END_MSG));
                    allCustomers.pop(updateIndex - 1);
                    System.out.println(
                            "SUCCESS DELETE!!\n" +
                                    "두다다다다다다다\n" +
                                    "두다다다다다다다\n" +
                                    "　(∩`・ω・)\n" +
                                    "＿/_ミつ/￣￣￣/\n" +
                                    "　　＼/＿＿＿/\n");
                }catch (InputEndException e) {
                    System.out.println(Message.ERR_MSG_INPUT_END);
                    break;
                }catch (NumberFormatException e) {
                    System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
                }catch (IndexOutOfBoundsException e) {
                    System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
                }
            }
        }
    }
}
