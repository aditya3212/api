package com.moneylend.api.announcements;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class AnnouncementsDAOImpl implements AnnouncementsDAO {
	
	private EntityManager entityManager;

	@Autowired
	public AnnouncementsDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	
	@Override
	public int addBorrowRequestPublic(HashMap<String, String> payload)
	{
		String userId=String.valueOf(payload.get("userId"));
		//String to=String.valueOf(payload.get("to"));
		String duration = String.valueOf(payload.get("duration"));
		String amount = String.valueOf(payload.get("amount"));
		String interestRate = String.valueOf(payload.get("interestRate"));
		
		//optional parameter/search parameter
		String purpose = payload.containsKey("purpose") ? String.valueOf(payload.get("purpose")) : "NULL";
		
		String query="insert into borrow_request_public\r\n"
				+ "(requested_by,amount,purpose,interest_rate,repayment_duration)\r\n"
				+ "value (?,?,?,?,?)";
		
		int trnInserted = entityManager.createNativeQuery(query).setParameter(1, userId)
				.setParameter(2, amount).setParameter(3, purpose).setParameter(4, interestRate)
				.setParameter(5, duration)
				.executeUpdate();
		
		return trnInserted;
	}
	
	@Override
	public int addLendRequestPublic(HashMap<String, String> payload)
	{
		String userId=String.valueOf(payload.get("userId"));
//		String to=String.valueOf(payload.get("to"));
		String duration = String.valueOf(payload.get("duration"));
		String amount = String.valueOf(payload.get("amount"));
		String interestRate = String.valueOf(payload.get("interestRate"));
		
		//optional parameter/search parameter
		String purpose = payload.containsKey("purpose") ? String.valueOf(payload.get("purpose")) : "NULL";
		
		String query="insert into lend_request_public\r\n"
				+ "(lender_id,amount,interest_rate,repayment_duration)\r\n"
				+ "value (?,?,?,?,?)";
		
		int trnInserted = entityManager.createNativeQuery(query).setParameter(1, userId)
				.setParameter(2, amount).setParameter(3, interestRate).setParameter(4, duration)
				.executeUpdate();
		
		return trnInserted;
	}
	
	@Override
	public void deleteBorrowRequestPublic(String borrowRequestId, String userId) {

		Session currentSession = entityManager.unwrap(Session.class);

		String updateQuery = "update borrow_request_public set deleted_at=NOW() "
				+  "where requested_by='" + userId + "'\r\n"
				+ "and id='" + borrowRequestId + "'" ;    

		entityManager.createNativeQuery(updateQuery).executeUpdate();

	}
	
	@Override
	public void deleteLendRequestPublic(String lendRequestId, String userId) {

		Session currentSession = entityManager.unwrap(Session.class);

		String updateQuery = "update lend_request_public set deleted_at=NOW() "
				+ "where id='" + lendRequestId + "' and lender_id='" + userId + "'";    

		entityManager.createNativeQuery(updateQuery).executeUpdate();

		//				currentSession.close();
	}
	
	@Override
	public List<Object> getLendRequestPublic() {

		Session currentSession = entityManager.unwrap(Session.class);


		String queryString = "SELECT bc.id,bc.remarks,bc.created_by,u.name AS commentPostedByName ,"
				+ "u.image AS commentPostedByImage,DATE_FORMAT(bc.created_at,\"%D %b %Y, %h:%i %p\") as created_at, "
				+ "DATE_FORMAT(bc.updated_at,\"%D %b %Y, %h:%i %p\") as updated_at, c.url AS tenantUrl\r\n"
				+ " FROM bulletin_comments bc\r\n"
				+ "JOIN users u ON u.id=bc.created_by \r\n"
				+ "JOIN colleges c ON c.id=u.college_id\r\n"
				+ "WHERE bc.bulletin_id='"+"' AND bc.is_deleted='0'\r\n"
				+ "ORDER BY bc.id desc";
		
//		queryString="select "

		Query query = currentSession.createNativeQuery(queryString);

		List<Object> result = query.getResultList();

		//LinkedHashMap<String, Object> finalHashMap = new LinkedHashMap<String, Object>();
		
		return result;
	}
	
	@Override
	public List<Object> filterLendRequestPublic(HashMap<String,String> payload) {

		Session currentSession = entityManager.unwrap(Session.class);


		String queryString = "SELECT bc.id,bc.remarks,bc.created_by,u.name AS commentPostedByName ,"
				+ "u.image AS commentPostedByImage,DATE_FORMAT(bc.created_at,\"%D %b %Y, %h:%i %p\") as created_at, "
				+ "DATE_FORMAT(bc.updated_at,\"%D %b %Y, %h:%i %p\") as updated_at, c.url AS tenantUrl\r\n"
				+ " FROM bulletin_comments bc\r\n"
				+ "JOIN users u ON u.id=bc.created_by \r\n"
				+ "JOIN colleges c ON c.id=u.college_id\r\n"
				+ "WHERE bc.bulletin_id='"+"' AND bc.is_deleted='0'\r\n"
				+ "ORDER BY bc.id desc";
		
//		queryString="select "

		Query query = currentSession.createNativeQuery(queryString);

		List<Object> result = query.getResultList();

		//LinkedHashMap<String, Object> finalHashMap = new LinkedHashMap<String, Object>();
		
		return result;
	}
	
	
	
	@Override
	public List<Object> getBorrowRequestPublic() {

		Session currentSession = entityManager.unwrap(Session.class);


		String queryString = "SELECT bc.id,bc.remarks,bc.created_by,u.name AS commentPostedByName ,"
				+ "u.image AS commentPostedByImage,DATE_FORMAT(bc.created_at,\"%D %b %Y, %h:%i %p\") as created_at, "
				+ "DATE_FORMAT(bc.updated_at,\"%D %b %Y, %h:%i %p\") as updated_at, c.url AS tenantUrl\r\n"
				+ " FROM bulletin_comments bc\r\n"
				+ "JOIN users u ON u.id=bc.created_by \r\n"
				+ "JOIN colleges c ON c.id=u.college_id\r\n"
				+ "WHERE bc.bulletin_id='"+"' AND bc.is_deleted='0'\r\n"
				+ "ORDER BY bc.id desc";
		
//		queryString="select "

		Query query = currentSession.createNativeQuery(queryString);

		List<Object> result = query.getResultList();

		//LinkedHashMap<String, Object> finalHashMap = new LinkedHashMap<String, Object>();
		
		return result;
	}
	
	@Override
	public List<Object> filterBorrowRequestPublic(HashMap<String,String> payload) {

		Session currentSession = entityManager.unwrap(Session.class);


		String queryString = "SELECT bc.id,bc.remarks,bc.created_by,u.name AS commentPostedByName ,"
				+ "u.image AS commentPostedByImage,DATE_FORMAT(bc.created_at,\"%D %b %Y, %h:%i %p\") as created_at, "
				+ "DATE_FORMAT(bc.updated_at,\"%D %b %Y, %h:%i %p\") as updated_at, c.url AS tenantUrl\r\n"
				+ " FROM bulletin_comments bc\r\n"
				+ "JOIN users u ON u.id=bc.created_by \r\n"
				+ "JOIN colleges c ON c.id=u.college_id\r\n"
				+ "WHERE bc.bulletin_id='"+"' AND bc.is_deleted='0'\r\n"
				+ "ORDER BY bc.id desc";
		
//		queryString="select "

		Query query = currentSession.createNativeQuery(queryString);

		List<Object> result = query.getResultList();

		//LinkedHashMap<String, Object> finalHashMap = new LinkedHashMap<String, Object>();
		
		return result;
	}
	
	
}
