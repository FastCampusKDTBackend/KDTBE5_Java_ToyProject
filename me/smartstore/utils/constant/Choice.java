package me.smartstore.utils.constant;

public enum Choice {
    MAIN_MENU(new String[] {
            "Parameter",
            "Customer",
            "Classification Summary",
            "Quit"
    }),
    GROUP_MENU(new String[]{
            "Set Parameter",
            "View Parameter",
            "Update Parameter",
            "Back"
    }),
    GROUP_MENU_SET_PARAMS(new String[] {
            "Minimum Spent Time",
            "Minimum Total Pay",
            "Back"
    }),
    CUSTOMER_MENU(new String[]{
            "Add Customer Data",
            "View Customer Data",
            "Update Customer Data",
            "Delete Customer Data",
            "Back"
    }),
    CUSTOMER_MENU_SET(new String[] {
            "Customer Name",
            "Customer ID",
            "Customer Spent Time",
            "Customer Total Pay",
            "Save"
    }),
    SUMMARY_MENU(new String[]{
            "Summary",
            "Summary (Sorted By Name)",
            "Summary (Sorted By Spent Time)",
            "Summary (Sorted By Total Payment)",
            "Back"
    });

    private final String[] choices;

    Choice(String[] choices) {
        this.choices = choices;
    }

    public String[] getChoices() {
        return this.choices;
    }
}
