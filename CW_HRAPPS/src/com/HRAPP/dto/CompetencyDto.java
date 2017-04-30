package com.HRAPP.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CompetencyDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String competencyId;
	private String competencyName;
	private String compDesc;
	private String ratingScaleId;
	public String getCompetencyId() {
		return competencyId;
	}
	public void setCompetencyId(String competencyId) {
		this.competencyId = competencyId;
	}
	public String getCompetencyName() {
		return competencyName;
	}
	public void setCompetencyName(String competencyName) {
		this.competencyName = competencyName;
	}
	public String getCompDesc() {
		return compDesc;
	}
	public void setCompDesc(String compDesc) {
		this.compDesc = compDesc;
	}
	public String getRatingScaleId() {
		return ratingScaleId;
	}
	public void setRatingScaleId(String ratingScaleId) {
		this.ratingScaleId = ratingScaleId;
	}
	
	

}
