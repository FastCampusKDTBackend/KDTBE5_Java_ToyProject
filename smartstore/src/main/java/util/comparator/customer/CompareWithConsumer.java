package util.comparator.customer;

import domain.customer.Customer;
import util.common.exception.NotFoundException;

import java.util.Arrays;
import java.util.Comparator;

public enum CompareWithConsumer {
    NAME(new ComparatorByName<>()),
    SPENT_TIME(new ComparatorBySpentTime<>()),
    TOTAL_PAYMENT(new ComparatorByTotalPayment<>());

    private final Comparator<Customer> comparator;

    CompareWithConsumer(Comparator<Customer> comparator) {
        this.comparator = comparator;
    }

    public static Comparator<Customer> by(CompareWithConsumer compareWithConsumer) {
        return Arrays.stream(CompareWithConsumer.values())
                .filter(type -> type == compareWithConsumer)
                .map(type -> type.comparator)
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
}
