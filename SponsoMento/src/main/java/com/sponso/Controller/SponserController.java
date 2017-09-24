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
import com.sponso.Pojo.*;
import com.sponso.Service.*;
//import com.sponso.Service.StudentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@RestController
@EnableSwagger2
@Api(value="sponsor")
public class SponserController {

	@Autowired
	public SponserService sponservice;
	
	@Autowired
	public StudentService stservice;

	
	@SuppressWarnings({ "rawtypes" })
	@CrossOrigin
	@RequestMapping(value = "/sponsor/save", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Saving Sponsor", notes = "", response = Sponser.class)
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
	@RequestMapping(value = "/sponsor/getall", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Getting Sponsor", notes = "")
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
	@RequestMapping(value = "/sponsor/login", method = RequestMethod.POST, produces = "application/json")
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
	@RequestMapping(value = "/sponsor/getbyid/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Getting Sponsor", notes = "")
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
	@RequestMapping(value = "/sponsor/update", method = RequestMethod.PUT, produces = "application/json")
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
	@RequestMapping(value = "/sponsor/getsponsored/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Getting Sponser", notes = "")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "valid user"),
    @ApiResponse(code = 404, message = "un-authorized user") })
	@ResponseBody
	public ResponseEntity getSponsered(@RequestParam("id") int id){
	
		List<SponserLink> list = sponservice.getsponsored(id);
		System.out.println(list.get(0).getId());
		
		//Sponser s = sponservice.getbyid(link.getSponsorId());
		//System.out.println(link.getFund());
		
		List<Student> st= new ArrayList<Student>();
		for(int i = 0;i<list.size();i++){
			Student s = new Student();
			s=stservice.getbyid(list.get(i).getStudentId());
			st.add(s);
			System.out.println("id"+list.get(i).getStudentId());
		}
		
		if(!st.isEmpty()) 
		return ResponseEntity.status(200).body(st);
		else
			return ResponseEntity.status(400).body("{\"error\":\"No Data Found\"}");
	}
	
	
	@SuppressWarnings({ "rawtypes" })
	@CrossOrigin
	@RequestMapping(value = "/sponsor/sponsor/{sponser_id}/{student_id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Getting Sponser", notes = "")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "valid user"),
    @ApiResponse(code = 404, message = "un-authorized user") })
	@ResponseBody
	public ResponseEntity Sponser(@RequestParam("sponser_id") int sponser_id,@RequestParam("student_id") int student_id){
	
		Student student = stservice.getbyid(student_id);
		Sponser sponser = sponservice.getbyid(sponser_id);
		SponserLink link = new SponserLink();
		link.setSponsorId(sponser_id);
		link.setStudentId(student_id);
		long sp_fund = sponser.getFund();
		long st_fund = student.getFund_needed();
		link.setFund(sp_fund);
		boolean t = sponservice.fund(link);
		if(sp_fund>st_fund){
			sp_fund = sp_fund-st_fund;
			sponser.setFund(st_fund);
			sponservice.save(sponser);
			student.setFund_needed(0);
			student.setFund_collected(sp_fund);
			stservice.save(student);
		}
		else if(sp_fund == st_fund){ 
			sponser.setFund(0);
			sponservice.save(sponser);
			student.setFund_collected(sp_fund);
			student.setFund_needed(0);
			stservice.save(student);
		}
		else{
			
			long n = student.getFund_collected();
			n = n+sp_fund;
			sponser.setFund(0);
			sponservice.save(sponser);
			student.setFund_collected(n);
			student.setFund_needed(st_fund-n);
			stservice.save(student);
		}
		
		if(t) 
		return ResponseEntity.status(200).body("{\"success\":\"fund transferred\"}");
		else
			return ResponseEntity.status(400).body("{\"error\":\"No Data Found\"}");
	}
	
	@SuppressWarnings({ "rawtypes" })
	@CrossOrigin
	@RequestMapping(value = "/sponsor/getstudents/{skill}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Getting Sponser", notes = "")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "valid user"),
    @ApiResponse(code = 404, message = "un-authorized user") })
	@ResponseBody
	public ResponseEntity getMentee(@RequestParam("skill") String skill){
		
		List<Student> st = sponservice.getbyskill(skill);
		if(!st.isEmpty()) 
		return ResponseEntity.status(200).body(st);
		else
			return ResponseEntity.status(400).body("{\"error\":\"No Data Found\"}");
	}
	
	
	
}
