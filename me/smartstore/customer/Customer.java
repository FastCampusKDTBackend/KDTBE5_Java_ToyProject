package smartstore.customer;

import smartstore.group.Group;

public class Customer {

    private String id;
    private String name;
    private int totalTime;
    private int totalPay;
    private Group group;

    public static class Builder {

        private final String id;
        private final String name;

        private int totalTime = 0;
        private int totalPay = 0;
        private Group group;

        public Builder(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public Builder totalTime(int value) {
            totalTime = value;
            return this;
        }

        public Builder totalPay(int value) {
            totalPay = value;
            return this;
        }

        public Builder group(Group type) {
            group = type;
            return this;
        }

        public Customer build(){
            return new Customer(this);
        }
    }

    private Customer(Builder builder) {
        id = builder.id;
        name = builder.name;
        totalTime = builder.totalTime;
        totalPay = builder.totalPay;
        group = builder.group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (totalTime != customer.totalTime) return false;
        if (totalPay != customer.totalPay) return false;
        if (id != null ? !id.equals(customer.id) : customer.id != null) return false;
        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;
        return group != null ? group.equals(customer.group) : customer.group == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + totalTime;
        result = 31 * result + totalPay;
        result = 31 * result + (group != null ? group.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", totalTime=" + totalTime +
                ", totalPay=" + totalPay +
                ", group=" + group +
                '}';
    }
}
