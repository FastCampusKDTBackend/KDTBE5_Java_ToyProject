package me.smartstore.utils.constant;

import me.smartstore.domain.customer.Customer;
import me.smartstore.utils.exception.InvalidSortTypeException;

import java.util.Comparator;

public enum SortType {
    CUSTOMER_NAME, CUSTOMER_TIME, CUSTOMER_PAYMENT;

    /**
     * 정렬 기준을 입력으로 받아, 해당 타입에 해당하는 Comparator를 정렬 기준에 맞게 반환합니다.
     * @param classificationVal: -1인 경우 내림차순, 1인 경우 오름차순을 의미합니다.
     * @return 입력받은 정렬 기준을 사용하여 알맞는 Comparator를 반환
     * @throws InvalidSortTypeException: SortType에 존재하지 않는 값이 들어오면 예외를 발생
     */
    public Comparator<Customer> getCustomerComparator(int classificationVal) {
        if (this == CUSTOMER_NAME) {
            return (customer1, customer2) ->
                    classificationVal * customer1.getCustomerName().compareTo(customer2.getCustomerName());
        } else if (this == CUSTOMER_TIME) {
            return (customer1, customer2) ->
                    classificationVal * customer1.getSpentTime().compareTo(customer2.getSpentTime());
        } else if (this == CUSTOMER_PAYMENT) {
            return (customer1, customer2) ->
                    classificationVal * customer1.getTotalPay().compareTo(customer2.getTotalPay());
        }
        throw new InvalidSortTypeException();
    }
}
