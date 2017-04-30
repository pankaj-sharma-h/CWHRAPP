package com.HRAPP.dto;



import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RatingScaleMappingDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
		private String ratingId;
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
		public void setRatingScaleId(String ratingScaleId) {
			this.ratingScaleId = ratingScaleId;
		}

}
