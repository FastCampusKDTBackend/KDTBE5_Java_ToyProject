package domain.customer;

import util.DArray;

import java.util.Objects;
import java.util.stream.IntStream;

/**
 * 정적인 객체 배열을 동적 배열로 만들어주는 DArray를 상속
 * DArray 내부의 메소드들은 Collections 클래스에 이미 구현되어 있는 것이지만
 * 학습을 위하여 직접 구현해 보자.
 */
public class Customers extends DArray<Customer> {

    private static Customers customers;

    public static Customers getInstance() {
        if (customers == null) {
            customers = new Customers();
        }
        return customers;
    }

    private Customers() {
    }

/**
 * old logic
 */
//    public Customer[] getCustomers() {
//        Customer[] customerArr = new Customer[size];
//        for (int i = 0; i < size; i++) {
//            customerArr[i] = customers.get(i);
//        }
//        return customerArr;
//    }

    /**
     * Stream을 이용하여 Customer[] 배열을 반환하도록 변경
     * for statement와 Stream 어떤 방법이 더 나을까?
     * @return Customer[] 객체 배열
     */
    public Customer[] getCustomers() {
        return IntStream.range(0, customers.size())
                .mapToObj(index -> customers.get(index))
                .filter(Objects::nonNull)
                .toArray(Customer[]::new);
    }
}
