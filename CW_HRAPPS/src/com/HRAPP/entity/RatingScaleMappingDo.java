package com.HRAPP.entity;



import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="HRAPPS_RATINGSCALEMAPPING")
public class RatingScaleMappingDo{

	@EmbeddedId
    private RatingMapKey ratingMapKeyPK;

	public RatingMapKey getRatingMapKeyPK() {
		return ratingMapKeyPK;
	}

	public void setRatingMapKeyPK(RatingMapKey ratingMapKeyPK) {
		this.ratingMapKeyPK = ratingMapKeyPK;
	}

	
}
