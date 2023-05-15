package com.smartstore.function.sorting;

import com.smartstore.customer.Customer;
import com.smartstore.customer.Customers;
import com.smartstore.membership.MembershipType;
import com.smartstore.util.*;

import java.util.Arrays;

public interface SortHandler extends EnumValueProvider, Handleable, MergeSort {
    @Override
    default void run() {
        CustomList<Customer> customerList = Customers.getInstance().getCustomerList();
        if(customerList.size() == 0){
            System.out.println("There is No Data Yet");
            return;
        }
        Customer[] customerArr = customerList.toArray(new Customer[customerList.size()]);
        for(MembershipType membershipType : MembershipType.values()){
            System.out.println(membershipType.toString());
            System.out.println("==========================");
            System.out.println(Arrays.toString(mergeSort(SortByMembership.getInstance().getSortedCustomersMap().get(membershipType))));
        }


    }

    @Override
    default Customer[] mergeSort(Customer[] leftArr, Customer[] rightArr, boolean ascending) {
        int leftLen = leftArr.length;
        int rightLen = rightArr.length;
        int i = 0, j = 0, k = 0;
        Customer[] mergedArr = new Customer[leftLen + rightLen];

        while (i < leftLen && j < rightLen) {
            if ((ascending && leftArr[i].getMembership().compareTo(rightArr[j].getMembership()) <= 0) ||
                    (!ascending && leftArr[i].getMembership().compareTo(rightArr[j].getMembership()) >= 0)) {
                mergedArr[k] = leftArr[i];
                i++;
            } else {
                mergedArr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < leftLen) {
            mergedArr[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < rightLen) {
            mergedArr[k] = rightArr[j];
            j++;
            k++;
        }

        return mergedArr;
    }
}