package com.HRAPP.entity;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RatingMapKey implements Serializable{

	private static final long serialVersionUID = 1L;

	
	@Column(name="RATING_ID",length=20)
	private String ratingId;
	
	@Column(name="RATINGSCALE_ID",length=20)
	private String ratingScaleId;

	public String getRatingId() {
		return ratingId;
	}

	public void setRatingId(String ratingId) {
		this.ratingId = ratingId;
	}

	public String getRatingScaleId() {
		return ratingScaleId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ratingId == null) ? 0 : ratingId.hashCode());
		result = prime * result + ((ratingScaleId == null) ? 0 : ratingScaleId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RatingMapKey other = (RatingMapKey) obj;
		if (ratingId == null) {
			if (other.ratingId != null)
				return false;
		} else if (!ratingId.equals(other.ratingId))
			return false;
		if (ratingScaleId == null) {
			if (other.ratingScaleId != null)
				return false;
		} else if (!ratingScaleId.equals(other.ratingScaleId))
			return false;
		return true;
	}

	public void setRatingScaleId(String ratingScaleId) {
		this.ratingScaleId = ratingScaleId;
	}
	
	
}
