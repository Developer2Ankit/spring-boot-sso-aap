package com.sso.api.common.utils;

public enum UserTopic {

	USER("User", 101);

	private final String name;

	private final int code;

	private UserTopic(String name, int code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public int getCode() {
		return code;
	}

}
