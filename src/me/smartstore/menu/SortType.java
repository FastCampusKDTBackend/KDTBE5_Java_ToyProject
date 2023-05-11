package me.smartstore.menu;

public enum SortType {
    BYNAME, BYTIME, BYPAY;

    String sortType = "";

    SortType(String sortType) {
        this.sortType = sortType;
    }

    SortType() {}
}
