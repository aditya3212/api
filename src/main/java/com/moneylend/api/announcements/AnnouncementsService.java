package com.moneylend.api.announcements;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public interface AnnouncementsService {
	
	public int addBorrowRequestPublic(HashMap<String, String> payload);
	
	public int addLendRequestPublic(HashMap<String, String> payload);
	
	public void deleteBorrowRequestPublic(HashMap<String, String> payload);
	
	public void deleteLendRequestPublic(HashMap<String, String> payload);
	
	public List<LinkedHashMap<String, String>> getBorrowRequestPublic();
	
	public List<LinkedHashMap<String, String>> getLendRequestPublic();
	
	public List<LinkedHashMap<String, String>> getLoanRequestPrivate(String email);
	
	
}
