package com.smartstore.function;

public interface MenuHandler extends Readable{
    String runMenuSelectionLoop(String[] menus);
    boolean handleChoice(String menuNumber);
    void run();
    void run(int menu);
    int getCurrentMenuNumber();

}
