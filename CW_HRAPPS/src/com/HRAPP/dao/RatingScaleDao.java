package com.HRAPP.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.HRAPP.dto.RatingScaleDto;
import com.HRAPP.entity.RatingScaleDo;
import com.HRAPP.response.ResponseMessage;
import com.HRAPP.util.ResponseEnum;

public class RatingScaleDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RatingScaleDao.class);
	private static final String PERSISTENCE_UNIT_NAME = "HRAPP_PU";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	private EntityManager em = factory.createEntityManager();
	
	public RatingScaleDao() {
	}
	
	public RatingScaleDao(EntityManager em) {
		super();
		this.em = em;
	}

	public RatingScaleDo exportDto(RatingScaleDto dto)
	{
		RatingScaleDo doNew=new RatingScaleDo();
		doNew.setScaleId(dto.getScaleId());
		doNew.setName(dto.getName());
		doNew.setScaleSize(dto.getScaleSize());
		return doNew;
	}
	public RatingScaleDto importDto(RatingScaleDo doNew)
	{
		RatingScaleDto dto=new RatingScaleDto();
		dto.setScaleId(doNew.getScaleId());
		dto.setName(doNew.getName());
		dto.setScaleSize(doNew.getScaleSize());
		return dto;
	}
	
	public ResponseMessage create(RatingScaleDto dto)
	{
		LOGGER.debug("[HRAPP][RatingScaleDao][createRatingScale] initiated:-"+dto.getScaleSize());
		ResponseMessage responseMessage=new ResponseMessage();
		try{
			em.getTransaction().begin();
			em.persist(exportDto(dto));
			em.getTransaction().commit();
			responseMessage.setMessageType(ResponseEnum.SUCCESS.status());
			responseMessage.setMessage("Successfully Scale created");
			
			return responseMessage;
		}
		catch(Exception e)
		{
			//em.getTransaction().rollback();
			LOGGER.debug("[HRAPP][RatingScaleDao][createRatingScale] exception:-"+e.getMessage());
			responseMessage.setMessageType(ResponseEnum.ERROR.status());
			responseMessage.setMessage("Error while creating scale");
			return responseMessage;
		}
	}
	
	
}
