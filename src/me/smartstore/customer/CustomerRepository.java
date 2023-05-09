package me.smartstore.customer;

import me.smartstore.group.Group;

import java.util.Comparator;

public class CustomerRepository {

    private static class InstanceHolder {
        private static final CustomerRepository INSTANCE = new CustomerRepository();
    }
    public static CustomerRepository getInstance() { return InstanceHolder.INSTANCE; }
    private CustomerRepository() {}

    private final List<Customer> customerList = new List<>();
    private static Customer tempCustomer;
    private static int tempIdx;

    public void checkIfCanAddMore() throws MaxCapacityReachedException {
        customerList.checkIfReachedMaxCapacity();
    }

    public void resetTempCustomer() {
        tempCustomer = new Customer(null, null);
    }

    public void checkIfHasNoDuplicate(String id) throws DuplicateCustomerIdException {
        if (hasCustomerWithId(id))
            throw new DuplicateCustomerIdException("The customer already exists which has same id.");
    }

    private boolean hasCustomerWithId(String id) {
        return findIdxById(id) >= 0;
    }

    private int findIdxById(String id) {
        assert id != null;

        int size = customerList.size();
        for (int i = 0; i < size; ++i)
            if (id.equals(customerList.get(i).getId()))
                return i;
        return -1;
    }

    public void setTempId(String id) throws InvalidCustomerIdException, DuplicateCustomerIdException {
        Customer.checkIfIdIsValid(id);
        checkIfHasNoDuplicate(id);
        tempCustomer.setId(id);
    }

    public void setTempName(String name) throws InvalidCustomerNameException {
        Customer.checkIfNameIsValid(name);
        tempCustomer.setName(name);
    }

    public void setTempSpentHours(Integer spentHours) {
        tempCustomer.setSpentHours(spentHours);
    }

    public void setTempTotalAmountPaid(Integer totalAmountPaid) {
        tempCustomer.setTotalAmountPaid(totalAmountPaid);
    }

    public String getTempInfo() {
        return tempCustomer.toString();
    }

    public void addTempIntoRepository() {
        // app logic에 따라 다음 두 구문이 true 임을 보장합니다.
        assert tempCustomer != null;
        assert !customerList.isReachedMaxCapacity();

        customerList.add(tempCustomer);
        tempCustomer = null;
    }

    public boolean isTempIdNull() {
        return tempCustomer.getId() == null;
    }

    public int size() {
        return customerList.size();
    }

    public String deleteAndGetInfoOf(int num) {
        int idx = num - 1;
        return customerList.remove(idx).toString();
    }

    public String setTempAndGetInfoOf(int num) {
        int idx = num - 1;
        Customer updatingCustomer = customerList.get(idx);
        tempCustomer = new Customer(updatingCustomer);
        tempIdx = idx;
        return updatingCustomer.toString();
    }

    public void updateTempInRepository() {
        customerList.get(tempIdx).copy(tempCustomer);
    }

    public String getUpdateBeforeAndAfterInfo() {
        Customer before = customerList.get(tempIdx);
        Customer after = tempCustomer;
        return String.format("\nNo. %2d\n", tempIdx) +
                "Before: " + before.toString() + '\n'+
                "After : " + after.toString();
    }

    public String getSummary() {
        Group[] groups = Group.values();
        int len = groups.length;
        StringBuilder[] stringBuilders = new StringBuilder[len];
        int[] cnt = new int[len];
        for (int i = 0; i < len; ++i) {
            StringBuilder sb = new StringBuilder();
            sb.append('\n').append("==============================")
                    .append(groups[i])
                    .append("==============================").append('\n');
            stringBuilders[i] = sb;
        }

        int size = customerList.size();
        for (int i = 0; i < size; ++i) {
            Customer customer = customerList.get(i);
            Group group = customer.getGroup();
            for (int j = 0; j < len; ++j) {
                if (group == groups[j]) {
                    stringBuilders[j].append(customer).append('\n');
                    cnt[j]++;
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; ++i) {
            sb.append(stringBuilders[i]);
            if (cnt[i] == 0)
                sb.append("No Customers.").append('\n');
        }
        return sb.append('\n').toString();
    }

    public String getSummaryByName(Order order) {
        Comparator<Customer> cmp = order == Order.ASCENDING ? Customer.ORDER_NAME_ASC
                                                            : Customer.ORDER_NAME_DEC;
        customerList.sort(cmp);
        return getSummary();
    }

    public String getSummaryBySpentHours(Order order) {
        Comparator<Customer> cmp = order == Order.ASCENDING ? Customer.ORDER_SPENT_HOURS_ASC
                                                            : Customer.ORDER_SPENT_HOURS_DEC;
        customerList.sort(cmp);
        return getSummary();
    }

    public String getSummaryByTotalPaidAmount(Order order) {
        Comparator<Customer> cmp = order == Order.ASCENDING ? Customer.ORDER_TOTAL_PAID_AMOUNT_ASC
                                                            : Customer.ORDER_TOTAL_PAID_AMOUNT_DEC;
        customerList.sort(cmp);
        return getSummary();
    }

    @Override
    public String toString() {
        if (customerList.isEmpty()) return "No Customers." + " Please input one first.\n";
        return customerList.toString();
    }
}
