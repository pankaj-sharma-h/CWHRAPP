package com.HRAPP.util;

public enum RoleEnum {
	ADMIN("ADMIN"),
	EMPLOYEE("EMPLOYEE"),
	MANAGER("MANAGER");
	
	private String message;

	RoleEnum(String message) {
        this.message = message;
    }

    public String role() {
        return message;
    }

}
