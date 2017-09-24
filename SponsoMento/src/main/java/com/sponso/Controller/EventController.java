package com.sponso.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sponso.Model.EventModel;
import com.sponso.Pojo.Event;
import com.sponso.Pojo.Student;
import com.sponso.Service.EventService;
import com.sponso.Service.SponserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
@Api(value="event")
public class EventController {

	@Autowired
	private EventService service;
	
	@Autowired
	private SponserService spservice;
	
	@SuppressWarnings({ "rawtypes" })
	@CrossOrigin
	@RequestMapping(value = "event/getall", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Getting Events", notes = "")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "valid user"),
    @ApiResponse(code = 404, message = "un-authorized user") })
	@ResponseBody
	public ResponseEntity getall(){

		List<Event> ev = service.getall();
		if(!ev.isEmpty()) 
		return ResponseEntity.status(200).body(ev);
		else
			return ResponseEntity.status(400).body("{\"error\":\"No Data Found\"}");
	}
	
	
	@SuppressWarnings({ "rawtypes" })
	@CrossOrigin
	@RequestMapping(value = "event/getbyskill{skill}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Getting Events", notes = "")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "valid user"),
    @ApiResponse(code = 404, message = "un-authorized user") })
	@ResponseBody
	public ResponseEntity getbyskill(@RequestParam("skill") String skill){

		List<Event> ev = service.getbyskill(skill); 
		if(!ev.isEmpty()) 
		return ResponseEntity.status(200).body(ev);
		else
			return ResponseEntity.status(400).body("{\"error\":\"No Data Found\"}");
	}
	
	@SuppressWarnings({ "rawtypes" })
	@CrossOrigin
	@RequestMapping(value = "event/save", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Saving Events", notes = "")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "valid user"),
    @ApiResponse(code = 404, message = "un-authorized user") })
	@ResponseBody
	public ResponseEntity saveEvent(@RequestBody EventModel model){

		Event ev = new Event();
		ev.setLocation(model.getLocation());
		ev.setName(model.getName());
		ev.setSponser(spservice.getbyid(model.getSponserId()));
		ev.setSkill(model.getSkill());
		if(service.save(ev)) 
		return ResponseEntity.status(201).body(ev);
		else
		return ResponseEntity.status(400).body("{\"error\":\"No Data Saved\"}");
	}
	
	
}
