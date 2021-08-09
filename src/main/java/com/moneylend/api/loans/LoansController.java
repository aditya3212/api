package com.moneylend.api.loans;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {
	//give loan
	//return loan
	//list of people who have taken loan
	//list of people whom u need to return
	//request loan
	//list of people who have requested loan
	@Autowired
	LoansService loansService;
	
	
	@PostMapping("/loans/request")
	public ResponseEntity<Map> requestLoan(@RequestBody HashMap<String,String> payload)
	{
		Map<String, Object> hm = new HashMap<String, Object>();
		int insert=loansService.requestLoan(payload);
		if(insert>0)
		{
			hm.put("status", "Success");
			hm.put("statusCode", "S");
			hm.put("message", "loan requested");
		}
		else
		{
			hm.put("status", "Fail");
			hm.put("statusCode", "F");
			hm.put("message", "Loan not reqeusted, something went wrong");
		}
		
		return new ResponseEntity<Map>(hm,HttpStatus.OK);
	}
	
	@PostMapping("/loans/give")
	public ResponseEntity<Map> giveLoan(@RequestBody HashMap<String,String> payload)
	{
		Map<String, Object> hm = new HashMap<String, Object>();
		int insert=loansService.giveLoan(payload);
		if(insert>0)
		{
			hm.put("status", "Success");
			hm.put("statusCode", "S");
			hm.put("message", "loan given successfull");
		}
		else
		{
			hm.put("status", "Fail");
			hm.put("statusCode", "F");
			hm.put("message", "Loan not given successfully");
		}
		
		return new ResponseEntity<Map>(hm,HttpStatus.OK);
	}
	
	@PostMapping("/loans/return")
	public ResponseEntity<Map> returnLoan(@RequestBody HashMap<String,String> payload)
	{
		Map<String, Object> hm = new HashMap<String, Object>();
		int insert=loansService.returnLoan(payload);
		if(insert>0)
		{
			hm.put("status", "Success");
			hm.put("statusCode", "S");
			hm.put("message", "loan repaid successfull");
		}
		else
		{
			hm.put("status", "Fail");
			hm.put("statusCode", "F");
			hm.put("message", "Loan repaid but not updated please try again");
		}
		
		return new ResponseEntity<Map>(hm,HttpStatus.OK);
	}

}
