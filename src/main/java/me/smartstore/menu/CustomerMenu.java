package me.smartstore.menu;

import me.smartstore.collections.MyArrayList;
import me.smartstore.customer.Customer;

import java.io.IOException;

public class CustomerMenu extends Menu implements DataCRUD {
    private static CustomerMenu customerMenu;

    private CustomerMenu() {
    }

    public static CustomerMenu getInstance() {
        if (customerMenu == null) {
            customerMenu = new CustomerMenu();
        }
        return customerMenu;
    }

    public void run() {
        int customerMenuNumber = 0;
        while (customerMenuNumber != 5) {
            printCustomerInitMenu();
            customerMenuNumber = readNumber(5);
            if (customerMenuNumber == 1) {
                addData();
            } else if (customerMenuNumber == 2) {
                viewData();
            } else if (customerMenuNumber == 3) {
                updateData();
            } else if (customerMenuNumber == 4) {
                deleteData();
            }
        }
    }

    @Override
    public void addData() {
        try {
            Customer customer = inputCustomerData();
            MyArrayList<Customer> customerList = customers.getCustomers();
            customerList.add(customer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //@Todo 예외처리
    private Customer inputCustomerData() throws IOException {
        //이름, id, 누적 시간, 누적 결제 금액을 차례로 입력받도록 함.
        System.out.println("이름을 입력하세요");
        String name = br.readLine();
        System.out.println("ID를 입력하세요");
        String id = br.readLine();
        System.out.println("누적 이용시간을 입력하세요.");
        int hour = Integer.parseInt(br.readLine());
        System.out.println("누적 결제금액을 입력하세요.");
        int totalAmount = Integer.parseInt(br.readLine());

        return new Customer(name, id, hour, totalAmount);
    }

    @Override
    public void viewData() {
        if (validator.isCustomerListEmpty()) {
            return;
        }
        MyArrayList<Customer> customerList = customers.getCustomers();
        for (int i = 0; i < customerList.size(); i++) {
            System.out.println(customerList.get(i));
        }
    }

    @Override
    public void updateData() {
        //변경할 요소의 index를 입력받음 -> 수정 희망하는 정보 받음
        viewData();
        MyArrayList<Customer> customerList = getCustomers();
        if (customerList == null) {
            return;
        }
        int listSize = customerList.size();
        System.out.print("수정을 희망하는 고객 번호를 입력해주세요");
        System.out.print(listSize >= 2 ? "(1~" + listSize + ")" : "");
        int customerNumber = readNumber(listSize);

        printCustomerUpdateMessage();
        int updateMenuNumber = 0;
        try {
            updateMenuNumber = Integer.parseInt(br.readLine());
            if (updateMenuNumber == 5) {
                return;
            }
            inputNewData(updateMenuNumber, customerNumber);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private MyArrayList<Customer> getCustomers() {
        MyArrayList<Customer> customerList = customers.getCustomers();
        if (customerList.isEmpty()) {
            System.out.println("등록된 고객정보가 존재하지 않습니다.");
            //예외발생 vs null리턴?
            return null;
        }
        return customerList;
    }

    private void printCustomerUpdateMessage() {
        System.out.println("==============================");
        System.out.println("수정할 정보를 선택해주세요");
        System.out.println("1. 이름");
        System.out.println("2. ID");
        System.out.println("3. 누적 이용 시간");
        System.out.println("4. 누적 결제 금액");
        System.out.println("5. 수정 취소");
        System.out.println("==============================");
        System.out.print("수정할 정보를 선택해주세요(1~5): ");
    }

    private void inputNewData(int updateMenuNumber, int customerNumber) throws IOException {
        MyArrayList<Customer> customerList = customers.getCustomers();
        Customer customer = customerList.get(customerNumber - 1);

        if (updateMenuNumber == 1) {
            System.out.println("새로운 이름을 입력해주세요.");
            String name = br.readLine();
            customer.setName(name);
            return;
        }
        if (updateMenuNumber == 2) {
            System.out.println("새로운 ID를 입력해주세요.");
            String id = br.readLine();
            customer.setId(id);
            return;
        }
        if (updateMenuNumber == 3) {
            System.out.println("새로운 누적 이용 시간을 입력해주세요.");
            int hours = Integer.parseInt(br.readLine());
            customer.setHours(hours);
            return;
        }
        System.out.println("새로운 누적 결제 금액을 입력해주세요.");
        int totalAmount = Integer.parseInt(br.readLine());
        customer.setTotalAmount(totalAmount);
    }

    @Override
    public void deleteData() {
        viewData();
        MyArrayList<Customer> customerList = customers.getCustomers();
        int listSize = customerList.size();
        if (listSize < 1) {
            System.out.println("고객이 존재하지 않습니다.");
            return;
        }
        //@Todo 뒤로가기 추가
        System.out.print("삭제할 고객 번호를 입력해주세요");
        System.out.print(listSize >= 2 ? "(1~" + listSize + ")" : "");
        int customerNumber = readNumber(listSize);
        customerList.remove(customerNumber);
    }

    public void printCustomerInitMenu() {
        System.out.println("==============================");
        System.out.println("1. 고객 정보 추가");
        System.out.println("2. 고객 정보 조회");
        System.out.println("3. 고객 정보 수정");
        System.out.println("4. 고객 정보 삭제");
        System.out.println("5. 뒤로 가기");
        System.out.println("==============================");
        System.out.print("메뉴번호를 입력해주세요: ");
    }
}