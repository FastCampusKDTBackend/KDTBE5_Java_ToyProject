package me.smartstore.group;

import me.smartstore.arrays.DArray;

public class Groups extends DArray<Group> {
    private static Groups allGroups;

    public static Groups getInstance(){
        if (allGroups == null){
            allGroups = new Groups();
        }
        return allGroups;
    }

    private Groups() {}

    public Group find(GroupType groupType) {
        for (int i = 0; i < allGroups.size; i++) {
            if (allGroups.get(i).getGroupType() == groupType) {
                return allGroups.get(i);
            }
        }
        return null;
    }

    public void checkGroupParameter(Group group) {
        // 전달 받은값으로 옳은 값인지 확인 후 적용
        // 이용시간, 금액 General < VIP < VVIP
        // 시간 되면 추가하기
        if (group.getParameter().getMinTime() != null
                && group.getParameter().getMinPay() != null){

        } else if (group.getParameter().getMinTime() != null
                && group.getParameter().getMinPay() == null){

        } else if (group.getParameter().getMinTime() == null
                && group.getParameter().getMinPay() != null){

        } else if (group.getParameter().getMinTime() == null
                && group.getParameter().getMinPay() == null){
            allGroups.set(allGroups.indexOf(group), group);
        }
    }




}
