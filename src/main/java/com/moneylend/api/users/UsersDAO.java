package com.moneylend.api.users;

import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;

public interface UsersDAO {
	public boolean addUser(JSONObject json, String userImage); 
	public boolean validateContact(String searchParam, String type);
	
}
