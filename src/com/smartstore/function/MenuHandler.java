package com.smartstore.function;

public interface MenuHandler extends Readable{
    boolean handleChoice(String menuNumber);
    void run();
    void run(int menu);
    int getCurrentMenuNumber();

}
