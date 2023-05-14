package com.smartstore.function.customer.view;

import com.smartstore.customer.Customer;
import com.smartstore.customer.Customers;
import com.smartstore.function.*;
import com.smartstore.util.Define;
import com.smartstore.util.List;

public class ViewCustomerByPaging implements MenuPrintable, ValueWithEndValidator,UserDataValidator, AnswerValidator, Handleable {

    private static ViewCustomerByPaging instance;

    private ViewCustomerByPaging(){

    }

    public static ViewCustomerByPaging getInstance() {
        if(instance == null){
            return new ViewCustomerByPaging();
        }
        return instance;
    }

    private boolean indexRangeCheck(int sizeOfList, int index){
        if(index + Define.PAGING > sizeOfList){
            return false;
        }
        return true;
    }

    @Override
    public void run() {
        Customers customers = Customers.getInstance();
        List<Customer> customerList = customers.getCustomerList();
        if (customerList.size() == 0){
            System.out.println("No Customer Data");
            return;
        }
        int listSize = customers.getCustomerList().size();
        int fromIndex=0;
        int toIndex;
        if(listSize > Define.PAGING){
            while (true){
                toIndex = indexRangeCheck(listSize, fromIndex) ? fromIndex + Define.PAGING -1 : listSize-1;
                System.out.println("======================================");
                System.out.printf("List of Customers %d - %d / %d\n",fromIndex+1,toIndex+1 > listSize ? listSize : toIndex +1, listSize);
                System.out.print(customerList.subList(fromIndex, toIndex).toString());
                fromIndex = toIndex + 1;
                if(fromIndex >= listSize){
                    break;
                }
                if(!isAnswerYes("Continue To See Customer list, Type 'Y'")){
                    break;
                }
            }
        }else{
            System.out.printf("List of Customers %d - %d\n",1,listSize);
            System.out.println("======================================");
            System.out.print(customerList.toString());
        }

        System.out.println("======================================");
    }
}
