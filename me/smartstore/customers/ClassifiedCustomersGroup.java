package me.smartstore.customers;

import java.util.Arrays;
import java.util.Comparator;
import me.smartstore.groups.GroupType;
import me.smartstore.groups.Parameter;

public class ClassifiedCustomersGroup {
    private static ClassifiedCustomersGroup classifiedCustomersGroup;

    private ClassifiedCustomers[] classifiedCustomers;

    public static ClassifiedCustomersGroup getInstance() {
        if (classifiedCustomersGroup == null)
            classifiedCustomersGroup = new ClassifiedCustomersGroup();
        return classifiedCustomersGroup;
    }

    public ClassifiedCustomersGroup() {
        this.classifiedCustomers = new ClassifiedCustomers[GroupType.size()];
        for (int i = 0; i < GroupType.size(); i++)
            this.classifiedCustomers[i] = new ClassifiedCustomers();
    }

    public ClassifiedCustomers get(int i) {
        return classifiedCustomers[i];
    }

    public void set(int i, ClassifiedCustomers customers) {
        classifiedCustomers[i] = customers;
    }

    public int size() {
        return GroupType.size();
    }

    public void print() {
        for (int i = 0; i < classifiedCustomers.length; i++) {
            System.out.println("\n==============================");
            if (classifiedCustomers[i] == null)
                return;
            GroupType groupType = classifiedCustomers[i].getGroup().getType();
            Parameter parameter = classifiedCustomers[i].getGroup().getParam();
            System.out.printf("그룹: %s (시간: %d, 금액: %d)\n", groupType, 
                    (parameter != null) ? parameter.getMinimumSpentTime() : null, 
                    (parameter != null) ? parameter.getMinimumTotalPay() : null);
            System.out.println("==============================");
            if (classifiedCustomers[i].isEmpty()) {
                System.out.println("빈 값");
            } else {
                classifiedCustomers[i].print();
                System.out.println("==============================\n");
            }
        }
    }

    public void sort(Comparator<Customer> comparator) {
        for (int i = 0; i < classifiedCustomersGroup.size(); i++) {
            Customer[] customers = classifiedCustomersGroup.get(i).getCustomers();
            try {
                if (comparator == null) {
                    Arrays.sort(customers);
                } else {
                    Arrays.sort(customers, comparator);
                }
                classifiedCustomersGroup.get(i).setCustomers(customers);
            } catch (NullPointerException e) {
                System.out.println("빈 배열은 정렬할 수 없습니다.");
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ClassifiedCustomersGroup that = (ClassifiedCustomersGroup) o;
        return Arrays.equals(classifiedCustomers, that.classifiedCustomers);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(classifiedCustomers);
    }

    @Override
    public String toString() {
        return "ClassifiedCustomersGroup{" +
                "classifiedCustomers=" + Arrays.toString(classifiedCustomers) +
                "}";
    }
}
