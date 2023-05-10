package Service;

import domain.customer.Customer;
import domain.customer.Customers;
import domain.group.Group;
import domain.group.GroupType;
import domain.group.Groups;
import domain.menu.CustomerMenu;
import handler.exception.*;

import static resources.Message.*;

import java.util.*;
import java.util.stream.Stream;

public class CustomerService {

    private static final Customers customers = Customers.getInstance();
    private static final CustomerMenu customerMenu = CustomerMenu.getInstance();
    private static final Groups groups = Groups.getInstance();

    private static CustomerService customerService;

    public static CustomerService getInstance() {
        if (customerService == null) {
            customerService = new CustomerService();
        }
        return customerService;
    }

    private CustomerService() {
    }

    public void addCustomer() {
        while (true) {
            try {
                System.out.println("How many customer to input?");
                int repeatNum = customerMenu.nextInt(END_MSG);
                addCustomerDetail(repeatNum);
                break;
            } catch (InputEndException e) {
                System.out.println(e.getMessage());
                break;
            } catch (InputRangeException | InputFormatException | NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void addCustomerDetail(int repeatNum) {
        while (true) { //등록 할 고객 수 만큼 반복
            try {
                int count = 0;
                while (count < repeatNum) {
                    System.out.println("\n====== Customer " + (count + 1) + " Info. ======\n");
                    Customer customer = new Customer();
                    //setCustomerData 메소드로 사용자 정보 추가
                    setCustomer(customer);
                    count++;
                }
                break;
            } catch (InputEndException e) {
                System.out.println(e.getMessage());
                break;
            } catch (NumberFormatException e) {
                throw new InputRangeException();
            } catch (InputRangeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setCustomer(Customer customer) {
        while (true) {
            try {
                int choice = customerMenu.selectMenu(new String[]{
                        "Customer Name",
                        "Customer ID",
                        "Customer Spent Time",
                        "Customer Total Pay",
                        "Back"
                });

                if (choice == 1) setCustomerName(customer);
                if (choice == 2) setCustomerId(customer);
                if (choice == 3) setCustomerSpentTime(customer);
                if (choice == 4) setCustomerTotalPay(customer);
                if (choice == 5) {
                    //모든 사용자 데이터 입력 작업이 끝나면 유저 등급 갱신 후 입력 종료
                    System.out.println("Updating user information. Wait a moment.");
                    customerService.refresh(groups);
                    break; //choice == 5
                }
            } catch (InputRangeException | NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setCustomerName(Customer customer) {
        try {
            System.out.println("Input Customer's Name:");
            customer.setCusName(customerMenu.nextLine(END_MSG));
        } catch (InputEndException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    private void setCustomerId(Customer customer) {

        try {
            System.out.println("Input Customer's ID:");
            customer.setCusId(customerMenu.nextLine(END_MSG));
        } catch (InputEndException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    private void setCustomerSpentTime(Customer customer) {
        try {
            System.out.println("Input Customer's Spent Time:");
            customer.setCusTotalTime(customerMenu.nextInt(END_MSG));
        } catch (InputEndException e) {
            System.out.println(e.getMessage());
            return;
        } catch (InputFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    private void setCustomerTotalPay(Customer customer) {
        try {
            System.out.println("Input Customer's Total Payment:");
            customer.setCusTotalPay(customerMenu.nextInt(END_MSG));
        } catch (InputEndException e) {
            System.out.println(e.getMessage());
            return;
        } catch (InputFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showCustomer() {
        if (customers.size() == 0) {
            System.out.println(ERR_MSG_INVALID_ARR_EMPTY);
            return;
        }

        System.out.println("\n======= Customer Info. =======");
        for (int i = 0; i < customers.size(); i++) {
            System.out.print("No. " + (i + 1) + " => ");
            System.out.println(customers.get(i));
        }
    }

    public void updateCustomer() {
        if (customers.size() == 0) {
            System.out.println(ERR_MSG_INVALID_ARR_EMPTY);
            return;
        }
        showCustomer();

        while (true) {
            try {
                System.out.print("Which customer ( 1 ~ " + customers.size() + " )? ");
                int input = customerMenu.nextInt(END_MSG);
                if (customerMenu.isInvalidRange(input, customers.size())) {
                    throw new InputRangeException();
                } else {
                    setCustomer(customers.get(input - 1));
                    break;
                }
            } catch (InputEndException e) {
                System.out.println(e.getMessage());
                break;
            } catch (NumberFormatException | InputRangeException | IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void deleteCustomer() {

        if (customers.size() == 0) {
            System.out.println(ERR_MSG_INVALID_ARR_EMPTY);
            return;
        }
        showCustomer();
        while (true) {
            try {
                System.out.print("Which customer ( 1 ~ " + customers.size() + " )? ");
                int input = customerMenu.nextInt(customerMenu.nextLine());
                if (customerMenu.isInvalidRange(input, customers.size())) {
                    throw new InputRangeException();
                } else {
                    System.out.println(customers.pop(input - 1));
                    break;
                }
            } catch (InputEndException e) {
                System.out.println(e.getMessage());
                break;
            } catch (InputFormatException | IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void refresh(Groups groups) {
        try {
            if (groups == null) {
                throw new ArrayEmptyException();
            }
            //customers[] 배열의 GroupType 갱신
            for (int i = 0; i < customers.size(); i++) {
                customers.get(i).setGroupType(findGroupType(groups, customers.get(i)));
            }
        } catch (ArrayEmptyException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private GroupType findGroupType(Groups groups, Customer customer) {
        try {
            if (groups == null) throw new ArrayEmptyException();
            Group matchGroup = groups.get(0); //초기값

            Group group = groups.get(0); // 처음에는 NONE

            /**
             * 사용자의 이용시간과 금액을 제시된 기준과 비교하여 현재의 등급이 낮으면 새로운 등급으로 설정
             *
             * @Param: comparingIndex 0:NONE, 1:GENERAL, 2:VIP, 3:VVIP
             * @return: comparingIndex가 분류 기준 GroupType의 index보다 작으면 새로운 groupType울 반환
             */
            for (int i = groups.size() - 1; i > -1; i--) {
                Group comparingGroup = groups.get(i);
                int comparingTotalTime = comparingGroup.getParameter().getMinTime();
                int comparingTotalPay = comparingGroup.getParameter().getMinPay();

                if (customer.getCusTotalTime() >= comparingTotalTime &&
                        customer.getCusTotalPay() >= comparingTotalPay) {
                    return groups.get(i).getGroupType();
                }
            }
        } catch (ArrayEmptyException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * 정렬 기준을 Comparator에 파라미터로 전달하면 정렬된 Customer 배열을 반환.
     *
     * @param comparator: 정렬 기준(name, TotalTime, TotalPay)
     * @return 정렬된 Customer[] 배열을 반환
     */
    public Customer[] getSortedCustomers(Comparator<Customer> comparator, Customers customers) {
        Customer[] customerArr = customers.getCustomers();
        Arrays.sort(customerArr, comparator);
        return customerArr;
    }
}