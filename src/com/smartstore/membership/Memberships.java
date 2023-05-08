package com.smartstore.membership;

import com.smartstore.util.CustomList;

public class Memberships {
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
    private Memberships(){

    }

}
