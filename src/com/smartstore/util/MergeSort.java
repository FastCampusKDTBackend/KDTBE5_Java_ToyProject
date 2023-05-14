package com.smartstore.util;

import com.smartstore.customer.Customer;

import java.util.Arrays;

public class MergeSort {

    //default -> ascending
    public static Customer[] mergeSort(Customer[] arr){
        return mergeSort(arr, true);
    }


    public static Customer[] mergeSort(Customer[] arr, boolean ascending){
        //Already Sorted
        if(arr == null || arr.length <= 1){
            return arr;
        }
        return mergeSort(arr, 0, arr.length -1, ascending);
    }

    private static Customer[] mergeSort(Customer[] arr, int left, int right, boolean ascending){
        if (left <right){
            int middle = (left + right) / 2;
            Customer[] leftArr = mergeSort(Arrays.copyOfRange(arr, left, middle + 1), ascending);
            Customer[] rightArr = mergeSort(Arrays.copyOfRange(arr, middle + 1, right + 1), ascending);
            return run(leftArr, rightArr, ascending);
        }
        return Arrays.copyOfRange(arr, left, right+1);
    }

    private static Customer[] run(Customer[] leftArr, Customer[] rightArr, boolean ascending) {
        int leftLen = leftArr.length;
        int rightLen = rightArr.length;
        int i = 0, j = 0, k = 0;
        Customer[] mergedArr = new Customer[leftLen + rightLen];

        while (i < leftLen && j < rightLen) {
            if ((ascending && leftArr[i].getCustomerName().compareTo(rightArr[j].getCustomerName()) <= 0) ||
                    (!ascending && leftArr[i].getCustomerName().compareTo(rightArr[j].getCustomerName()) >= 0)) {
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
