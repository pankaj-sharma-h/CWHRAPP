package com.HRAPP.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.HRAPP.dto.RatingDto;
import com.HRAPP.dto.RatingScaleDto;
import com.HRAPP.dto.RatingScaleMappingDto;
import com.HRAPP.entity.RatingDo;
import com.HRAPP.request.RatingScaleRequestDto;
import com.HRAPP.response.ResponseMessage;
import com.HRAPP.util.Make4Digit;
import com.HRAPP.util.PrefixEnum;
import com.HRAPP.util.ResponseEnum;
import com.HRAPP.util.ServicesUtil;

public class RatingDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RatingDao.class);
	private static final String PERSISTENCE_UNIT_NAME = "HRAPP_PU";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	private EntityManager em = factory.createEntityManager();
	
	public RatingDao() {
	}
	
	
	
	public RatingDo exportDto(RatingDto dto)
	{
		RatingDo doNew=new RatingDo();
		doNew.setRatingId(dto.getRatingId());
		doNew.setRatingName(dto.getRatingName());
		doNew.setRatingValue(dto.getRatingValue());
		return doNew;
	}
	public RatingDto importDto(RatingDo doNew)
	{
		RatingDto dto=new RatingDto();
		dto.setRatingId(doNew.getRatingId());
		dto.setRatingName(doNew.getRatingName());
		dto.setRatingValue(doNew.getRatingValue());
		return dto;
	}
	
	public ResponseMessage createRating(RatingDto ratingDto)
	{
		LOGGER.debug("[HRAPP][RatingDao][createRating] initiated"+ratingDto.getRatingName());
		ResponseMessage responseMessage=new ResponseMessage();
		try{
			em.getTransaction().begin();
			em.persist(exportDto(ratingDto));
			em.getTransaction().commit();
			responseMessage.setMessageType(ResponseEnum.SUCCESS.status());
			responseMessage.setMessage("Successfully Rating created");
			return responseMessage;
		}
		catch(Exception e)
		{
			LOGGER.error("[HRAPP][RatingDao][createRating] Exception:- "+e.getMessage());
			responseMessage.setMessageType(ResponseEnum.ERROR.status());
			responseMessage.setMessage("Error while creating rating:-"+e.getMessage());
			return responseMessage;
		}
	}
	
	public ResponseMessage createRatingScale(RatingScaleRequestDto ratingScaleRequestDto)
	{
		LOGGER.debug("[HRAPP][RatingDao][createRatingScale] initiated");

		RatingScaleDao ratingScaleDao=new RatingScaleDao();
		SequenceDao sequenceDao=new SequenceDao();
		ResponseMessage response=new ResponseMessage();
		ResponseMessage daoResponse=new ResponseMessage();
		
		List<RatingDto> ratingDto=ratingScaleRequestDto.getRatingListDto();
		RatingScaleDto scaleDto=ratingScaleRequestDto.getRatingScaleDto();
		
		
		
		Integer masterId=sequenceDao.getLastId(PrefixEnum.RATINGSCALE.prefix());
		String scaleId=PrefixEnum.RATINGSCALE.prefix()+new Make4Digit().make4Digit(masterId);
		LOGGER.debug("[HRAPP][RatingDao][createRatingScale] initiated:-"+scaleId);
		if(ResponseEnum.ERROR.equals(sequenceDao.updateLastId(masterId+1, PrefixEnum.RATINGSCALE.prefix())))
		{
			response.setMessage("ERROR OCCURED");
			response.setMessageType(ResponseEnum.ERROR.status());
			return response;
		}
		
		try{
			
			if(!ServicesUtil.isEmpty(ratingDto) && !ServicesUtil.isEmpty(scaleDto))
			{
				scaleDto.setScaleId(scaleId);
				
				
				daoResponse=ratingScaleDao.create(scaleDto);
				
				if(daoResponse.getMessageType().equals(ResponseEnum.ERROR.status()))
				{
					
					response.setMessageType(daoResponse.getMessageType());
					response.setMessage("Error while creating scale");
					LOGGER.debug("[HRAPP][RatingDao][createRatingScale] error occured while creating scale:-"+daoResponse.getMessageType());
					return response;
				}
				else {
					masterId=sequenceDao.getLastId(PrefixEnum.RATING.prefix());
					String ratingId=PrefixEnum.RATING.prefix()+new Make4Digit().make4Digit(masterId);
					RatingScaleMappingDao ratingScaleMappingDao=new RatingScaleMappingDao();
					LOGGER.debug("[HRAPP][RatingDao][createRatingScale] ratingId:-"+ratingId+" "+masterId+" "+ratingDto.size());
					for(RatingDto dto:ratingDto)
					{
							System.err.println("RatingId:-"+ratingId);
							dto.setRatingId(ratingId);
							daoResponse=createRating(dto);
							if(daoResponse.getMessageType().equals(ResponseEnum.ERROR.status()))
							{
								
								LOGGER.debug("[HRAPP][RatingDao][createRatingScale] error occured while creating rating:-"+daoResponse.getMessageType());
								response.setMessageType(daoResponse.getMessageType());
								break;
							}
							else
							{
								System.err.println("RatingId:-"+ratingId+" ScaleId"+scaleId);
								RatingScaleMappingDto ratingScaleMappingDto=new RatingScaleMappingDto();
								ratingScaleMappingDto.setRatingScaleId(scaleId);
								ratingScaleMappingDto.setRatingId(ratingId);
								daoResponse=ratingScaleMappingDao.create(ratingScaleMappingDto);
								if(daoResponse.getMessageType().equals(ResponseEnum.ERROR.status()))
								{
									
									LOGGER.debug("[HRAPP][RatingDao][createRatingScale] error occured while creating rating scales mapping:-"+daoResponse.getMessageType());
									response.setMessageType(daoResponse.getMessageType());
									break;
								}
								else{
									response.setMessageType(daoResponse.getMessageType());
								}
								
							}
						
						
						masterId++;
						ratingId=PrefixEnum.RATING.prefix()+new Make4Digit().make4Digit(masterId);
						
					}
					
					if(response.getMessageType().equals(ResponseEnum.ERROR.status()))
					{
						
						response.setMessageType(ResponseEnum.ERROR.status());
						response.setMessage("Error while creating rating");
						LOGGER.debug("[HRAPP][RatingDao][createRatingScale] error occured while creating rating.");
						return response;
					}
					else
					{
						sequenceDao.updateLastId(masterId, PrefixEnum.RATING.prefix());
						response.setMessageType(ResponseEnum.SUCCESS.status());
						response.setMessage("Scale and Rating Created Successfully");
						return response;
					}
				}
				
			}
		}
		catch(Exception e)
		{
			response.setMessageType(ResponseEnum.ERROR.status());
			response.setMessage("Error while creating Rating Scale");
			LOGGER.debug("[HRAPP][RatingDao][createRatingScale] error occured while creating rating:-"+e.getMessage());
			return response;
		}
					
		
		
		return response;
	}
	
	
	
}
