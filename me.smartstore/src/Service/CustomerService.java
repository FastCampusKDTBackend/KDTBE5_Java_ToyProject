package Service;

import domain.customer.Customer;
import domain.customer.Customers;
import domain.group.Group;
import domain.group.GroupType;
import domain.group.Groups;
import domain.menu.CustomerMenu;
import handler.exception.*;

import java.util.NoSuchElementException;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import static resources.Message.*;

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
        // 고객 등록을 하기 전에 group size가 1이라면 먼저 등록을 유도
        // Groups의 사이즈가 1이라면 default로 등록한 NONE 그룹 외에은 등록되지 않았다는 의미
        if (groups.size() == 1) {
            System.out.println(ERR_MSG_INVALID_GROUP_EMPTY);
            return;
        }

        while (true) {
            try {
                System.out.println("How many customer to input?");
                int repeatNum = customerMenu.nextInt(END_MSG);
                addCustomerDetail(repeatNum);
            } catch (InputEndException e) {
                System.out.println(e.getMessage());
                break;
            } catch (InputFormatException | NoSuchElementException | IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void addCustomerDetail(int repeatNum) {

        for (int count = 0; count < repeatNum; count++) {
            try {
                System.out.println("====== Customer " + (count + 1) + " Info. ======\n");
                System.out.println("After input all the items, select 'Back' to complete the registration.");

                // 신규 Customer 객체 생성 후 개인별로 GroupType을 판별하여 설정해 준다.
                Customer regCustomer = getCustomerDetail();
                regCustomer.setGroupType(findGroupType(regCustomer));

                // 고객등록이 완료되면 Customers 배열 객체에 add 한다.
                customers.add(regCustomer);
                System.out.println("[Registration Complete] => " + regCustomer);

            } catch (InputEndException e) {
                //end 입력시 입력 루틴을 종료
                System.out.println(e.getMessage());
                break;
            } catch (InputRangeException | InputFormatException e) {
                System.out.println(e.getMessage());
            } catch (NoSuchGroupException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }

    private Customer getCustomerDetail() {
        if (groups.size() == 1) {
            throw new NoSuchGroupException();
        }
        String cusName = "";
        String cusId = "";
        Integer cusTotalTime = null;
        Integer cusTotalPay = null;

        while (true) {
            try {
                int choice = showCustomerDetailMenu();

                if (choice == 1) cusName = getCustomerName();
                if (choice == 2) cusId = getCustomerId();
                if (choice == 3) cusTotalTime = getCustomerTotalTime();
                if (choice == 4) cusTotalPay = getCustomerTotalPay();
                if (choice == 5) {
                    //입력 종료시 name과 id가 입력되지 않았다면 재입력 유도
                    if (cusName.isEmpty() || cusId.isEmpty() || cusTotalTime == null ||
                            cusTotalPay == null) {
                        System.out.println("Customer Name : " + cusName + ", CustomerId : " + cusId +
                                ", CustomerTotalTime : " + cusTotalTime + ", CustomerTotalPay : " + cusTotalPay);
                        throw new InputEmptyException();
                    }
                    break;
                }
            } catch (InputRangeException | NumberFormatException | InputFormatException e) {
                System.out.println(e.getMessage());
            } catch (InputEmptyException e) {
                System.out.println("[ERROR] => " + e.getMessage());
            } //end of try-catch
        } //end of while
        return new Customer(cusName, cusId, cusTotalTime, cusTotalPay);
    }

    private int showCustomerDetailMenu() {
        return customerMenu.selectMenu(new String[]{
                "Customer Name",
                "Customer ID",
                "Customer Spent Time",
                "Customer Total Pay",
                "Back"
        });
    }

    private String getCustomerName() {
        while (true) {
            try {
                System.out.println("Input Customer's Name:");
                String name = customerMenu.nextLine(END_MSG);
                if (name.isEmpty()) throw new InputEmptyException();
                return name;
            } catch (InputEndException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
        throw new InputEmptyException();
    }

    private String getCustomerId() {
        while (true) {
            try {
                System.out.println("Input Customer's ID:");
                String inputId = customerMenu.nextLine(END_MSG);

                if (inputId.isEmpty()) throw new InputEmptyException();

                // customerId는 중복이 있을 수 없으므로 중복 체크 후 값을 반환
                // true : id 중복 / false : id 중복 아님
                if (isDupeCusId.test(inputId)) {
                    // 중복이면 다시 입력 유도
                    throw new InvalidIdException();
                }
                return inputId;
            } catch (InputEndException e) {
                System.out.println(e.getMessage());
                break;
            } catch (InvalidIdException e) {
                System.out.println(e.getMessage());
            }
        }
        throw new InputEmptyException();
    }

    private Integer getCustomerTotalTime() {
        while (true) {
            try {
                System.out.println("Input Customer's Spent Time:");
                return customerMenu.nextInt(END_MSG);
            } catch (InputFormatException e) {
                System.out.println(e.getMessage());
            } catch (InputEndException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
        throw new InputEmptyException();
    }

    private Integer getCustomerTotalPay() {
        while (true) {
            try {
                System.out.println("Input Customer's Total Payment:");
                return customerMenu.nextInt(END_MSG);
            } catch (InputFormatException e) {
                System.out.println(e.getMessage());
            } catch (InputEndException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
        throw new InputEmptyException();
    }

    public void showCustomerAll() {
        if (customers.size() == 0) {
            System.out.println(ERR_MSG_INVALID_ARR_EMPTY);
            return;
        }

        System.out.println("======= Customer Info. =======");
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

        while (true) {
            try {
                //1. 먼저 등록된 고객 목록을 모두 보여주고 index 번호를 입력받는다.
                showCustomerAll();
                int input = getCusIndex();

                //2. 입력받은 index가 정상이라면 세부 설정 항목으로 이동
                //index는 0부터 시작하므로 입력받은 수에서 1을 빼준다.
                setCustomerDetail(customers.get(input - 1));
            } catch (IndexOutOfBoundsException | InputFormatException | InputRangeException e) {
                System.out.println(e.getMessage());
            } catch (InputEndException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }

    private int getCusIndex() throws InputEndException, InputFormatException {
        System.out.print("Which customer ( 1 ~ " + customers.size() + " )? ");
        int choice = customerMenu.nextInt(END_MSG);
        if (customerMenu.isInvalidRange(choice, customers.size())) {
            throw new InputRangeException();
        }
        return choice;
    }

    private void setCustomerDetail(Customer targetCustomer) {

        while (true) {
            try {
                int choice = showCustomerDetailMenu();

                if (choice == 1) targetCustomer.setCusName(getCustomerName());
                if (choice == 2) targetCustomer.setCusId(getCustomerId());
                if (choice == 3) targetCustomer.setCusTotalTime(getCustomerTotalTime());
                if (choice == 4) targetCustomer.setCusTotalPay(getCustomerTotalPay());
                if (choice == 5) {
                    if (targetCustomer.getCusName().isEmpty() || targetCustomer.getCusId().isEmpty()) {
                        throw new InputEmptyException();
                    } else break;
                }
            } catch (InputRangeException | InputFormatException | InputEmptyException e) {
                System.out.println(e.getMessage());
            } catch (InputEndException e) {
                System.out.println(e.getMessage());
                break;
            }
            //end of try-catch
        }//end of while
    }

    public void deleteCustomer() {

        if (customers.size() == 0) {
            System.out.println(ERR_MSG_INVALID_ARR_EMPTY);
            return;
        }
        showCustomerAll();

        while (true) {
            try {
                System.out.print("Which customer ( 1 ~ " + customers.size() + " )? ");
                int input = customerMenu.nextInt(END_MSG);

                //true - 범위를 벗어남 / false - 범위 내
                if (customerMenu.isInvalidRange(input, customers.size())) {
                    throw new InputRangeException();
                } else {
                    System.out.println(customers.pop(input - 1));
                    break;
                }
            } catch (InputEndException e) {
                System.out.println(e.getMessage());
                break;
            } catch (InputFormatException | InputRangeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Customer가 추가되거나 Group Parameter가 변경되면 고객 등급 초기화
     */
    public void refresh() {
        if (groups.size() == 0 || customers.size() == 0) {
            System.out.println(ERR_MSG_INVALID_ARR_EMPTY);
            return;
        }
        try {
            //customers[] 배열의 GroupType 갱신
            for (int i = 0; i < customers.size(); i++) {
                customers.get(i).setGroupType(findGroupType(customers.get(i)));
            }
        } catch (ArrayEmptyException | IndexOutOfBoundsException | NoSuchGroupException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 사용자의 이용시간과 금액을 등록된 기준별로 매칭하여 반환
     *
     * @param customer : Customer 객체
     * @return GroupType
     */
    private GroupType findGroupType(Customer customer) {
        // groups size가 1이라면 등록을 먼저 하도록 유도
        if (groups.size() == 1) {
            throw new NoSuchGroupException();
        }
        // groups/get(1) => GENERAL Type
        // Customer의 사용시간과 이용금액이 GENERAL Type 기준보다 작으면 NONE
        if (lessThanGeneral.test(groups.get(1), customer)) {
            return GroupType.NONE;
        }

        // VVIP 부터 GENERAL까지 역순으로 Group Parameter 기준에 맞는지 체크
        for (int i = (groups.size() - 1); i > 0; i--) {
            try {
                if (compareParameter.test(groups.get(i), customer)) {
                    return groups.get(i).getGroupType();
                }
            } catch (ArrayEmptyException | IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
        // for문을 다 돌았는데도 맞는 조건이 없다면 NONE
        return GroupType.NONE;
    }

    /**
     * 입력받은 customerID의 중복을 체크
     *
     * @param inputId : 중복 체크할 customerId
     * @return : true : id 중복 / false : id 중복 아님
     */
    Predicate<String> isDupeCusId = inputId -> {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCusId().equals(inputId)) {
                return true;
            }
        }
        return false;
    };

    /**
     * Customer의 총 이용시간과 총 이용금액이 GENERAL Type 기준보다 작은지 체크
     * 작으면 true / 그렇지 않으면 false
     */
    BiPredicate<Group, Customer> lessThanGeneral = (group, customer) -> {
        if (customer.getCusTotalPay() < group.getParameter().getMinPay() ||
                customer.getCusTotalTime() < group.getParameter().getMinTime()) {
            return true;
        }
        return false;
    };

    /**
     * Customer의 Parameter의 값이 Group에 등록된 Parameter 값보다 크거나 같으면 true
     * 아니면 false return
     */
    BiPredicate<Group, Customer> compareParameter = (group, customer) -> {
        if (customer.getCusTotalPay() >= group.getParameter().getMinPay() &&
                customer.getCusTotalTime() >= group.getParameter().getMinTime()) {
            return true;
        }
        return false;
    };
}
