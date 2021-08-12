package com.moneylend.api.announcements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementsServiceImpl implements AnnouncementsService {
	
	@Autowired
	AnnouncementsDAO announcementsDAO;
	
	Logger logger = LoggerFactory.getLogger(AnnouncementsServiceImpl.class);
	
	@Override
	public int addBorrowRequestPublic(HashMap<String, String> payload) {
		// TODO Auto-generated method stub
		logger.info("Inside add borrow request public service\n\n ");
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
		String borrowRequestId=payload.get("borrowRequestId");
		String userId=payload.get("userId");
//		String userType=payload.get("userType");
		announcementsDAO.deleteBorrowRequestPublic(borrowRequestId, userId);
	}
	
	@Override//for deleting Lend request public
	public void deleteLendRequestPublic(HashMap<String, String> payload) {
		String lendRequestId=payload.get("lendRequestId");
		String userId=payload.get("userId");
		announcementsDAO.deleteLendRequestPublic(lendRequestId, userId);
	}
	

	
	@Override
	public List<LinkedHashMap<String, String>> getBorrowRequestPublic() {

//		logger.info("Inside the view, comments section");
		String imagePath = "";
		List<Object> borrowRequestList = announcementsDAO.getBorrowRequestPublic();
//		List announcementsResList = new ArrayList();
//		List res = new ArrayList();
		List<LinkedHashMap<String,String>> res= new ArrayList<LinkedHashMap<String,String>>();
//		=new List<LinkedHashMap<String,String>>();
		
		for(Object obj:borrowRequestList) {
			LinkedHashMap<String,String> hm=new LinkedHashMap<String,String>();
			Object[] resObj = (Object[]) obj;
			hm.put("name", String.valueOf(resObj[0]));
			hm.put("email", String.valueOf(resObj[1]));
			hm.put("phoneNo", String.valueOf(resObj[2]));
			hm.put("address", String.valueOf(resObj[3]));
			
			hm.put("city", String.valueOf(resObj[4]));
			hm.put("pincode", String.valueOf(resObj[5]));
			//hm.put("userImage", String.valueOf(resObj[6]));
			hm.put("amount", String.valueOf(resObj[7]));
			hm.put("interestRate", String.valueOf(resObj[8]));
			hm.put("duration", String.valueOf(resObj[9]));
			hm.put("createdAt", String.valueOf(resObj[10]));
			hm.put("updatedAt", String.valueOf(resObj[11]));
//			hm.put("updatedAt", String.valueOf(resObj[11]));
			hm.put("purpose", String.valueOf(resObj[12]));
			hm.put("borrowRequestId", String.valueOf(resObj[13]));
			res.add(hm);
//			hm.put("name", String.valueOf(resObj[0]));
		}
		return res;
	}
	
	@Override
	public List<LinkedHashMap<String, String>> getLendRequestPublic() {

//		logger.info("Inside the view, comments section");
		String imagePath = "";
		List<Object> borrowRequestList = announcementsDAO.getLendRequestPublic();
//		List announcementsResList = new ArrayList();
//		List res = new ArrayList();
		List<LinkedHashMap<String,String>> res= new ArrayList<LinkedHashMap<String,String>>();
//		=new List<LinkedHashMap<String,String>>();
		
		for(Object obj:borrowRequestList) {
			LinkedHashMap<String,String> hm=new LinkedHashMap<String,String>();
			Object[] resObj = (Object[]) obj;
			hm.put("name", String.valueOf(resObj[0]));
			hm.put("email", String.valueOf(resObj[1]));
			hm.put("phoneNo", String.valueOf(resObj[2]));
			hm.put("address", String.valueOf(resObj[3]));
			
			hm.put("city", String.valueOf(resObj[4]));
			hm.put("pincode", String.valueOf(resObj[5]));
			//hm.put("userImage", String.valueOf(resObj[6]));
			hm.put("amount", String.valueOf(resObj[7]));
			hm.put("interestRate", String.valueOf(resObj[8]));
			hm.put("duration", String.valueOf(resObj[9]));
			hm.put("createdAt", String.valueOf(resObj[10]));
			hm.put("updatedAt", String.valueOf(resObj[11]));
//			hm.put("updatedAt", String.valueOf(resObj[11]));
			//hm.put("purpose", String.valueOf(resObj[12]));
			hm.put("lendRequestId", String.valueOf(resObj[12]));
			res.add(hm);
//			hm.put("name", String.valueOf(resObj[0]));
		}
		return res;
	}
	
	@Override
	public List<LinkedHashMap<String, String>> getLoanRequestPrivate(String email)
	{
		String imagePath = "";
		List<Object> borrowRequestList = announcementsDAO.getLoanRequestPrivate(email);
//		List announcementsResList = new ArrayList();
//		List res = new ArrayList();
		List<LinkedHashMap<String,String>> res= new ArrayList<LinkedHashMap<String,String>>();
//		=new List<LinkedHashMap<String,String>>();
		
		for(Object obj:borrowRequestList) {
			LinkedHashMap<String,String> hm=new LinkedHashMap<String,String>();
			Object[] resObj = (Object[]) obj;
			hm.put("name", String.valueOf(resObj[0]));
			hm.put("email", String.valueOf(resObj[1]));
			hm.put("phoneNo", String.valueOf(resObj[2]));
			hm.put("address", String.valueOf(resObj[3]));
			
			hm.put("city", String.valueOf(resObj[4]));
			hm.put("pincode", String.valueOf(resObj[5]));
			//hm.put("userImage", String.valueOf(resObj[6]));
			
			hm.put("amount", String.valueOf(resObj[7]));
			hm.put("interestRate", String.valueOf(resObj[8]));
			hm.put("duration", String.valueOf(resObj[9]));
			hm.put("createdAt", String.valueOf(resObj[10]));
			hm.put("updatedAt", String.valueOf(resObj[11]));
//			hm.put("updatedAt", String.valueOf(resObj[11]));
			//hm.put("purpose", String.valueOf(resObj[12]));
			hm.put("lendRequestId", String.valueOf(resObj[12]));
			res.add(hm);
//			hm.put("name", String.valueOf(resObj[0]));
		}
		return res;
		
	}
	
	
	
}
