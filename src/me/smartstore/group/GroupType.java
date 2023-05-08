package me.smartstore.group;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum GroupType {
	NONE("N"),
	GENERAL("G"),
	VIP("V"),
	VVIP("VV");

	private String label;

	GroupType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		return this.name() + "(" + this.label + ")";
	}

	public static GroupType findByInput(String input) {
		return Arrays.stream(GroupType.values())
			.skip(1)    // NONE은 건너뜀
			.filter(t -> t.name().equals(input) || t.getLabel().equals(input))
			.findAny()
			.orElseThrow(NoSuchElementException::new);
	}
}