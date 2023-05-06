package me.day10.smartstore.customer;

public class CustomerRepository {

    private static class InstanceHolder {
        private static final CustomerRepository INSTANCE = new CustomerRepository();
    }
    public static CustomerRepository getInstance() { return InstanceHolder.INSTANCE; }
    private CustomerRepository() {}

    private List<Customer> customerList = new List<>();
    private static Customer tempCustomer;

    public void checkIfCanAddMore() throws MaxCapacityReachedException {
        if (customerList.isReachedMaxCapacity())
            throw new MaxCapacityReachedException("Repository reached the max capacity.\n");
    }

    public void resetTempCustomer() {
        tempCustomer = new Customer();
    }

    @Override
    public String toString() {
        if (customerList.isEmpty()) return "No Customers. Please input one first.\n";
        return super.toString();
    }
}
