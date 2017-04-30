package com.HRAPP.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.HRAPP.dto.UserDto;
import com.HRAPP.entity.UserDo;
import com.HRAPP.response.ResponseMessage;
import com.HRAPP.util.ResponseEnum;
import com.HRAPP.util.ServicesUtil;

public class UserDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);
	private static final String PERSISTENCE_UNIT_NAME = "HRAPP_PU";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	private EntityManager em = factory.createEntityManager();
	
	public UserDao() {
	}
	
	public UserDao(EntityManager em) {
		super();
		this.em = em;
	}
	
	public UserDto importDto(UserDo inputDo)
	{
		UserDto outputDto=new UserDto();
		outputDto.setUserId(inputDo.getUserId());
		outputDto.setUserName(inputDo.getUserName());
		outputDto.setPassword(inputDo.getPassword());
		outputDto.setRole(inputDo.getRole());
		
		return outputDto;
	}
	
	public UserDo exportDto(UserDto inputDto)
	{
		UserDo outputDo=new UserDo();
		outputDo.setUserId(inputDto.getUserId());
		outputDo.setUserName(inputDto.getUserName());
		outputDo.setPassword(inputDto.getPassword());
		outputDo.setRole(inputDto.getRole());
		
		return outputDo;
	}
	
	public ResponseMessage createUser(UserDto userDto)
	{
		LOGGER.debug("[HRAPP][UserDao][createUser] initiated "+userDto.getUserName()+" "+userDto.getPassword());
		ResponseMessage responseMessage=new ResponseMessage();
		try{
			em.getTransaction().begin();
			em.persist(exportDto(userDto));
			em.getTransaction().commit();
			responseMessage.setMessageType(ResponseEnum.SUCCESS.status());
			responseMessage.setMessage("Successfully Scale created");
			LOGGER.debug("[HRAPP][UserDao][createUser] User Created Successfuly "+userDto.getUserName()+" "+userDto.getPassword());
			return responseMessage;
		}
		catch(Exception e)
		{
			LOGGER.error("Error while creating user:- "+e.getMessage());
			responseMessage.setMessageType(ResponseEnum.ERROR.status());
			responseMessage.setMessage("Error while creating user");
			return responseMessage;
		}
	}
	
	@SuppressWarnings("unchecked")
	public ResponseMessage readUser(UserDto userDto)
	{
		ResponseMessage responseMessage=new ResponseMessage();
			LOGGER.debug("[HRAPP][UserDao][readUser] initiated "+userDto.getUserId()+" "+userDto.getPassword());
			UserDto userDtoObj=new UserDto();
			try{
				String stringQuery="Select user from UserDo user where user.userName="+userDto.getUserName()+" and user.password="+userDto.getPassword();
				LOGGER.debug("[HRAPP][UserDao][readUser] Query:-"+stringQuery);
				Query query=em.createQuery(stringQuery);
				em.getTransaction().begin();
				List<UserDo>  userDos = query.getResultList();
				if(!ServicesUtil.isEmpty(userDos))
				{
					userDtoObj = importDto(userDos.get(0));
				}
				responseMessage.setUserDto(userDtoObj);
				responseMessage.setMessageType(ResponseEnum.SUCCESS.status());
				responseMessage.setMessage("User read successfully");
				
			}
			catch(Exception e)
			{
				em.getTransaction().rollback();
				LOGGER.debug("[HRAPP][HRDatabaseService][readRating] errror occured:-"+e.getMessage());
				responseMessage.setMessageType(ResponseEnum.ERROR.status());
				responseMessage.setMessage("Error while reading user");
				return responseMessage;
			}
			em.getTransaction().commit();
			return responseMessage;
		}
	
	
	
}

