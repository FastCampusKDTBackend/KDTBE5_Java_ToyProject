package com.smartstore.util;

import com.smartstore.customer.Customer;

import java.util.Arrays;

public interface MergeSort {

    //default -> ascending
    default Customer[] mergeSort(Customer[] arr){
        return mergeSort(arr, true);
    }


    default Customer[] mergeSort(Customer[] arr, boolean ascending){
        //Already Sorted
        if(arr == null || arr.length <= 1){
            return arr;
        }
        return mergeSort(arr, 0, arr.length -1, ascending);
    }

    default Customer[] mergeSort(Customer[] arr, int left, int right, boolean ascending){
        if (left <right){
            int middle = (left + right) / 2;
            Customer[] leftArr = mergeSort(Arrays.copyOfRange(arr, left, middle + 1), ascending);
            Customer[] rightArr = mergeSort(Arrays.copyOfRange(arr, middle + 1, right + 1), ascending);
            return mergeSort(leftArr, rightArr, ascending);
        }
        return Arrays.copyOfRange(arr, left, right+1);
    }

    Customer[] mergeSort(Customer[] leftArr, Customer[] rightArr, boolean ascending);

}
