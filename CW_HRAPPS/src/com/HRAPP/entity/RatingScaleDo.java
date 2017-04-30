package com.HRAPP.entity;

/**
 * 
 */


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Team5
 * Do object  for Rating Scale table
 */
@Entity
@Table(name="HRAPPS_RATINGSCALE")
public class RatingScaleDo {
		@Id
		@Column(name="RATINGSCALE_ID",length=20)
		private String scaleId;
		
		@Column(name="RATINGSCALE_NAME",length=255)
		private String name;
		
		@Column(name="RATINGSCALE_SIZE")
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
