package com.HRAPP.util;

public enum ResponseEnum {
	
	SUCCESS("S"),
	ERROR("E");
	
	private String message;

	ResponseEnum(String message) {
        this.message = message;
    }

    public String status() {
        return message;
    }
	

}
