package com.HRAPP.util;

public class Make4Digit {
	
	public String make4Digit(Integer sequence)
	{
		String number=String.format("%04d", sequence);
		return number;
	}

}
