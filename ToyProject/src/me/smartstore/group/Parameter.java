package me.smartstore.group;

import java.util.Objects;

/**
 * 고객 분류기준
 * 등급 분류를 위해서 minTime, minPay 최소시간과 최소금액을 설정함
 */
public class Parameter {
    private Integer minTime;
    private Integer minPay;

    public Parameter() {
    }

    public Parameter(Integer minTime, Integer minPay) {
        this.minTime = minTime;
        this.minPay = minPay;
    }

    public Integer getMinTime() {
        return minTime;
    }

    public void setMinTime(Integer minTime) {
        this.minTime = minTime;
    }

    public Integer getMinPay() {
        return minPay;
    }

    public void setMinPay(Integer minPay) {
        this.minPay = minPay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameter parameter = (Parameter) o;
        return Objects.equals(minTime, parameter.minTime) && Objects.equals(minPay, parameter.minPay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minTime, minPay);
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "minTime=" + minTime +
                ", minPay=" + minPay +
                '}';
    }
}
