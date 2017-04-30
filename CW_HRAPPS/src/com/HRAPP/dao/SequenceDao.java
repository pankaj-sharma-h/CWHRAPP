package com.HRAPP.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.HRAPP.dto.SequenceDto;
import com.HRAPP.entity.SequenceDo;
import com.HRAPP.response.ResponseMessage;
import com.HRAPP.util.ResponseEnum;
import com.HRAPP.util.ServicesUtil;

public class SequenceDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(SequenceDao.class);
	private static final String PERSISTENCE_UNIT_NAME = "HRAPP_PU";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	private EntityManager em = factory.createEntityManager();
	
	public SequenceDao() {
	}
	
	public SequenceDao(EntityManager em) {
		super();
		this.em = em;
	}
	
	public SequenceDto importDto(SequenceDo inputDo)
	{
		SequenceDto outputDto=new SequenceDto();
		outputDto.setSeqId(inputDo.getSeqId());
		outputDto.setTableName(inputDo.getTableName());
		outputDto.setPrefix(inputDo.getPrefix());
		outputDto.setSequenceValue(inputDo.getSequenceValue());
		
		return outputDto;
	}
	
	public SequenceDo exportDto(SequenceDto inputDto)
	{
		SequenceDo outputDo=new SequenceDo();
		outputDo.setSeqId(inputDto.getSeqId());
		outputDo.setTableName(inputDto.getTableName());
		outputDo.setPrefix(inputDto.getPrefix());
		outputDo.setSequenceValue(inputDto.getSequenceValue());
		
		return outputDo;
	}
	
	public ResponseMessage createSequence(SequenceDto dto)
	{
		ResponseMessage responseMessage=new ResponseMessage();
		try{
			em.getTransaction().begin();
			em.persist(exportDto(dto));
			responseMessage.setMessageType(ResponseEnum.SUCCESS.status());
			responseMessage.setMessage("Successfully Sequence Generator Created");
			
		}
		catch(Exception e)
		{
			em.getTransaction().rollback();
			LOGGER.error("Error while creating scale:- "+e.getMessage());
			responseMessage.setMessageType(ResponseEnum.ERROR.status());
			responseMessage.setMessage("Error while creating Sequence Generator.");
			return responseMessage;
		}
		em.getTransaction().commit();
		return responseMessage;
	}

	@SuppressWarnings("unchecked")
	public ResponseMessage getSequence()
	{
		ResponseMessage responseMessage=new ResponseMessage();
		LOGGER.debug("[HRAPP][SequenceDao][getSequence] initiated ");
		List<SequenceDto> sequenceDtos=new ArrayList<SequenceDto>();
		try{
			String stringQuery="Select seq from SequenceDo seq";
			LOGGER.debug("[HRAPP][SequenceDao][getSequence] Query:-"+stringQuery);
			Query query=em.createQuery(stringQuery);
			
			List<SequenceDo>  sequenceDo = query.getResultList();
			if(!ServicesUtil.isEmpty(sequenceDo))
			{
				for(SequenceDo sequenceDos : sequenceDo)
				{
					SequenceDto SequenceDtoObj = importDto(sequenceDos);
					sequenceDtos.add(SequenceDtoObj);
				}
			}
			responseMessage.setSequenceDto(sequenceDtos);
			responseMessage.setMessageType(ResponseEnum.SUCCESS.status());
			responseMessage.setMessage("Sequence Read successfully");
			
			
		}
		catch(Exception e)
		{
			
			LOGGER.debug("[HRAPP][SequenceDao][getSequence] errror occured:-"+e.getMessage());
			
			return null;
		}
	
		return responseMessage;
	}
	
	public Integer getLastId(String prefixName)
	{
		System.err.println("Start Sequence" + prefixName);
		LOGGER.debug("[HRAPP][SequenceDao][getLastId] initiated "+prefixName);
		SequenceDto sequenceDto=new SequenceDto();
		try{
			String stringQuery="Select seq from SequenceDo seq where seq.prefix=?1";
			System.err.println("Query"+ stringQuery);
			LOGGER.debug("[HRAPP][SequenceDao][getLastId] Query:-"+stringQuery);
			Query query=em.createQuery(stringQuery);
			query.setParameter(1, prefixName);
			
			@SuppressWarnings("unchecked")
			List<SequenceDo>  sequenceDo = query.getResultList();
			if(!ServicesUtil.isEmpty(sequenceDo))
			{
				sequenceDto = importDto(sequenceDo.get(0));
			}
			
			
			
		}
		catch(Exception e)
		{
			System.err.println("Seq" + e.getMessage());
		
			LOGGER.debug("[HRAPP][SequenceDao][getLastId] errror occured:-"+e.getMessage());
			
			return null;
		}
		
		return sequenceDto.getSequenceValue();
	}
	
	public String updateLastId(Integer lastId,String prefixName)
	{
		LOGGER.debug("[HRAPP][SequenceDao][updateLastId] Initiated:-"+lastId+" "+prefixName);
		
		try{
			String stringQuery="Update SequenceDo seq set seq.sequenceValue=?1 where seq.prefix=?2";
			LOGGER.debug("[HRAPP][SequenceDao][updateLastId] Query:-"+stringQuery);
			em.getTransaction().begin();
			Query updateQuery=em.createQuery(stringQuery);
			updateQuery.setParameter(1, lastId);
			updateQuery.setParameter(2, prefixName);
			Integer updatedCount=updateQuery.executeUpdate();
			em.getTransaction().commit();
			if(updatedCount>0)
				return ResponseEnum.SUCCESS.status();
			
			
		}
		catch(Exception e)
		{
			//em.getTransaction().rollback();
			LOGGER.debug("[HRAPP][SequenceDao][updateLastId] Exception:-"+e.getMessage());
			return ResponseEnum.ERROR.status();
		}
		
		
		return ResponseEnum.SUCCESS.status();
	}
	
}
