package me.day10.smartstore.customer;

public class CustomerRepository extends List<Customer> {

    private static class InstanceHolder {
        private static final CustomerRepository INSTANCE = new CustomerRepository();
    }
    public static CustomerRepository getInstance() { return InstanceHolder.INSTANCE; }
    private CustomerRepository() {}

    @Override
    public String toString() {
        if (isEmpty()) return "No Customers. Please input one first.\n";
        return super.toString();
    }
}
