package com.smartstore.function;

public interface Handler extends Readable, Handleable {
    boolean handleChoice(String menuNumber);
    void run(int menu);
    int getCurrentMenuNumber();

}
