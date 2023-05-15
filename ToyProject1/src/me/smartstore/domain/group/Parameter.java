package me.smartstore.domain.group;

import java.util.Objects;

public class Parameter {
	private Integer minimumSpentTime;
	private Integer minimumTotalPay;
	
	public Parameter() {}
	public Parameter(Integer minimumSpentTime, Integer minimumTotalPay) {
		this.minimumSpentTime = minimumSpentTime;
		this.minimumTotalPay = minimumTotalPay;
	}
	
	public Integer getMinimumSpentTime() {
		return this.minimumSpentTime;
	}
	
	public void setMinimumSpentTime(Integer minimumSpentTime) {
		this.minimumSpentTime = minimumSpentTime;
	}
	
	public Integer getMinimumTotalPay() {
		return this.minimumTotalPay;
	}
	
	public void setMinimumTotalPay(Integer minimumTotalPay) {
		this.minimumTotalPay = minimumTotalPay;
	}
	
	public boolean equals(Object o) {
		if (this == o)
			return true; 
		if (o == null || getClass() != o.getClass())
			return false; 
		Parameter parameter = (Parameter)o;
		return (this.minimumSpentTime == parameter.minimumSpentTime && this.minimumTotalPay == parameter.minimumTotalPay);
	}
	
	public int hashCode() {
		return Objects.hash(new Object[] { this.minimumSpentTime, this.minimumTotalPay });
	} 

	public String toString() {
		return "Parameter{minimumSpentTime=" + this.minimumSpentTime + ", minimumTotalPay=" + this.minimumTotalPay + "}";
	}

}
