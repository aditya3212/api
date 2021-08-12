package com.moneylend.api.users;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@RestController
public class UsersController {
	private EntityManager entityManager;
	
	@Autowired
	UsersService usersService;
	@Autowired
	public UsersController(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@GetMapping("/hii")
	public Object sayHii()
	{
		Session currentSession = entityManager.unwrap(Session.class);
		String queryString="Select * from persons where PersonId=1";
		NativeQuery query = currentSession.createNativeQuery(queryString);

		Object result = query.getResultList();
		return result;
	}
	
	@PostMapping("/users/create")
	public Object createNewUser(HashMap<String,String> hm )
	{
		
		return null;
	}
	
	@PostMapping("/users/add")
	public ResponseEntity<Map> addUser (
			@RequestParam(value = "userDetails",required=false) String userDetails,
			@RequestParam(value = "userImage", required = false) MultipartFile userImage) 
					throws IOException, ParseException {
		
		System.out.println("in user add controlleer\n\n\n");
		Map hm = new HashMap();
		//return new ResponseEntity<Map>(hm, HttpStatus.OK);	
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(userDetails);
		
		boolean flag=usersService.addUser(json,userImage);
		
		
		if(flag==true) {
			hm.put("status", "Success");
			hm.put("statusCode", "S");
			hm.put("message", "Users successfully added");
		}else {
			hm.put("status", "Failure");
			hm.put("statusCode", "F");
			hm.put("message", "Something went wrong");
		}
		
//		hm.put("result", superAdminService.addCollege(json,collegeImage);
		return new ResponseEntity<Map>(hm, HttpStatus.OK);	
	}
	
	@GetMapping("/users/validatecontact")
	public ResponseEntity<Map> validateContact(@RequestParam(value="searchq") String searchParam, 
			@RequestParam(value="type") String type) {
		Map hm = new HashMap();
		hm.put("status", "Success");
		hm.put("statusCode", "S");
		hm.put("result", usersService.validateContact(searchParam, type));
//		 logger.info("Inside the controller");
		return new ResponseEntity<Map>(hm, HttpStatus.OK);

	}
	
	
	
	//to check if given tenant url is present or not
//	@GetMapping("/superadmin/validateemail")
//	public ResponseEntity<Map> validateTenantUrl(@RequestParam("tenanturl") String tenantUrl  ) {
//		Map hm = new HashMap();
//		hm.put("status", "Success");
//		hm.put("statusCode", "S");
//		hm.put("result", superAdminService.validateTenantUrl(tenantUrl));
//		logger.info("Inside the controller");
//		return new ResponseEntity<Map>(hm, HttpStatus.OK);
//
//	}
	
}
