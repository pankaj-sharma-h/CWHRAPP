package com.HRAPP.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: CompetencyDo
 *
 */
@Entity
@Table(name="HRAPPS_COMPETENCY")

public class CompetencyDo implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COMPETENCY_ID",length=10)
	private String competencyId;
	
	@Column(name="COMPETENCY_NAME",length=20)
	private String competencyName;
	
	@Column(name="COMPETENCY_DESC", length=255)
	private String compDesc;
	
	@Column(name="RATING_SCALE_ID", length=10)
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
