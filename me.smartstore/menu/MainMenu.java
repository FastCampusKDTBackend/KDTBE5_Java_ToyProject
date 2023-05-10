package menu;

public class MainMenu implements Menu {

    private final CustomerMenu customerMenu = CustomerMenu.getInstance();
    private final GroupMenu groupMenu = GroupMenu.getInstance();
    private final SummaryMenu summaryMenu = SummaryMenu.getInstance();

    // singleton
    private static MainMenu mainMenu;

    public static MainMenu getInstance() {
        if (mainMenu == null) {
            mainMenu = new MainMenu();
        }
        return mainMenu;
    }

    private MainMenu() {}

    @Override
    public void manage() {
        while ( true ) { // 프로그램 실행 while
            int choice = mainMenu.chooseMenu(new String[] {
                    "Parameter",
                    "Customer",
                    "Classification Summary",
                    "Quit"});

            if (choice == 1) {
                editParameter();
            }
            else if (choice == 2) {
                customerMenu.manage();
            }
            else if (choice == 3) {
                summaryMenu.manage();
            }
            else { // choice == 4
                System.out.println("Program Finished");
                // 데이터 저장 코드 추가
                // saveData()는 데이터를 파일에 저장하는 메서드로 가정
                saveData();
                break;
            }
        }
    }

    public void editParameter() {
        while ( true ) {
            int choice = mainMenu.chooseMenu(new String[]{
                    "Set Parameter",
                    "View Parameter",
                    "Update Parameter",
                    "Back"});

            if (choice == 1) {
                groupMenu.setParameter();
            }
            else if (choice == 2) {
                // 파라미터를 보여주는 메서드 호출
            }
            else if (choice == 3) {
                // 파라미터를 수정하는 메서드 호출
            }
            else { // choice == 4
                break;
            }
        }
    }

    public void saveData() {
        // 데이터를 파일에 저장하는 코드
        System.out.println("Data Saved");
    }
}
