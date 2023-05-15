package com.smartstore.util;

public interface Handler extends Readable, Handleable {
    boolean handleChoice(String menuNumber);
    int getCurrentMenuNumber();

}
