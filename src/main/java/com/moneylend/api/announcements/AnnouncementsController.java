package com.moneylend.api.announcements;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnnouncementsController {
	//add lender
	//add borrower
	//lender list
	//lender filter list
	//borrow list
	//borrow list
	//delete lender
	  
	AnnouncementsService announcementsService;
	
	@PostMapping("/public/borrowrequest/add")
	public ResponseEntity<Map> addBorrowRequestPublic(@RequestBody HashMap<String,String> payload)
	{
		Map<String, Object> hm = new HashMap<String, Object>();
		
		int insert=announcementsService.addBorrowRequestPublic(payload);
		if(insert>0)
		{
			hm.put("status", "Success");
			hm.put("statusCode", "S");
			hm.put("message", "Loan request added publically");
		}
		else
		{
			hm.put("status", "Fail");
			hm.put("statusCode", "F");
			hm.put("message", "Loan not reqeusted, something went wrong");
		}
		
		return new ResponseEntity<Map>(hm,HttpStatus.OK);
	}
	
	@PostMapping("/public/lendrequest/add")
	public ResponseEntity<Map> addLendRequestPublic(@RequestBody HashMap<String,String> payload)
	{
		Map<String, Object> hm = new HashMap<String, Object>();
		int insert=announcementsService.addLendRequestPublic(payload);
		if(insert>0)
		{
			hm.put("status", "Success");
			hm.put("statusCode", "S");
			hm.put("message", "Lending request added publically");
		}
		else
		{
			hm.put("status", "Fail");
			hm.put("statusCode", "F");
			hm.put("message", "Loan not reqeusted, something went wrong");
		}
		
		return new ResponseEntity<Map>(hm,HttpStatus.OK);
	}
	
	@PostMapping("/public/borrowrequest/delete")
	public ResponseEntity<Map> deleteBorrowRequestPublic(@RequestBody HashMap<String,String> payload) {
		
		announcementsService.deleteBorrowRequestPublic(payload);
		Map hm = new HashMap();
		hm.put("status", "Success");
		hm.put("statusCode", "S");
		hm.put("message", "Complaint Deleted Successfully");
		
		return new ResponseEntity<Map>(hm, HttpStatus.OK);
	}
	
	@PostMapping("/public/lendrequest/delete")
	public ResponseEntity<Map> deleteLendRequestPublic(@RequestBody HashMap<String,String> payload) {
		
		announcementsService.deleteLendRequestPublic(payload);
		Map hm = new HashMap();
		hm.put("status", "Success");
		hm.put("statusCode", "S");
		hm.put("message", "Complaint Deleted Successfully");
		
		return new ResponseEntity<Map>(hm, HttpStatus.OK);
	}
	
	
	
}
