package com.HRAPP.request;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.HRAPP.dto.RatingDto;
import com.HRAPP.dto.RatingScaleDto;

@XmlRootElement
public class RatingScaleRequestDto implements Serializable{

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RatingScaleDto ratingScaleDto;
	
	private List<RatingDto> ratingListDto;

	public RatingScaleDto getRatingScaleDto() {
		return ratingScaleDto;
	}

	public void setRatingScaleDto(RatingScaleDto ratingScaleDto) {
		this.ratingScaleDto = ratingScaleDto;
	}

	public List<RatingDto> getRatingListDto() {
		return ratingListDto;
	}

	public void setRatingListDto(List<RatingDto> ratingListDto) {
		this.ratingListDto = ratingListDto;
	}
	
	
	

}
