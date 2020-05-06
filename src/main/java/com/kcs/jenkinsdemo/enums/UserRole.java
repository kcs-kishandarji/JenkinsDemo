package com.kcs.jenkinsdemo.enums;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

public enum UserRole {

	ADMIN("ADMIN"), 
	USER("USER"), 
	GUEST("GUEST");

	private final String name;

	private UserRole(String value) {
		this.name = value;
	}

	public String value() {
		return this.name;
	}

	public static UserRole from(String value) {
		return Arrays.stream(UserRole.values()).filter(e -> StringUtils.equalsIgnoreCase(e.name(), value)).findFirst()
				.orElse(null);
	}

	@Override
	public String toString() {
		return name;
	}
}
