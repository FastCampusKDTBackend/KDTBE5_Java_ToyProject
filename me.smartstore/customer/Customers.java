package customer;

import arrays.DArray;
import group.Group;
import group.Groups;

import java.util.Arrays;
import java.util.Comparator;

public class Customers extends DArray<Customer> {
    private static Customers allCustomers;

    public static Customers getInstance() {
        if (allCustomers == null) {
            allCustomers = new Customers();
        }
        return allCustomers;
    }

    public void refresh(Groups groups) {
        // 1. 분류 기준이 바뀔 때
        // 2. 새로운 고객이 들어올 때
        // refresh 함수 호출을 통해 그룹 다시 설정
    }

}
