package com.moneylend.api.users;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	UsersDAO usersDAO;
	
	@Override
	public boolean addUser(JSONObject json, MultipartFile usersImage) {
		
		
		System.out.println("In users add service layer\n\n");
		
		String userImageUrl=null;
//		String tenantUrl=String.valueOf(json.get("tenantUrl"));
		String email=String.valueOf(json.get("email"));
		String phoneNo=String.valueOf(json.get("phoneNo"));
		
		boolean flag1=usersDAO.validateContact(email,"email_id");
		boolean flag2=usersDAO.validateContact(phoneNo,"phone_no");
		
		if(flag1==true||flag2==true)
		{  
			return false;
		}
		//collegeImageUrl=uploadFileToBucket(collegeImage,tenantUrl);
		usersDAO.addUser(json,userImageUrl);
		
		return true;
	}
	//for checking if given contact email and phone already exist or not
	@Override
	public Object validateContact(String searchParam, String type) {
		
		HashMap<String, String> hm = new HashMap<String, String>();
		
		boolean flag=usersDAO.validateContact(searchParam, type);
		if(flag==true) {
			hm.put("emailExist", "Yes");
			hm.put("message", "This "+type+" already exist");
		}else {
			hm.put("emailExist", "No");
			hm.put("message", "This "+type+" does not exist");
		}
		return hm;
		
	}
		
}
