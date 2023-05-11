package me.smartstore.util;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum SortOrder {
	ASCENDING("A"),
	DESCENDING("D");

	private String label;

	SortOrder(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		return this.name() + "(" + this.label + ")";
	}

	public static SortOrder findByInput(String input) {
		return Arrays.stream(SortOrder.values())
			.filter(t -> t.name().equals(input) || t.getLabel().equals(input))
			.findAny()
			.orElseThrow(NoSuchElementException::new);
	}
}
