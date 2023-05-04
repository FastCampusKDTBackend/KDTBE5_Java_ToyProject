package domain.customer;

import java.util.Objects;

public class Customer {

    private String name;                // Customer name
    private String id;                  // Customer id
    private Integer storeUsageTime;     // smartStore usage Time
    private Integer totalPaymentAmount; // smartStore total Payment Amount

    public Customer() {

    }

    public Customer(String name, String id, Integer storeUsageTime, Integer totalPaymentAmount) {
        this.name = name;
        this.id = id;
        this.storeUsageTime = storeUsageTime;
        this.totalPaymentAmount = totalPaymentAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStoreUsageTime() {
        return storeUsageTime;
    }

    public void setStoreUsageTime(Integer storeUsageTime) {
        this.storeUsageTime = storeUsageTime;
    }

    public Integer getTotalPaymentAmount() {
        return totalPaymentAmount;
    }

    public void setTotalPaymentAmount(Integer totalPaymentAmount) {
        this.totalPaymentAmount = totalPaymentAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(id, customer.id) && Objects.equals(storeUsageTime, customer.storeUsageTime) && Objects.equals(totalPaymentAmount, customer.totalPaymentAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, storeUsageTime, totalPaymentAmount);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", storeUsageTime=" + storeUsageTime +
                ", totalPaymentAmount=" + totalPaymentAmount +
                '}';
    }
}
