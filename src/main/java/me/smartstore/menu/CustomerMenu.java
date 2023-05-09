package me.smartstore.menu;

import me.smartstore.collections.MyArrayList;
import me.smartstore.customer.Customer;
import java.io.IOException;

public class CustomerMenu extends Menu implements DataCRUD {
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

    }

    @Override
    public void updateData() {

    }

    @Override
    public void DeleteData() {

    }

    @Override
    public void back() {

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
