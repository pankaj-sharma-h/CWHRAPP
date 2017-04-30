package com.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.HRAPP.dao.SequenceDao;
import com.HRAPP.dao.UserDao;
import com.HRAPP.dto.SequenceDto;
import com.HRAPP.dto.UserDto;
import com.HRAPP.response.ResponseMessage;

@Path("/master")
public class MasterRestService {
	
	UserDao userDao=new UserDao();
	SequenceDao sequenceDao=new SequenceDao();
	
	@POST
	@Path("/createUser")
	@Produces( { MediaType.APPLICATION_JSON })
	@Consumes({MediaType.APPLICATION_JSON})
	public ResponseMessage createUser (UserDto userDto)
	{
		ResponseMessage response=new ResponseMessage();
		response=userDao.createUser(userDto);
		return response;
	}
	
	@Path("/getUser")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseMessage getUser(UserDto requestDto)
	{
		ResponseMessage response=new ResponseMessage();
		response=userDao.readUser(requestDto);
		return response; 
	}
	
	@Path("/createSequence")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseMessage createSequence(SequenceDto requestDto)
	{
		ResponseMessage response=new ResponseMessage();
		response=sequenceDao.createSequence(requestDto);
		return response; 
	}
	
	@Path("/readSequence")
	@GET
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseMessage readSequence()
	{
		ResponseMessage response=new ResponseMessage();
		response=sequenceDao.getSequence();
		return response; 
	}
	

}
