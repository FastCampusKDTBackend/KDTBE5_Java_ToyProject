package Menu;

public class ParameterMenu implements Menu{

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

    }
}
