package com.moneylend.api.announcements;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementsServiceImpl implements AnnouncementsService {
	
	@Autowired
	AnnouncementsDAO announcementsDAO;
	
	@Override
	public int addBorrowRequestPublic(HashMap<String, String> payload) {
		// TODO Auto-generated method stub
		return announcementsDAO.addBorrowRequestPublic(payload);
		//return null;
	}
	
	@Override
	public int addLendRequestPublic(HashMap<String, String> payload) {
		// TODO Auto-generated method stub
		return announcementsDAO.addLendRequestPublic(payload);
		//return null;
	}
	
	@Override//for deleting borrow request public
	public void deleteBorrowRequestPublic(HashMap<String, String> payload) {
		// TODO Auto-generated method stub
		String borrowRequestId=payload.get("complaintId");
		String userId=payload.get("userId");
//		String userType=payload.get("userType");
		announcementsDAO.deleteBorrowRequestPublic(borrowRequestId, userId);
	}
	
	@Override//for deleting Lend request public
	public void deleteLendRequestPublic(HashMap<String, String> payload) {
		// TODO Auto-generated method stub
		String lendRequestId=payload.get("complaintId");
		String userId=payload.get("userId");
//		String userType=payload.get("userType");
		announcementsDAO.deleteLendRequestPublic(lendRequestId, userId);
	}
	
	
}
