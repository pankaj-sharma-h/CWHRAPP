package com.HRAPP.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="HRAPPS_RATING")
public class RatingDo {
		@Id
		@Column(name="RATING_ID",length=20)
		private String ratingId;
		
		@Column(name="RATING_NAME",length=20)
		private String ratingName;
		
		@Column(name="RATING_VALUE")
		private Integer ratingValue;
		
		public String getRatingId() {
			return ratingId;
		}
		public void setRatingId(String ratingId) {
			this.ratingId = ratingId;
		}
		public String getRatingName() {
			return ratingName;
		}
		public void setRatingName(String ratingName) {
			this.ratingName = ratingName;
		}
		public Integer getRatingValue() {
			return ratingValue;
		}
		public void setRatingValue(Integer ratingValue) {
			this.ratingValue = ratingValue;
		}

}
