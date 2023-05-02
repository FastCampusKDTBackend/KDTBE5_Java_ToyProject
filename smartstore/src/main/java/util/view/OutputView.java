package util.view;

public class OutputView {
    public void copyRight() {
        System.out.println(
                new StringBuilder()
                        .append("===========================================\n")
                        .append(" Title : SmartStore Customer Segmentation\n")
                        .append(" Release Date : 23.05.10\n")
                        .append(" Copyright 2023 TaeyunChoi All rights reserved.\n")
                        .append("===========================================\n\n\n")
        );

    }

    public void choiceMenu() {
        System.out.println("==============================");
        System.out.println(" 1. Parameter");
        System.out.println(" 2. Customer Data");
        System.out.println(" 3. Classification Summary");
        System.out.println(" 4. Quit");
        System.out.println("==============================");
    }

    public void choiceParameterMenu() {
        System.out.println(
                new StringBuilder()
                        .append("==============================\n")
                        .append(" 1. Set Parameter\n")
                        .append(" 2. View Parameter\n")
                        .append(" 3. Update Parameter\n")
                        .append(" 4. Back\n")
                        .append("==============================\n")
        );
    }

    public void choiceCustomerDataMenu() {
        System.out.println(
                new StringBuilder()
                        .append("==============================\n")
                        .append(" 1. Add Customer Data\n")
                        .append(" 2. View Customer Data\n")
                        .append(" 3. Update Customer Data\n")
                        .append(" 4. Delete Customer Data\n")
                        .append(" 5. Back\n")
                        .append("==============================\n")
        );
    }

    public void choiceGroup() {
        System.out.println(
                new StringBuilder()
                        // Group으로 이동
                        .append("Which group (GENERAL (G), VIP (V), VVIP (VV))?  \n")
                        .append("** Press 'end', if you want to exit! **\n")
        );
    }

    public void alreadyExistParameter() {
        System.out.println(
                new StringBuilder()
                        .append("GENERAL group already exists.\n")
                        .append("\n")

                        // (View Parameter) 같은 코드 -> 재활용
                        // (Update Parameter) 
                        .append("GroupType: GENERAL\n")
                        // 그룹 받아서 해당 그룹의 Parameter 출력
                        .append("Parameter: Parameter{minimumSpentTime=10, minimumTotalPay=100000}\n")
        );

    }


    public void chooseOne() {
        System.out.println("Choose One: ");
    }

    public void setParameter() {
        System.out.println(
                new StringBuilder()
                        // updateParameter -> 재활용
                        .append("==============================\n")
                        .append(" 1. Minimum Spent Time\n")
                        .append(" 2. Minimum Total Pay\n")
                        .append(" 3. Back\n")
                        .append("==============================\n")
                        .append("Choose One: ")
        );
    }

    public void inputParameter() {
        System.out.println(
                new StringBuilder()
                        .append("Input Minimum Spent Time:\n")
                        .append("** Press 'end', if you want to exit! **")
        );

    }
}
