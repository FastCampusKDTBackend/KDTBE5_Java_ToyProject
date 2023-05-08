package smartstore.group;

import smartstore.arrays.DArray;

public class Groups extends DArray<Group> {
    // singleton
    private static Groups allGroups;

    public static Groups getInstance() {
        if (allGroups == null) {
            allGroups = new Groups();
            
            // 최초 등급 생성
            allGroups.add(new Group(new Parameter(), GroupType.NONE));
//            allGroups.add(new Group(new Parameter(), GroupType.GENERAL));
//            allGroups.add(new Group(new Parameter(), GroupType.VIP));
//            allGroups.add(new Group(new Parameter(), GroupType.VVIP));
        }
        
        return allGroups;
    }

    private Groups() {}
    
    public Group find(GroupType groupType) {
        for (int i = 0; i < this.size; i++) {
        	if (this.get(i).getGroupType() == groupType) { // enum의 비교는 equals가 아님
        		return this.get(i);
            }
        }
        return null;
    }
    
    
}
