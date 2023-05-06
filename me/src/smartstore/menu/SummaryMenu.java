package smartstore.menu;

public class SummaryMenu implements Menu {
    // singleton
    private static SummaryMenu summaryMenu;

    public static SummaryMenu getInstance() {
        if (summaryMenu == null) {
            summaryMenu = new SummaryMenu();
        }
        return summaryMenu;
    }

    private SummaryMenu() {}

    @Override
    public void manage() {
        while ( true ) { // 서브 메뉴 페이지를 유지하기 위한 while
            int choice = chooseMenu(new String[]{
                    "Summary",
                    "Summary (Sorted By Name)",
                    "Summary (Sorted By Time)",
                    "Summary (Sorted By Pay)",
                    "Back"});
            
            if (choice == 1) summary();
            else if (choice == 2) summary(Sorted By Name)();
            else if (choice == 3) summary(Sorted By Time)();
            else if (choice == 4) summary(Sorted By Pay)();
            else break; // choice == 4
        }
    }
    
    public void summary() {
    	
    }
    
    // Summary (Sorted By Name)
    public void summary(String name) {
    	
    }
    
    // Summary (Sorted By Time)
    public void summary() {
    	
    }
    
 // Summary (Sorted By Pay)
    public void summary() {
    	
    }
}
