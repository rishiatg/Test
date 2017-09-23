package com.sponso.Controller;

import java.net.SocketOptions;
import java.util.ArrayList;
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

import com.sponso.Model.LoginModel;
import com.sponso.Pojo.Sponser;
import com.sponso.Pojo.SponserLink;
import com.sponso.Pojo.Student;
import com.sponso.Service.SponserService;
import com.sponso.Service.StudentService;
import com.sponso.repo.StudentRepo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@RestController
@EnableSwagger2
@Api(value="sponser")
public class SponserController {

	@Autowired
	public SponserService sponservice;
	
	@Autowired
	public StudentService stservice;
	
	@SuppressWarnings({ "rawtypes" })
	@CrossOrigin
	@RequestMapping(value = "/sponser/save", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Saving Sponser", notes = "", response = Sponser.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "valid user"),
    @ApiResponse(code = 404, message = "un-authorized user") })
	@ResponseBody
	public ResponseEntity saveSponser(@RequestBody Sponser sponser ){
		
		if(sponservice.save(sponser)=="success") 
		return ResponseEntity.status(201).body("{\"success\":\"Data Saved\"}");
		else
			return ResponseEntity.status(400).body("{\"error\":\"Data not Saved\"}");
	}
	
	@SuppressWarnings({ "rawtypes" })
	@CrossOrigin
	@RequestMapping(value = "/sponser/getall", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Getting Sponser", notes = "")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "valid user"),
    @ApiResponse(code = 404, message = "un-authorized user") })
	@ResponseBody
	public ResponseEntity getSponser(){
	
		List<Sponser> list = sponservice.getall(); 
		if(!list.isEmpty()) 
		return ResponseEntity.status(200).body(list);
		else
			return ResponseEntity.status(400).body("{\"error\":\"No Data Found\"}");
	}
	
	@SuppressWarnings({ "rawtypes" })
	@CrossOrigin
	@RequestMapping(value = "/sponser/login", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Validating Sponser", notes = "")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "valid user"),
    @ApiResponse(code = 404, message = "un-authorized user") })
	@ResponseBody
	public ResponseEntity getvalidSponser(@RequestBody LoginModel model){
	
		Sponser s = sponservice.get(model); 
		if(s!=null) 
		return ResponseEntity.status(200).body(s);
		else
			return ResponseEntity.status(400).body("{\"error\":\"No Data Found\"}");
	}
	
	@SuppressWarnings({ "rawtypes" })
	@CrossOrigin
	@RequestMapping(value = "/sponser/getbyid/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Getting Sponser", notes = "")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "valid user"),
    @ApiResponse(code = 404, message = "un-authorized user") })
	@ResponseBody
	public ResponseEntity getsponserbyid(@RequestParam("id") int id){
	
		Sponser s = sponservice.getbyid(id); 
		if(s!=null) 
		return ResponseEntity.status(200).body(s);
		else
			return ResponseEntity.status(400).body("{\"error\":\"No Data Found\"}");
	}
	
	@SuppressWarnings({ "rawtypes" })
	@CrossOrigin
	@RequestMapping(value = "/sponser/update", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Update Sponser", notes = "")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "valid user"),
    @ApiResponse(code = 404, message = "un-authorized user") })
	@ResponseBody
	public ResponseEntity updateSponser(@RequestBody Sponser s){
	
		Sponser sp = sponservice.getbyid(s.getId());
		sp.setFund(s.getFund());
		sp.setPrimary_skill(s.getPrimary_skill());
		sp.setSecondary_skill(s.getSecondary_skill());
		sponservice.save(sp);
		if(s!=null) 
		return ResponseEntity.status(200).body(s);
		else
			return ResponseEntity.status(400).body("{\"error\":\"No Data Found\"}");
	}
	
	@SuppressWarnings({ "rawtypes" })
	@CrossOrigin
	@RequestMapping(value = "/sponser/getsponsored/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Update Sponser", notes = "")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "valid user"),
    @ApiResponse(code = 404, message = "un-authorized user") })
	@ResponseBody
	public ResponseEntity getSponsered(@RequestParam("id") int id){
	
		List<SponserLink> list = sponservice.getsponsored(id);
		
		//Sponser s = sponservice.getbyid(link.getSponsorId());
		//System.out.println(link.getFund());
		
		List<Student> st= new ArrayList<Student>();
		for(int i = 0;i<list.size();i++){
			Student s = new Student();
			s=stservice.getbyid(list.get(i).getStudentId());
			st.add(s);
		}
		
		if(!st.isEmpty()) 
		return ResponseEntity.status(200).body(st);
		else
			return ResponseEntity.status(400).body("{\"error\":\"No Data Found\"}");
	}
	
}
