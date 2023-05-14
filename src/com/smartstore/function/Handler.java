package com.smartstore.function;

public interface Handler extends Readable, Handleable {
    boolean handleChoice(String menuNumber);
    int getCurrentMenuNumber();

}
