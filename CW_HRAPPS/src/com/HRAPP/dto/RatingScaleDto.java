package com.HRAPP.dto;



import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RatingScaleDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String scaleId;
	private String name;
	private Integer scaleSize;
	public String getScaleId() {
		return scaleId;
	}
	public void setScaleId(String scaleId) {
		this.scaleId = scaleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getScaleSize() {
		return scaleSize;
	}
	public void setScaleSize(Integer scaleSize) {
		this.scaleSize = scaleSize;
	}
	
}
