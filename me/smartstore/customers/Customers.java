package me.smartstore.customers;

import java.util.Arrays;
import me.smartstore.groups.Group;
import me.smartstore.groups.GroupType;
import me.smartstore.groups.Groups;

public class Customers {
    private static Customers allCustomers;

    public static Customers getInstance() {
        if (allCustomers == null)
            allCustomers = new Customers();
        return allCustomers;
    }

    private final Groups allGroups = Groups.getInstance();

    protected static final int DEFAULT_SIZE = 10;

    protected int capacity;
    protected int size;
    protected Customer[] customers;

    public Customers() {
        this.customers = new Customer[DEFAULT_SIZE];
        this.capacity = DEFAULT_SIZE;
    }

    public Customers(int initialCapacity) {
        this.customers = new Customer[initialCapacity];
        this.capacity = initialCapacity;
    }

    public Customers(Customer[] customers) {
        this.customers = customers;
        this.capacity = customers.length;
        this.size = customers.length;
    }

    public void setCustomers(Customer[] customers) {
        this.customers = customers;
    }

    public Customer[] getCustomers() {
        return this.customers;
    }

    public Customer[] getRealCustomers() {
        int real = 0;
        for (int i = 0; i < this.size; i++) {
            if (this.customers[i] != null)
                real++;
        }
        this.size = real;
        return Arrays.copyOf(this.customers, real);
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int capacity() {
        return this.capacity;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return (this.size == 0);
    }

    public void set(int index, Customer customer) {
        if (index < 0 || index >= this.size)
            return;
        if (customer == null)
            return;
        this.customers[index] = customer;
    }

    public Customer get(int index) {
        if (index < 0 || index >= this.size)
            return null;
        return this.customers[index];
    }

    public int indexOf(Customer customer) {
        if (customer == null)
            return -1;
        for (int i = 0; i < this.size; i++) {
            if (this.customers[i] != null && this.customers[i].equals(customer))
                return i;
        }
        return -1;
    }

    public void add(Customer customer) {
        if (customer == null)
            return;
        if (this.size < this.capacity) {
            this.customers[this.size] = customer;
            this.size++;
        } else {
            grow();
            add(customer);
        }
    }

    public void add(int index, Customer customer) {
        if (index < 0 || index > this.size)
            return;
        if (customer == null)
            return;
        if (this.size < this.capacity) {
            for (int i = this.customers.length - 1; i >= index; i--)
                this.customers[i + 1] = this.customers[i];
            this.customers[index] = customer;
            this.size++;
        }   else {
            grow();
            add(index, customer);
        }
    }

    public void grow() {
        Customer[] copy = Arrays.copyOf(this.customers, this.customers.length);
        this.capacity *= 2;
        this.customers = new Customer[this.capacity];
        System.arraycopy(copy, 0, this.customers, 0, copy.length);
        this.size = copy.length;
    }

    public void pop(int index) {
        if (this.size == 0)
            return;
        if (index < 0 || index >= this.size)
            return;
        this.customers[index] = null;
        for (int j = index + 1; j < this.size; j++)
            this.customers[j - 1] = this.customers[j];
        this.customers[this.size - 1] = null;
        this.size--;
    }

    public void pop() {
        if (this.size == 0)
            return;
        this.customers[this.size - 1] = null;
        this.size--;
    }

    public void pop(Customer customer) {
        if (this.size == 0)
            return;
        if (customer == null)
            return;
        pop(indexOf(customer));
    }

    public Customers trimToSize() {
        Customer[] newCustomers = Arrays.copyOf(this.customers, this.size);
        this.customers = newCustomers;
        this.capacity = this.size;
        return new Customers(newCustomers);
    }

    public Customers findCustomers(GroupType type) {
        Customers custs = new Customers();
        for (int i = 0; i < this.size; i++) {
            Customer cust = get(i);
            if (cust == null)
                return null;
            Group grp = cust.getGroup();
            if (type == GroupType.NONE) {
                if (grp == null || grp.getType() == null || grp.getType() == GroupType.NONE)
                    custs.add(cust);
            } else if (grp != null && grp.getType() == type) 
            {
                custs.add(cust);
            }
        }
        return custs;
    }

    public Customers findCustomers(Group grp) {
        if (grp != null) {
            if (grp.getType() != null)
                return findCustomers(grp.getType());
            System.out.println("Customers.findCustomers() Error: 그룹 타입이 존재하지 않습니다.");
            return null;
        }
        System.out.println("Customers.findCustomers() Error: 그룹이 없습니다.");
        return null;
    }

    public void refresh(Groups groups) {
        if (groups == null)
            return;
        for (int i = 0; i < this.size; i++) {
            Customer cust = this.customers[i];
            cust.setGroup(groups.findGroupFor(cust));
        }
    }

    public void print() {
        for (int i = 0; i < this.size; i++) {
            if (this.customers[i] != null)
                System.out.printf("No. %4d => %s\n", i + 1, this.customers[i]);
        }
    }

    public ClassifiedCustomersGroup classified() {
        ClassifiedCustomersGroup classifiedCusGroup = ClassifiedCustomersGroup.getInstance();
        for (int i = 0; i < this.allGroups.size(); i++) {
            Group grp = this.allGroups.get(i);
            Customer[] customers = grp.getCustomers(allCustomers).trimToSize().getCustomers();
            Customer[] copy = Arrays.copyOf(customers, customers.length);
            ClassifiedCustomers classifiedCustomers = new ClassifiedCustomers();
            classifiedCustomers.setGroup(grp);
            classifiedCustomers.setSize(copy.length);
            classifiedCustomers.setCustomers(copy);
            classifiedCusGroup.set(i, classifiedCustomers);
        }
        return classifiedCusGroup;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.size; i++) {
            if (this.customers[i] != null)
                str.append(String.format("No. %4d => %s\n", i + 1, this.customers[i]));
        }
        return str.toString();
    }
}
