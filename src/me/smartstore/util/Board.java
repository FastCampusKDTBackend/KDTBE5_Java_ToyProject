package me.smartstore.util;

public enum Board {
    /*
    * @parameter String[] 생성하는 과정에서 오류 발생
    * 왜 new 키워드를 사용해서 만들어야 하는지
    * */
    MAIN_MENU(new String[] {
        "Parameter",
        "Customer Data",
        "Classification Summary",
        "Quit"
    }),
    GROUP_MENU(new String[] {
        "Set Parameter",
        "View Parameter",
        "Update Parameter",
        "Back"

    }),

    CUSTOMER_MENU(new String[] {
        "Add Customer",
        "View Customer",
        "Update Customer",
        "Delete Customer",
        "Back"
    }),

    SUMMARY_MENU(new String[] {
        "Summary",
        "Summary (Sorted By Name)",
        "Summary (Sorted By Time)",
        "Summary (Sorted By Pay)",
        "Back"
    }),

    PARAMETER_MENU(new String[] {
        "Minimum Spent Time",
        "Minimum Total Pay",
        "Back"
    }),

    CUSTOMER_ADD_MENU(new String[] {
        "Customer Name",
        "Customer ID",
        "Customer Spent Time",
        "Customer Total Pay",
        "Back"
    })

    ;

    private final String[] board;

    Board(String[] board) {
        this.board = board;
    }

    public String[] getBoard() {
        return board;
    }
}
