package me.smartstore.group;

import java.util.Optional;

public class Groups {

    private static Groups instance;
    private Optional<GroupGeneral> groupGeneral;// 이렇게 optional를 사용하는게 맞는지는 모르겠지만 진행
    private Optional<GroupVIP> groupVIP;
    private Optional<GroupVVIP> groupVVIP;

    private Groups(){
        groupGeneral = Optional.empty();
        groupVIP = Optional.empty();
        groupVVIP = Optional.empty();

    }

    public static Groups getInstance(){
        if(instance == null){
            instance = new Groups();
        }
        return instance;
    }


}
