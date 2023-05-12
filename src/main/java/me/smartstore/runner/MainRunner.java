package me.smartstore.runner;

import me.smartstore.menu.CustomerMenu;
import me.smartstore.menu.GroupMenu;
import me.smartstore.menu.Menu;
import me.smartstore.menu.SummaryMenu;

import java.io.IOException;

public class MainRunner {
    private static MainRunner mainRunner;
    private Menu menu;
    private GroupMenu groupMenu;
    private CustomerMenu customerMenu;
    private SummaryMenu summaryMenu;

    //runner -> GroupMenuRunner, SummaryMenuRunner, CustomerMenuRunner 로 분리
    private MainRunner() {
        this.menu = Menu.getInstance();
        this.groupMenu = GroupMenu.getInstance();
        this.summaryMenu = SummaryMenu.getInstance();
        this.customerMenu = CustomerMenu.getInstance();
    }

    public static MainRunner getInstance() {
        if (mainRunner == null) {
            mainRunner = new MainRunner();
        }
        return mainRunner;
    }

    public void run() {
        int initMenu = menu.inputInitMenuNumber();
        if(initMenu==1){
            //groupMenu
            groupMenu.run();
        } else if (initMenu==2) {
            //customerMenu

        } else if (initMenu==3) {
            //summaryMenu
            summaryMenu.run();
        }
        return;
    }

}
