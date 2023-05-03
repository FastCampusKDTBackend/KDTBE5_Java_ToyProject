package Menu;

public class ParameterMenu implements Menu {

    private static ParameterMenu allParameterMenu;

    public static ParameterMenu getInstance() {
        if (allParameterMenu == null) {
            allParameterMenu = new ParameterMenu();
        }
        return allParameterMenu;
    }

    private ParameterMenu() {

    }


    @Override
    public void manage() {
        while (true) {
            int choice = allParameterMenu.chooseMenu(new String[]{
                    "Set Parameter",
                    "View Parameter",
                    "Update Parameter",
                    "Back"
            });

            if (choice == 1) setParameter();
            else if (choice == 2) viewParameter();
            else if (choice == 3) updateParameter();
            else if (choice == 4) break;
        }
    }

    private void setParameter() {
        System.out.println("setParameter");
    }

    private void viewParameter() {
        System.out.println("viewParameter");
    }

    private void updateParameter() {
        System.out.println("updateParameter");
    }
}
