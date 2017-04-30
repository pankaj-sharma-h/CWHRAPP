package com.HRAPP.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.HRAPP.dto.RatingScaleMappingDto;
import com.HRAPP.entity.RatingMapKey;
import com.HRAPP.entity.RatingScaleMappingDo;
import com.HRAPP.response.ResponseMessage;
import com.HRAPP.util.ResponseEnum;
public class RatingScaleMappingDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(RatingScaleMappingDao.class);
	private static final String PERSISTENCE_UNIT_NAME = "HRAPP_PU";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	private EntityManager em = factory.createEntityManager();
	
	public RatingScaleMappingDao() {
	}
	
	public RatingScaleMappingDao(EntityManager em) {
		super();
		this.em = em;
	}
	
	
	public RatingScaleMappingDto importDto(RatingScaleMappingDo inputDo)
	{
		RatingScaleMappingDto dto=new RatingScaleMappingDto();
		dto.setRatingId(inputDo.getRatingMapKeyPK().getRatingId());
		dto.setRatingScaleId(inputDo.getRatingMapKeyPK().getRatingScaleId());
		
		return dto;
	}
	public RatingScaleMappingDo exportDto(RatingScaleMappingDto inputDto)
	{
		RatingScaleMappingDo ratingScaleMappingDo=new RatingScaleMappingDo();
		RatingMapKey mapKey=new RatingMapKey();
		
		mapKey.setRatingId(inputDto.getRatingId());
		mapKey.setRatingScaleId(inputDto.getRatingScaleId());
		ratingScaleMappingDo.setRatingMapKeyPK(mapKey);
		
		return ratingScaleMappingDo;
	}
	
	public ResponseMessage create(RatingScaleMappingDto dto)
	{
		ResponseMessage responseMessage=new ResponseMessage();
		try{
			em.getTransaction().begin();
			em.persist(exportDto(dto));
			em.getTransaction().commit();
			responseMessage.setMessageType(ResponseEnum.SUCCESS.status());
			responseMessage.setMessage("Successfully Rating Scale Mapping created");
			return responseMessage;
		}
		catch(Exception e)
		{
			LOGGER.error("Error while creating scale:- "+e.getMessage());
			responseMessage.setMessageType(ResponseEnum.ERROR.status());
			responseMessage.setMessage("Error while creating Rating Scale Mapping:-"+e.getMessage());
			return responseMessage;
		}
	}
	
	
}
