package me.day10.smartstore.menu;

public class ClassificationMenu extends TopicIntroMenu {

    private static final String SUMMARY_MENU_OUTPUT = null;
    private static final ClassificationMenu INSTANCE = new ClassificationMenu(SUMMARY_MENU_OUTPUT);

    private ClassificationMenu(String TOPIC_OUTPUT, Menu... nextMenus) {
        super(TOPIC_OUTPUT, nextMenus);
    }

    public static ClassificationMenu getInstance() { return INSTANCE; }
}
