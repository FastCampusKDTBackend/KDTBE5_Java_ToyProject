package view.input;

import domain.group.GroupType;
import util.Console;
import view.Message;

public class GroupInput extends Input{

    public static final String INPUT_GROUP = "\nWhich group (GENERAL (G), VIP (V), VVIP (VV))?";
    public static final String INPUT_MINIMUM_SPENT_TIME = "Input Minimum Spent Time: ";
    public static final String INPUT_MINIMUM_TOTAL_PAYMENT = "Input Minimum Total Payment: ";

    public static GroupType chooseGroup(){
        while (true) {
            try {
                System.out.println(INPUT_GROUP);
                String group = Console.readLineEnd();
                GroupType choiceGroup = GroupType.valueOf(group);
                return choiceGroup.replaceFullName();
            } catch (IllegalArgumentException exception) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    public static int inputMinSpentTime() {
        int minimumSpentTime = inputValueToInteger(INPUT_MINIMUM_SPENT_TIME);
        validateNegativeNumber(minimumSpentTime);
        return minimumSpentTime;
    }

    public static int inputMinTotalPayment() {
        int minTotalPayment = inputValueToInteger(INPUT_MINIMUM_TOTAL_PAYMENT);
        validateNegativeNumber(minTotalPayment);
        return minTotalPayment;
    }
}
