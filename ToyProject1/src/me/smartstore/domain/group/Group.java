package me.smartstore.domain.group;

import java.util.Objects;
import me.smartstore.domain.customer.Customers;
import me.smartstore.constant.GroupType;

public class Group {
	private GroupType type;
	
	private Parameter param;
	
	public Group() {
		this(null, null);
	}
  
	public Group(GroupType type, Parameter param) {
		this.type = type;
		this.param = param;
	}
	
	public GroupType getType() {
		return this.type;
	}
  
	public void setType(GroupType type) {
		this.type = type;
	}

	public Parameter getParam() {
		return this.param;
	}

	public void setParam(Parameter param) {
		this.param = param;
	}

	public Customers getCustomers(Customers allCustomers) {
		return allCustomers.findCustomers(this);
	}

	public void update(GroupType type, Parameter param) {
		this.type = type;
		this.param = param;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true; 
		if (o == null || getClass() != o.getClass())
			return false; 
		Group group = (Group)o;
		return (this.type == group.type && Objects.equals(this.param, group.param));
	}
  
	public int hashCode() {
		return Objects.hash(new Object[] { this.type, this.param });
	}

	public String toString() {
		if (this.type == null)
			return "No group."; 
		return (this.param == null) ? ("GroupType: " + 
				this.type + "\nParameter: null") : ("GroupType: " + 
		this.type + "\nParameter: " + this.param);
	}
	
}
