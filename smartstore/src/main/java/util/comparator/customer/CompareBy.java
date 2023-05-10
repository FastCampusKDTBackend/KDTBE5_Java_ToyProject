package util.comparator.customer;

import domain.customer.Customer;
import util.common.exception.NotFoundException;

import java.util.Arrays;
import java.util.Comparator;

public enum CompareBy {
    NAME(new ComparatorByName<>()),
    SPENT_TIME(new ComparatorBySpentTime<>()),
    TOTAL_PAYMENT(new ComparatorByTotalPayment<>());

    private final Comparator<Customer> comparator;

    CompareBy(Comparator<Customer> comparator) {
        this.comparator = comparator;
    }

    public static Comparator<Customer> getComparator(CompareBy compareBy) {
        return Arrays.stream(CompareBy.values())
                .filter(type -> type == compareBy)
                .map(type -> type.comparator)
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
}
