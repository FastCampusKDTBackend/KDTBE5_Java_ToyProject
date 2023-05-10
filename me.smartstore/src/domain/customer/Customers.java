package domain.customer;

import domain.group.GroupType;
import util.DArray;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//정적인 객체 배열을 동적 배열로 만들어주는 DArray를 상속
//DArray 내부의 메소드들은 Collections 클래스에 이미 구현되어 있는 것이지만
//학습을 위하여 직접 구현해 보자.
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

    //TODO DArray에 의존적인 메서드의 rafactoring 생각해 보기
    public Customer[] getCustomers() {
        Customer[] customerArr = new Customer[size];
        for (int i = 0; i < size; i++) {
            customerArr[i] = customers.get(i);
        }
        return customerArr;
    }
}
