package smartstore1;

import java.util.ArrayList;
import java.util.Comparator;

public class MemberSorter {

    public static void sortByUseHour(ArrayList<Member> memberList) {
        Comparator<Member> comparator = new Comparator<Member>() {
            public int compare(Member m1, Member m2) {
                return Integer.compare(m1.getUseHour(), m2.getUseHour());
            }
        };
        memberList.sort(comparator);
    }

    public static void sortByUseMoney(ArrayList<Member> memberList) {
        Comparator<Member> comparator = new Comparator<Member>() {
            public int compare(Member m1, Member m2) {
                return Integer.compare(m1.getUseMoney(), m2.getUseMoney());
            }
        };
        memberList.sort(comparator);
    }

    public static void sortByName(ArrayList<Member> memberList) {
        Comparator<Member> comparator = new Comparator<Member>() {
            public int compare(Member m1, Member m2) {
                return m1.getName().compareTo(m2.getName());
            }
        };
        memberList.sort(comparator);
    }
}
