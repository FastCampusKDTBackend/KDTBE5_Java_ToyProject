package com.smartstore.function.sorting;

import com.smartstore.customer.Customer;
import com.smartstore.customer.Customers;
import com.smartstore.util.CustomList;
import com.smartstore.util.MergeSort;

public class SortByUsageTime implements SortHandler, MergeSort {
    private static SortByUsageTime instance;

    private SortByUsageTime(){

    }

    public static SortByUsageTime getInstance(){
        if(instance == null){
            return new SortByUsageTime();
        }
        return instance;
    }

    @Override
    public Customer[] mergeSort(Customer[] leftArr, Customer[] rightArr, boolean ascending) {
        int leftLen = leftArr.length;
        int rightLen = rightArr.length;
        int i = 0, j = 0, k = 0;
        Customer[] mergedArr = new Customer[leftLen + rightLen];

        while (i < leftLen && j < rightLen) {
            if ((ascending && leftArr[i].getUsageTime() <= rightArr[j].getUsageTime()) ||
                    (!ascending && leftArr[i].getUsageTime() >= rightArr[j].getUsageTime())) {
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
