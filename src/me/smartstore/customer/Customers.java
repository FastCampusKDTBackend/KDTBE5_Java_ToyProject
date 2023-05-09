package me.smartstore.customer;

import me.smartstore.arrays.DArray;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;

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

        if (groups.find(GroupType.VVIP) != null) {
            VVIPMinTime = groups.find(GroupType.VVIP).getParameter().getMinTime();
            VVIPMinPay = groups.find(GroupType.VVIP).getParameter().getMinPay();
            cnt += 4;
        }
        if (groups.find(GroupType.VIP) != null) {
            VIPMinTime = groups.find(GroupType.VIP).getParameter().getMinTime();
            VIPMinPay = groups.find(GroupType.VIP).getParameter().getMinPay();
            cnt += 2;
        }
        if (groups.find(GroupType.GENERAL) != null) {
            GENERALMinTime = groups.find(GroupType.GENERAL).getParameter().getMinTime();
            GENERALMInPay = groups.find(GroupType.GENERAL).getParameter().getMinPay();
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

    public void viewCustomerData(){
        int num = 1;
        for (int i = 0; i < allCustomers.size; i++){
            System.out.println("No. " + num + " => "+ allCustomers.get(i));
            num++;
        }
    }

    public void viewCustomerData(GroupType groupType) {
        int num = 1;
        for (int i = 0; i < allCustomers.size; i++){
            if( allCustomers.get(i).getGroup() == groupType) {
                System.out.println("No. " + num + " => " + allCustomers.get(i));
                num++;
            }
        }
    }


}
