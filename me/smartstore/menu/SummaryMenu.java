package me.smartstore.menu;

public class SummaryMenu implements Menu {
    private static SummaryMenu summaryMenu;

    public static SummaryMenu getInstance(){
        if(summaryMenu == null){
            summaryMenu = new SummaryMenu();
        }
        return summaryMenu;
    }
    private SummaryMenu(){}

    @Override
    public void manage() {
        while (true) { //서브 메뉴 페이지를 유지하기 위한 while
            int choice = chooseMenu(new String[]{
                    "Summary",
                    "Summary (Sorted By Name)",
                    "Summary (Sorted By Spent Time)",
                    "Summary (Sorted By Total Payment)",
                    "Back"
            });
            if(choice == 1) {}//summary();
            else if(choice == 2) {}//sortedByName();
            else if(choice == 3) {}//sortedBySpentTime();
            else if(choice == 4) {}//sortedByTotalPayment();
            else break;
        }

    }
}