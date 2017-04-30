package com.HRAPP.util;

public enum PrefixEnum {

	RATING("RAT"),
	RATINGSCALE("SCAL"),
	RATSCLMAP("RATSCAL"),
	USER("USER"),
	SEQ("SEQ"),
	COMPETENCY("COMP");
	
	private String message;

	PrefixEnum(String message) {
        this.message = message;
    }

    public String prefix() {
        return message;
    }
	
}
