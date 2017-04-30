package com.HRAPP.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SequenceDto {

	private Integer seqId;
	private String tableName;
	private String prefix;
	private Integer sequenceValue;
	
	
	public Integer getSequenceValue() {
		return sequenceValue;
	}
	public void setSequenceValue(Integer sequenceValue) {
		this.sequenceValue = sequenceValue;
	}
	public Integer getSeqId() {
		return seqId;
	}
	public void setSeqId(Integer seqId) {
		this.seqId = seqId;
	}
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	
}
