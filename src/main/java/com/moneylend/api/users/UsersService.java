package com.moneylend.api.users;

import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;

public interface UsersService {

	public Object validateContact(String searchParam, String type) ;
	public boolean addUser(JSONObject json, MultipartFile usersImage);
}
