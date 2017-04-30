package com.HRAPP.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.HRAPP.dto.CompetencyDto;
import com.HRAPP.entity.CompetencyDo;
import com.HRAPP.response.ResponseMessage;
import com.HRAPP.util.Make4Digit;
import com.HRAPP.util.PrefixEnum;
import com.HRAPP.util.ResponseEnum;
import com.HRAPP.util.ServicesUtil;

public class CompetencyDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);
	private static final String PERSISTENCE_UNIT_NAME = "HRAPP_PU";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	private EntityManager em = factory.createEntityManager();
	
	public CompetencyDao() {
	}
	
	
	
	public CompetencyDto exportDto(CompetencyDo inputDo)
	{
		CompetencyDto outputDto=new CompetencyDto();
		outputDto.setCompetencyId(inputDo.getCompetencyId());
		outputDto.setCompetencyName(inputDo.getCompetencyName());
		outputDto.setCompDesc(inputDo.getCompDesc());
		outputDto.setRatingScaleId(inputDo.getRatingScaleId());
		
		return outputDto;
	}
	
	public CompetencyDo importDo(CompetencyDto inputDto)
	{
		CompetencyDo outputDo=new CompetencyDo();
		outputDo.setCompetencyId(inputDto.getCompetencyId());
		outputDo.setCompetencyName(inputDto.getCompetencyName());
		outputDo.setCompDesc(inputDto.getCompDesc());
		outputDo.setRatingScaleId(inputDto.getRatingScaleId());
		
		return outputDo;
	}
	
	public ResponseMessage createCompetency(CompetencyDto competencyDto)
	{
		LOGGER.debug("[HRAPP][CompetencyDao][createCompetency] initiated "+competencyDto.getCompetencyId()+" "+competencyDto.getCompetencyName());
		ResponseMessage responseMessage=new ResponseMessage();
		SequenceDao sequenceDao=new SequenceDao();
		try{
			Integer masterId=sequenceDao.getLastId(PrefixEnum.COMPETENCY.prefix());
			String competencyId=PrefixEnum.COMPETENCY.prefix()+new Make4Digit().make4Digit(masterId);
			competencyDto.setCompetencyId(competencyId);
			em.getTransaction().begin();
			em.persist(importDo(competencyDto));
			em.getTransaction().commit();
			
			sequenceDao.updateLastId((masterId+1), PrefixEnum.COMPETENCY.prefix());
			
			responseMessage.setMessageType(ResponseEnum.SUCCESS.status());
			responseMessage.setMessage("Competency successfully created");
			LOGGER.debug("[HRAPP][CompetencyDao][createCompetency] User Created Successfuly "+competencyDto.getCompetencyId()+" "+competencyDto.getCompetencyName());
			return responseMessage;
		}
		catch(Exception e)
		{
			LOGGER.error("Error while creating Competency:- "+e.getMessage());
			responseMessage.setMessageType(ResponseEnum.ERROR.status());
			responseMessage.setMessage("Error while creating competency");
			return responseMessage;
		}
	}
	
	@SuppressWarnings("unchecked")
	public ResponseMessage readCompetency(CompetencyDto competencyDto)
	{
		ResponseMessage responseMessage=new ResponseMessage();
			LOGGER.debug("[HRAPP][CompetencyDao][readCompetency] initiated "+competencyDto.getCompetencyId()+" "+competencyDto.getCompetencyName());
			CompetencyDto competencyDtoObj=new CompetencyDto();
			try{
				String stringQuery="Select comp from CompetencyDo comp where comp.competencyId=?1";
				LOGGER.debug("[HRAPP][CompetencyDao][readCompetency] Query:-"+stringQuery);
				Query query=em.createQuery(stringQuery);
				query.setParameter(1, competencyDto.getCompetencyId());
				List<CompetencyDo>  competencyDos = query.getResultList();
				if(!ServicesUtil.isEmpty(competencyDos))
				{
					competencyDtoObj = exportDto(competencyDos.get(0));
				}
				responseMessage.setCompetencyDto(competencyDtoObj);
				responseMessage.setMessageType(ResponseEnum.SUCCESS.status());
				responseMessage.setMessage("Competency Read successfully");
				
			}
			catch(Exception e)
			{
				LOGGER.debug("[HRAPP][CompetencyDao][readCompetency] errror occured:-"+e.getMessage());
				responseMessage.setMessageType(ResponseEnum.ERROR.status());
				responseMessage.setMessage("Error while reading competency");
				return responseMessage;
			}
			return responseMessage;
		}
	
	
	@SuppressWarnings("unchecked")
	public ResponseMessage readCompetencies()
	{
		ResponseMessage responseMessage=new ResponseMessage();
			LOGGER.debug("[HRAPP][CompetencyDao][readCompetencies] initiated ");
			List<CompetencyDto> competencyDtos=new ArrayList<>();
			try{
				String stringQuery="Select comp from CompetencyDo comp";
				LOGGER.debug("[HRAPP][CompetencyDao][readCompetencies] Query:-"+stringQuery);
				Query query=em.createQuery(stringQuery);
				List<CompetencyDo>  competencyDos = query.getResultList();
				if(!ServicesUtil.isEmpty(competencyDos))
				{
					for(CompetencyDo competencyDo : competencyDos)
					{
						CompetencyDto competencyDtoObj = exportDto(competencyDo);
						competencyDtos.add(competencyDtoObj);
					}
				}
				responseMessage.setCompetencyDtos(competencyDtos);
				responseMessage.setMessageType(ResponseEnum.SUCCESS.status());
				responseMessage.setMessage("Competencies Read successfully");
				
			}
			catch(Exception e)
			{
				LOGGER.debug("[HRAPP][CompetencyDao][readCompetencies] errror occured:-"+e.getMessage());
				responseMessage.setMessageType(ResponseEnum.ERROR.status());
				responseMessage.setMessage("Error while reading competencies");
				return responseMessage;
			}
			return responseMessage;
		}
	
	

	public ResponseMessage updateCompetency(CompetencyDto competencyDto)
	{
		ResponseMessage responseMessage=new ResponseMessage();
			LOGGER.debug("[HRAPP][CompetencyDao][updateCompetency] initiated ");
			
			try{
				String stringQuery="Update CompetencyDo comp set comp.competencyName = ?2 ,comp.compDesc = ?3 , comp.ratingScaleId = ?4 where comp.competencyId = ?1";
				
				LOGGER.debug("[HRAPP][CompetencyDao][readCompetencies] Query:-"+stringQuery);
				Query query=em.createQuery(stringQuery);
				query.setParameter(1, competencyDto.getCompetencyId());
				query.setParameter(2, competencyDto.getCompetencyName());
				query.setParameter(3, competencyDto.getCompDesc());
				query.setParameter(4, competencyDto.getRatingScaleId());
				em.getTransaction().begin();
				int updateCount = query.executeUpdate();
				if(updateCount>0)
				{
					responseMessage.setMessageType(ResponseEnum.SUCCESS.status());
					responseMessage.setMessage("Competencies Updated successfully");
				}
				
				
				
			}
			catch(Exception e)
			{
				em.getTransaction().rollback();
				LOGGER.debug("[HRAPP][CompetencyDao][updateCompetency] errror occured:-"+e.getMessage());
				responseMessage.setMessageType(ResponseEnum.ERROR.status());
				responseMessage.setMessage("Error while updating competency");
				return responseMessage;
			}
			em.getTransaction().commit();
			return responseMessage;
		}
	
	
	
}
