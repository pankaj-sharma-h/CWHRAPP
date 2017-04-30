package com.HRAPP.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: SequnceDo
 *
 */
@Entity
@Table(name="HRAPPS_SEQ")
public class SequenceDo implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public SequenceDo() {
		super();
	}
   
	@Id
	@GeneratedValue
	@Column(name="SEQ_ID")
	private Integer seqId;
	
	@Column(name="TAB_NAME")
	private String tableName;
	
	@Column(name="PREFIX")
	private String prefix;
	
	@Column(name="SEQ_VAL")
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
