package com.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.HRAPP.dao.CompetencyDao;
import com.HRAPP.dao.RatingDao;
import com.HRAPP.dao.SequenceDao;
import com.HRAPP.dao.UserDao;
import com.HRAPP.dto.CompetencyDto;
import com.HRAPP.request.RatingScaleRequestDto;
import com.HRAPP.response.ResponseMessage;

@Path("/ratingMaster")


public class RatingCompetencyRestService {
	
	
	UserDao userDao=new UserDao();
	RatingDao ratingDao=new RatingDao();
	SequenceDao sequenceDao=new SequenceDao();
	CompetencyDao competencyDao=new CompetencyDao();
	
	
	@Path("/test")
	@GET
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String getVale()
	{
		return "Hello";
	}
	
	
	@Path("/createRatingScale")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseMessage createRatingScale(RatingScaleRequestDto ratingScaleRequestDto)
	{
		System.err.println("Coming");
		ResponseMessage response=new ResponseMessage();
		response=ratingDao.createRatingScale(ratingScaleRequestDto);
		return response; 
	}
	
	@POST
	@Path("/createCompetency")
	@Produces( { MediaType.APPLICATION_JSON })
	@Consumes({MediaType.APPLICATION_JSON})
	public ResponseMessage createCompetency (CompetencyDto competencyDto)
	{
		ResponseMessage response=new ResponseMessage();
		response=competencyDao.createCompetency(competencyDto);
		return response;
	}

	@Path("/getCompetencies")
	@GET
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseMessage getCompetencies()
	{
		ResponseMessage response=new ResponseMessage();
		response=competencyDao.readCompetencies();
		return response; 
	}

	@Path("/getCompetency")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseMessage getCompetency(CompetencyDto competencyDto)
	{
		ResponseMessage response=new ResponseMessage();
		response=competencyDao.readCompetency(competencyDto);
		return response; 
	}

	@Path("/updateCompetency")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseMessage updateCompetency(CompetencyDto competencyDto)
	{
		ResponseMessage response=new ResponseMessage();
		response=competencyDao.updateCompetency(competencyDto);
		return response; 
	}
	
	

}
