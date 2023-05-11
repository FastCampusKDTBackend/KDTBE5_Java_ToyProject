package me.project.customers;

import me.project.groups.Grade;
import me.project.groups.Group;
import me.project.groups.Groups;
import java.util.Arrays;

public class Customers {

    private int DEFAULT_SIZE = 10;
    protected Customer[] customers;
    private int size = 0; // 실제 배열 capacity
    private int count = 0; // 실제 인스턴스 개수
    private static Customers allCustomers;

    public static Customers getInstance() {
        if (allCustomers == null) {
            allCustomers = new Customers();
        }
        return allCustomers;
    }

    public Customers() {
        customers = new Customer[DEFAULT_SIZE];
        size = DEFAULT_SIZE;
    }
    public Customers(int size) {
        customers = new Customer[size];
        this.size = size;
    }

    public Customers(Customer[] customers) {
        if (customers != null) {
            this.customers = customers;
            count = customers.length;
        }
    }

    public Customer[] getCustomers() {
        return Arrays.copyOf(customers, count);
    }

    public void setCustomers(Customer[] customers) {
        this.customers = customers;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isNull() {
        return this.customers == null;
    }
    public boolean isEmpty() {
        return count == 0;
    }

    public void grow() {
        Customer[] copy = Arrays.copyOf(customers, count);
        size *= 2;
        customers = Arrays.copyOf(copy, size);
    }

    public void add(Customer customer) { // 배열 끝에 추가
        if (customer == null) return;

        if (count < size) {
            customers[count] = customer;
            count++;
        } else {
            grow();
            add(customer);
        }
    }
    public void add(int i, Customer customer) {
        if (!(i >= 0 && i <= count)) return;
        if (customer == null) return;

        if (count < size) {
            Customer[] copy = Arrays.copyOfRange(customers, i, count);
            customers[i] = customer;
            count++;


            System.arraycopy(customers, i + 1, copy, 0, copy.length);

        } else {
            grow();
            add(i, customer);
        }
    }

    public Customer pop(int i) {
        if (!(i >= 0 && i < count)) return null;
        if (isEmpty()) return null;

        Customer[] copy = Arrays.copyOfRange(customers, i + 1, count);
        Customer removeNode = customers[i];
        customers[i] = null;
        count--;

        for(int j = i, k = 0; j < count; j++, k++) {
            customers[j] = copy[k];
        }

        return removeNode;
    }

    public Customer pop() {
        if (isEmpty()) return null;

        Customer popNode = customers[count - 1];
        customers[count - 1] = null;
        count--;

        return popNode;
    }

    public Customer get(int i) {
        if (!(i >= 0 && i < count)) return null;
        return customers[i];
    }

    public void set(int i, Customer customer) {
        if (!(i >= 0 && i < count)) return;
        if (customer == null) return;
        customers[i] = customer;
    }

    public void print() {
        for (int i = 0; i < count; i++) {
            if (customers[i] != null) {
                System.out.printf("No.  %4d => %s\n", (i + 1), customers[i]);
            }
        }
    }

    public Customers findCustomers(Grade grade) {
        Customers custs = new Customers();
        if (custs != null) {
            for (int i = 0; i < count; i++) {
                Customer cust = this.get(i);
                if (cust != null) {
                    Group grp = Groups.getInstance().findGroup(cust);
                    if (grade.equals(Grade.NONE)) {
                        if (grp == null || grp.getGrade() == null || grp.getGrade().equals(Grade.NONE)) {
                            custs.add(cust);
                        }
                    } else if (grp != null && grp.getGrade().equals(grade)) {
                        custs.add(cust);
                    }
                }
            }
        }
        return custs;
    }

    public Customers findCustomers(Group group){
        if (group != null) {
            if (group.getGrade() != null) {
                return this.findCustomers(group.getGrade());
            } else {
                System.out.println("그룹 등급이 존재하지 않습니다.");
                return null;
            }
        } else {
            System.out.println("그룹이 존재하지 않습니다.");
            return null;
        }
    }

    public void refresh(Groups groups) {
        if (groups != null) {
            for(int i = 0; i < this.count; i++) {
                Customer cust = this.customers[i];
                cust.setGroup(groups.findGroup(cust));
            }
        }
    }

    @Override
    public String toString() {
        String str = "";

        for (int i = 0; i < count; i++) {
            str += customers[i] + "\n";
        }
        return str;
    }
}