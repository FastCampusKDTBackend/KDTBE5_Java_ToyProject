package com.smartstore.membership;

import com.smartstore.menu.Menu;
import com.smartstore.util.CustomList;

public class Memberships implements Menu {
    private CustomList<Membership> membershipList = new CustomList<>();
    private static Memberships instance;

    public static Memberships getInstance(){
        if(instance == null){
            instance = new Memberships();
        }
        return instance;
    }

    public void refresh(){

    }
    Memberships(){

    }

    @Override
    public void handleChoice(int menuNumber) {

    }

    @Override
    public void run() {

    }
}
