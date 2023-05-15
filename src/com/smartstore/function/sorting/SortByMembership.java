package com.smartstore.function.sorting;

import com.smartstore.customer.Customer;
import com.smartstore.customer.Customers;
import com.smartstore.membership.MembershipType;
import com.smartstore.util.CustomEnumMap;
import com.smartstore.util.CustomList;
import com.smartstore.util.MergeSort;

import java.util.Arrays;

public class SortByMembership implements SortHandler, MergeSort {
    private static SortByMembership instance;

    private SortByMembership(){

    }

    public static SortByMembership getInstance(){
        if(instance == null){
            return new SortByMembership();
        }
        return instance;
    }
    private CustomEnumMap<MembershipType, Customer[]> sortedCustomerMap = new CustomEnumMap<>(MembershipType.class);

    public CustomEnumMap<MembershipType, Customer[]> getSortedCustomersMap() {
        updateSortedData();
        return sortedCustomerMap;
    }

    public void updateSortedData() {
        CustomList<Customer> customerList = Customers.getInstance().getCustomerList();
        if(customerList.size() == 0){
            return;
        }
        Customer[] customersSortByMembership = mergeSort(customerList.toArray(new Customer[customerList.size()]));
        int index = 0;
        for(MembershipType membershipType : MembershipType.values()){
            for(int j = 0 ; j < customersSortByMembership.length ; j++){
                if(customersSortByMembership[j].getMembership() != membershipType){
                    index = j;
                    break;
                }
                //copy and add to list of Customers Group by membership
            }
            if(index == 0){
                sortedCustomerMap.put(membershipType, customersSortByMembership);
                break;
            }
            sortedCustomerMap.put(membershipType, Arrays.copyOfRange(customersSortByMembership,0, index-1));
            //resize sorted Arr
            customersSortByMembership = Arrays.copyOfRange(customersSortByMembership, index, customersSortByMembership.length-1);
            index=0;
        }

    }
}
