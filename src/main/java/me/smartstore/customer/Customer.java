package me.smartstore.customer;

import me.smartstore.collections.MyArrayList;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;

import java.util.Objects;

public class Customer implements Comparable<Customer> {
    private String name;
    private String id;
    private int hours;
    private int totalAmount;
    private GroupType group;
    private ClassifiedCustomers classifiedCustomers;

    public Customer(String name, String id, int hours, int totalAmount) {
        this.name = name;
        this.id = id;
        this.hours = hours;
        this.totalAmount = totalAmount;
        this.group = judgeCustomerGroup(hours, totalAmount);
        this.classifiedCustomers = ClassifiedCustomers.getInstance();
    }

    private GroupType judgeCustomerGroup(int hours, int totalAmount) {
        //정렬된 groups요소를 돌며 그 요소의 기준 미만이라면 그 grouptype으로 설정
        Groups groups = Groups.getInstance();
        MyArrayList<Group> groupList = groups.getGroups();
        for (int i = 0; i < groupList.size() - 1; i++) {
            int currentMinimumHours = groupList.get(i).getParameter().getMinimumHours();
            int currentMinimumTotalAmount = groupList.get(i).getParameter().getMinimumTotalAmount();
            int nextMinimumHours = groupList.get(i + 1).getParameter().getMinimumHours();
            int nextMinimumTotalAmount = groupList.get(i + 1).getParameter().getMinimumTotalAmount();

            //어떤 기준도 충족 못시킬 때
            if (hours <= currentMinimumHours || totalAmount <= currentMinimumTotalAmount) {
                return GroupType.getGroupType(i);
            }
            //현재 기준 충족 + 다음 기준 미충족 시 현재 기준에 해당하는 등급 부여
            if (hours <= nextMinimumHours && totalAmount <= nextMinimumTotalAmount) {
                return groupList.get(i).getCustomerGroup();
            }
        }
        return GroupType.getGroupType(groupList.size() - 1);
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

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public GroupType getGroup() {
        return group;
    }

    public void setGroup(GroupType group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return hours == customer.hours && totalAmount == customer.totalAmount && Objects.equals(name, customer.name) && Objects.equals(id, customer.id) && group == customer.group;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, hours, totalAmount, group);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", hours=" + hours +
                ", totalAmount=" + totalAmount +
                ", group=" + group +
                '}';
    }


    @Override
    public int compareTo(Customer o) {
        if (classifiedCustomers.getSortBy() == SortBy.NAME) {
            return this.name.compareTo(o.name);
        }
        if (classifiedCustomers.getSortBy() == SortBy.HOURS) {
            return this.hours - o.hours;
        }
        return this.totalAmount - o.totalAmount;
    }
}
