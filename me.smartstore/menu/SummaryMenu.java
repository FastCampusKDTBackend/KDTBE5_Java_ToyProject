package menu;

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
            switch (choice) {
                case 1:
                    // SummaryMenu에서 제공하는 "Summary" 기능 실행
                    break;
                case 2:
                    // SummaryMenu에서 제공하는 "Summary (Sorted By Name)" 기능 실행
                    break;
                case 3:
                    // SummaryMenu에서 제공하는 "Summary (Sorted By Time)" 기능 실행
                    break;
                case 4:
                    // SummaryMenu에서 제공하는 "Summary (Sorted By Pay)" 기능 실행
                    break;
                case 5:
                    // 이전 메뉴로 돌아가기
                    return;
            }
        }
    }
}