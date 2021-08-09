package com.moneylend.api.announcements;

import java.util.HashMap;

public interface AnnouncementsService {
	
	public int addBorrowRequestPublic(HashMap<String, String> payload);
	
	public int addLendRequestPublic(HashMap<String, String> payload);
	
	public void deleteBorrowRequestPublic(HashMap<String, String> payload);
	
	public void deleteLendRequestPublic(HashMap<String, String> payload);
	
	
}
