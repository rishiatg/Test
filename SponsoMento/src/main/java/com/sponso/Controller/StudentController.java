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

import com.sponso.Model.LoginModel;
import com.sponso.Pojo.Sponser;
import com.sponso.Pojo.Student;
import com.sponso.Service.StudentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
@Api(value="student")
public class StudentController {
 
	@Autowired
	public StudentService stservice;
	
	@SuppressWarnings({ "rawtypes" })
	@CrossOrigin
	@RequestMapping(value = "/student/save", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Saving Sponser", notes = "", response = Student.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "valid user"),
    @ApiResponse(code = 404, message = "un-authorized user") })
	@ResponseBody
	public ResponseEntity saveStudent(@RequestBody Student student){

		if(stservice.save(student)=="success") 
		return ResponseEntity.status(201).body("{\"success\":\"Data Saved\"}");
		else
			return ResponseEntity.status(400).body("{\"error\":\"Data not Saved\"}");
	}
	
	@SuppressWarnings({ "rawtypes" })
	@CrossOrigin
	@RequestMapping(value = "/student/getall", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Getting Student", notes = "")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "valid user"),
    @ApiResponse(code = 404, message = "un-authorized user") })
	@ResponseBody
	public ResponseEntity getStudent(){
	
		List<Student> list = stservice.getall(); 
		if(!list.isEmpty()) 
		return ResponseEntity.status(200).body(list);
		else
			return ResponseEntity.status(400).body("{\"error\":\"No Data Found\"}");
	}
	
	@SuppressWarnings({ "rawtypes" })
	@CrossOrigin
	@RequestMapping(value = "student/login", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Getting Student", notes = "")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "valid user"),
    @ApiResponse(code = 404, message = "un-authorized user") })
	@ResponseBody
	public ResponseEntity getvalidStudent(@RequestBody LoginModel model){
	
		Student s = stservice.get(model); 
		if(s!=null) 
		return ResponseEntity.status(200).body(s);
		else
			return ResponseEntity.status(400).body("{\"error\":\"No Data Found\"}");
	}

	
	@SuppressWarnings({ "rawtypes" })
	@CrossOrigin
	@RequestMapping(value = "student/getbyid/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Getting Sponser", notes = "")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "valid user"),
    @ApiResponse(code = 404, message = "no data") })
	@ResponseBody
	public ResponseEntity getstudentbyid(@RequestParam("id") int id){
	
		Student s = stservice.getbyid(id); 
		if(s!=null) 
		return ResponseEntity.status(200).body(s);
		else
			return ResponseEntity.status(400).body("{\"error\":\"No Data Found\"}");
	}
	
	@SuppressWarnings({ "rawtypes" })
	@CrossOrigin
	@RequestMapping(value = "student/update", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Getting Sponser", notes = "")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "valid user"),
    @ApiResponse(code = 404, message = "un-authorized user") })
	@ResponseBody
	public ResponseEntity updatestudent(@RequestBody Student st){
	
		Student s = stservice.getbyid(st.getId());
		s.setFund_needed(st.getFund_needed());
		s.setFund_collected(st.getFund_collected());
		s.setSkill(st.getSkill());
		String str = stservice.save(s);
		if(str=="success") 
		return ResponseEntity.status(200).body(s);
		else
			return ResponseEntity.status(400).body("{\"error\":\"No Data Found\"}");
	}
	
	@SuppressWarnings({ "rawtypes" })
	@CrossOrigin
	@RequestMapping(value = "/student/getstudenttosponser", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Getting Sponser", notes = "")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "valid user"),
    @ApiResponse(code = 404, message = "un-authorized user") })
	@ResponseBody
	public ResponseEntity getstudenttosponser(){
	
		List<Student> s = stservice.getstudenttosponser();	
		if(!s.isEmpty()) 
		return ResponseEntity.status(200).body(s);
		else
			return ResponseEntity.status(400).body("{\"error\":\"No Data Found\"}");
	}
	
}
