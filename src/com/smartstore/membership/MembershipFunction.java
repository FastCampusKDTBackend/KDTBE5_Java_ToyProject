package com.smartstore.membership;

import com.smartstore.menu.Back;
import com.smartstore.menu.Menu;
import com.smartstore.util.Function;

public enum MembershipFunction implements Function {
    SET(1, new SetMembershipRequirement()),
    VIEW(2, new ViewMembershipRequirement()),
    UPDATE(3,new UpdateMembershipRequirement()),
    BACK(4, new Back());


    private final int menuNumber;
    private final Menu menuController;
    MembershipFunction(int menuNumber, Menu menuController) {
        this.menuNumber = menuNumber;
        this.menuController = menuController;
    }

    @Override
    public void run() {
        if(menuController.getClass().equals(Back.class)){
            //((Back) menuController).isExit();
        }else{
            menuController.run();
        }
    }

    @Override
    public boolean isMatchedMenuNumber(int menuNumber){
        return this.menuNumber == menuNumber;
    }
}
