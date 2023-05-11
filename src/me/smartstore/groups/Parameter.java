package me.smartstore.groups;

import java.util.Objects;

public class Parameter {
    private Integer minTime;
    private Integer minPayment;

    public Parameter() {}
    public Parameter(Integer minTime, Integer minPayment) {
        this.minTime = minTime;
        this.minPayment = minPayment;
    }

    public Integer getMinTime() {
        return minTime;
    }

    public void setMinTime(Integer minTime) {
        this.minTime = minTime;
    }

    public Integer getMinPayment() {
        return minPayment;
    }

    public void setMinPayment(Integer minPayment) {
        this.minPayment = minPayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameter parameter = (Parameter) o;
        return Objects.equals(minTime, parameter.minTime) && Objects.equals(minPayment, parameter.minPayment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minTime, minPayment);
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "minTime=" + minTime +
                ", minPayment=" + minPayment +
                '}';
    }
}
