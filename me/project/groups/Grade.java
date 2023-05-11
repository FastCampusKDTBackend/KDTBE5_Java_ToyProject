package me.project.groups;

public enum Grade {
    NONE("NONE"),GENERAL("GENERAL"),VIP("VIP"),VVIP("VVIP");
    private String gradeName = null;

    Grade(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }
}