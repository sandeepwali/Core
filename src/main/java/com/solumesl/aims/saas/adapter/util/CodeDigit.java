package com.solumesl.aims.saas.adapter.util;

import java.util.Arrays;

public enum CodeDigit {
	TWELVE(12, 0),
	FOURTEEN(14, 0),
	SIXTEEN(16, 1),
	SEVENTEEN(17, 0),
	EIGHTEEN(18, 1);
	private final int value;
	private final int index;
	CodeDigit(int value, int index) {
		this.value = value;
		this.index = index;
	}
	public static CodeDigit valueOf(int intValue) {
		return Arrays.stream(values())
				.filter(codeDigit -> codeDigit.value == intValue)
				.findAny()
				.orElse(null);
	}
}
