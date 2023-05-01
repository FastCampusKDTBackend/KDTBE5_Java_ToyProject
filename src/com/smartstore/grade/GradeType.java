package com.smartstore.grade;

public enum GradeType {
    GENERAL(new String[] {"G", "GENERAL", "일반"}),
    VIP(new String[] {"V", "VIP", "우수"}),
    VVIP(new String[] {"VV", "VVIP", "최우수"});

    String[] grade = new String[3];
    GradeType(String[] grade) {
        this.grade = grade;
    }
}
