package me.smartstore.domain.menu;

public class GroupMenu implements Menu {
    private static GroupMenu groupMenu;

    public static GroupMenu getInstance() {
        if (groupMenu == null) {
            groupMenu = new GroupMenu();
        }
        return groupMenu;
    }

    private GroupMenu() {}

    @Override
    public void manage() {
        System.out.println("그룹 메뉴입니다.");
    }
}
