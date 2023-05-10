package domain.group;

public class Parameter {
    private int minimumSpentTime;
    private int minimumTotalPay;

    public Parameter() {
        minimumSpentTime = 0;
        minimumTotalPay = 0;
    }

    public Parameter(int minimumSpentTime, int minimumTotalPay) {
        this.minimumSpentTime = minimumSpentTime;
        this.minimumTotalPay = minimumTotalPay;
    }

    public int getMinimumSpentTime() {
        return minimumSpentTime;
    }

    public int getMinimumTotalPay() {
        return minimumTotalPay;
    }

    public void setMinimumSpentTime(int minimumSpentTime) {
        this.minimumSpentTime = minimumSpentTime;
    }

    public void setMinimumTotalPay(int minimumTotalPay) {
        this.minimumTotalPay = minimumTotalPay;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Parameter{minimumSpentTime=")
                .append(minimumSpentTime)
                .append(", minimumTotalPay=")
                .append(minimumTotalPay)
                .append('}').toString();
    }
}
