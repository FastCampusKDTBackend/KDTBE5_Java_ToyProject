package me.smartstore.customer;

import me.smartstore.arrays.DArray;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;
import java.util.Arrays;

public class Customers extends DArray<Customer> {
    private static Customers allCustomers;

    public static Customers getInstance() {
        if (allCustomers == null){
            allCustomers = new Customers();
        }
        return allCustomers;
    }

    private Customers() {}

    // refresh 함수가 호출되는 경우
    // 1. 분류기준 바뀔 때
    // 2. 새로운 고객이 들어올 때
    public void refresh(Groups groups) {
        int cusMinTime;
        int cusMinPay;

        int VVIPMinTime = 0, VVIPMinPay = 0,
            VIPMinTime = 0, VIPMinPay = 0,
            GENERALMinTime = 0, GENERALMInPay = 0;

        int cnt = 0;

        if (groups.findGroupByGroupType(GroupType.VVIP) != null) {
            VVIPMinTime = groups.findGroupByGroupType(GroupType.VVIP).getParameter().getMinTime();
            VVIPMinPay = groups.findGroupByGroupType(GroupType.VVIP).getParameter().getMinPay();
            cnt += 4;
        }
        if (groups.findGroupByGroupType(GroupType.VIP) != null) {
            VIPMinTime = groups.findGroupByGroupType(GroupType.VIP).getParameter().getMinTime();
            VIPMinPay = groups.findGroupByGroupType(GroupType.VIP).getParameter().getMinPay();
            cnt += 2;
        }
        if (groups.findGroupByGroupType(GroupType.GENERAL) != null) {
            GENERALMinTime = groups.findGroupByGroupType(GroupType.GENERAL).getParameter().getMinTime();
            GENERALMInPay = groups.findGroupByGroupType(GroupType.GENERAL).getParameter().getMinPay();
            cnt += 1;
        }


        for (int i = 0; i < allCustomers.size; i++) {
            cusMinTime = allCustomers.get(i).getCusTotalTime();
            cusMinPay = allCustomers.get(i).getCusTotalPay();

            if( cnt == 7 ) {
                if (cusMinTime >= VVIPMinTime && cusMinPay >= VVIPMinPay)
                    allCustomers.get(i).setGroup(GroupType.VVIP);
                else if (cusMinTime >= VIPMinTime && cusMinPay >= VIPMinPay)
                    allCustomers.get(i).setGroup(GroupType.VIP);
                else if (cusMinTime >= GENERALMinTime && cusMinPay >= GENERALMInPay)
                    allCustomers.get(i).setGroup(GroupType.GENERAL);
                else allCustomers.get(i).setGroup(GroupType.NONE);
            } else if ( cnt == 6 ){
                if (cusMinTime >= VVIPMinTime && cusMinPay >= VVIPMinPay)
                    allCustomers.get(i).setGroup(GroupType.VVIP);
                else if (cusMinTime >= VIPMinTime && cusMinPay >= VIPMinPay)
                    allCustomers.get(i).setGroup(GroupType.VIP);
                else allCustomers.get(i).setGroup(GroupType.NONE);
            } else if ( cnt == 5 ){
                if (cusMinTime >= VVIPMinTime && cusMinPay >= VVIPMinPay)
                    allCustomers.get(i).setGroup(GroupType.VVIP);
                else if (cusMinTime >= GENERALMinTime && cusMinPay >= GENERALMInPay)
                    allCustomers.get(i).setGroup(GroupType.GENERAL);
                else allCustomers.get(i).setGroup(GroupType.NONE);
            } else if ( cnt == 4 ){
                if (cusMinTime >= VVIPMinTime && cusMinPay >= VVIPMinPay)
                    allCustomers.get(i).setGroup(GroupType.VVIP);
                else allCustomers.get(i).setGroup(GroupType.NONE);
            } else if ( cnt == 3 ){
                if (cusMinTime >= VIPMinTime && cusMinPay >= VIPMinPay)
                    allCustomers.get(i).setGroup(GroupType.VIP);
                else if (cusMinTime >= GENERALMinTime && cusMinPay >= GENERALMInPay)
                    allCustomers.get(i).setGroup(GroupType.GENERAL);
                else allCustomers.get(i).setGroup(GroupType.NONE);
            } else if ( cnt == 2 ){
                if (cusMinTime >= VIPMinTime && cusMinPay >= VIPMinPay)
                    allCustomers.get(i).setGroup(GroupType.VIP);
                else allCustomers.get(i).setGroup(GroupType.NONE);
            } else if ( cnt == 1 ){
                if (cusMinTime >= GENERALMinTime && cusMinPay >= GENERALMInPay)
                    allCustomers.get(i).setGroup(GroupType.GENERAL);
                else allCustomers.get(i).setGroup(GroupType.NONE);
            }
        }
        System.out.println(allCustomers);
    }

    public int findCustomerDataByCusNo(int customerIdx){

        int idx = -1;
        for (int i = 0; i < allCustomers.size(); i++) {
            if(allCustomers.get(i).getCusNo() == customerIdx){
                idx = allCustomers.indexOf(allCustomers.get(i));
            }
        }
        return idx;
    }

    public void viewCustomerData(){
        int num = 1;
        for (int i = 0; i < allCustomers.size; i++){
            System.out.println(allCustomers.get(i));
            num++;
        }
    }

    public void viewCustomerDataByGroupType(GroupType groupType) {
        int num = 1;
        for (int i = 0; i < allCustomers.size; i++){
            if( allCustomers.get(i).getGroup() == groupType) {
                System.out.println(allCustomers.get(i));
                num++;
            }
        }
    }

    public void viewCustomerDataByGroupType(GroupType groupType, String sortingMethod, String order) {
        int num = 1;
        Customer[] customers = new Customer[allCustomers.size];
        for (int i = 0; i < allCustomers.size; i++) {
            customers[i] = allCustomers.get(i);
        }

        Arrays.sort(customers, (o1, o2) -> {
            if (sortingMethod.equals("Name")){
                if (order.equals("A"))
                    return o1.getCusName().compareTo(o2.getCusName());
                else if (order.equals("D"))
                    return o2.getCusName().compareTo(o1.getCusName());
            } else if (sortingMethod.equals("SpentTime")) {
                if (order.equals("A"))
                    return o1.getCusTotalTime() - o2.getCusTotalTime();
                else if (order.equals("D"))
                    return o2.getCusTotalTime() - o1.getCusTotalTime();
            } else if (sortingMethod.equals("TotalPayment")) {
                if (order.equals("A"))
                    return o1.getCusTotalPay() - o2.getCusTotalPay();
                else if (order.equals("D"))
                    return o2.getCusTotalPay() - o1.getCusTotalPay();
            }
            return 0;
        });

        for (int i = 0; i < allCustomers.size; i++) {
            if (allCustomers.get(i).getGroup() == groupType) {
                System.out.println(customers[i]);
                num++;
            }
        }
    }

}
