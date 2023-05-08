package me.smartstore.menu.topic;

public class ClassificationMenu extends TopicIntroMenu {

    private static final String SUMMARY_MENU_OUTPUT = null;
    private static final ClassificationMenu INSTANCE = new ClassificationMenu();

    private ClassificationMenu() { super(SUMMARY_MENU_OUTPUT); }

    public static ClassificationMenu getInstance() { return INSTANCE; }

    @Override
    protected void setNextMenus() {
        setNextMenus();
    }
}
