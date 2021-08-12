package com.moneylend.api.announcements;

import java.util.HashMap;
import java.util.List;

public interface AnnouncementsDAO {
	
	public int addBorrowRequestPublic(HashMap<String, String> payload);
	
	public int addLendRequestPublic(HashMap<String, String> payload);
	
	public List<Object> getLendRequestPublic();
	
	public List<Object> getBorrowRequestPublic();
	
	public List<Object> filterBorrowRequestPublic(HashMap<String,String> payload);
	
	public List<Object> filterLendRequestPublic(HashMap<String,String> payload);
	
	public void deleteBorrowRequestPublic(String borrowRequestId, String userId);
	
	public void deleteLendRequestPublic(String lendRequestId, String borrowRequestId);
	
	public List<Object> getLoanRequestPrivate(String email);
	
}
