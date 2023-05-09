package smartstore.menu;

public enum SortType {
	ASCENDING("Ascending"), DESCENDING("Descending"),
    A("Ascending"), D("Descending");

    String sortType = "";

    SortType(String sortType) {
        this.sortType = sortType;
    }

    public SortType replaceFullName() {
        if (this == A) return ASCENDING;
        else if (this == D) return DESCENDING;
        return this;
    }
}
