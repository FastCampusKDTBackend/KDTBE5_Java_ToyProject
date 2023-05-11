package util.comparator.customer;

import domain.customer.Customer;
import util.common.exception.NotFoundException;

import java.util.Arrays;
import java.util.Comparator;

public enum CompareWithCustomer {
    NAME(new ComparatorByName<>()),
    SPENT_TIME(new ComparatorBySpentTime<>()),
    TOTAL_PAYMENT(new ComparatorByTotalPayment<>());

    private final Comparator<Customer> comparator;

    CompareWithCustomer(Comparator<Customer> comparator) {
        this.comparator = comparator;
    }

    public static Comparator<Customer> by(CompareWithCustomer compareWithCustomer) {
        return Arrays.stream(CompareWithCustomer.values())
                .filter(type -> type == compareWithCustomer)
                .map(type -> type.comparator)
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
}
