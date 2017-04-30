package com.HRAPP.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.HRAPP.dto.CompetencyDto;
import com.HRAPP.dto.RatingDto;
import com.HRAPP.dto.RatingScaleDto;
import com.HRAPP.dto.SequenceDto;
import com.HRAPP.dto.UserDto;

/**
 * @author Deep
 *
 */
@XmlRootElement
public class ResponseMessage {

	private String message;
	private String messageType;
	private List<RatingScaleDto> scaleDto;
	private List<RatingDto> ratingDto;
	private UserDto userDto;
	private List<SequenceDto> sequenceDto;
	private CompetencyDto competencyDto;
	private List<CompetencyDto> competencyDtos;
	

	public CompetencyDto getCompetencyDto() {
		return competencyDto;
	}
	public void setCompetencyDto(CompetencyDto competencyDto) {
		this.competencyDto = competencyDto;
	}
	public List<CompetencyDto> getCompetencyDtos() {
		return competencyDtos;
	}
	public void setCompetencyDtos(List<CompetencyDto> competencyDtos) {
		this.competencyDtos = competencyDtos;
	}
	public List<SequenceDto> getSequenceDto() {
		return sequenceDto;
	}
	public void setSequenceDto(List<SequenceDto> sequenceDto) {
		this.sequenceDto = sequenceDto;
	}
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	public List<RatingDto> getRatingDto() {
		return ratingDto;
	}
	public void setRatingDto(List<RatingDto> ratingDto) {
		this.ratingDto = ratingDto;
	}
	public List<RatingScaleDto> getScaleDto() {
		return scaleDto;
	}
	public void setScaleDto(List<RatingScaleDto> scaleDto) {
		this.scaleDto = scaleDto;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	
}
