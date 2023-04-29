package customer;
import grade.Grade;

public class Customer {
    private int customerId;
    private String customerName;
    private Grade grade;



    //TODO: 2023-04-30  constructor to builder
    Customer(int customerId, String customerName, Grade grade){
        this.customerId = customerId;
        this.customerName = customerName;
        this.grade = grade;
    }

    public void updateUser(int customerId){

    }

    public void deleteUser(int customerId){

    }
}
