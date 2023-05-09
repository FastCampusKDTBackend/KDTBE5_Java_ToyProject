package me.smartstore.menu;

public enum SortType {
    ASCENDING("오름차순"),
    DESCENDING("내림차순"),
    A("오름차순"),
    D("내림차순");

    String sortType = "";

    SortType(String sortType) {
        this.sortType = sortType;
    }

    public SortType replaceFullName() {
        if (this == A)
            return ASCENDING;
        if (this == D)
            return DESCENDING;
        return this;
    }
}