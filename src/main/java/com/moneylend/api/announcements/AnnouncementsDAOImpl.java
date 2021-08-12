package com.moneylend.api.announcements;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AnnouncementsDAOImpl implements AnnouncementsDAO {
	
	private EntityManager entityManager;
	Logger logger = LoggerFactory.getLogger(AnnouncementsDAOImpl.class);
	
	@Autowired
	public AnnouncementsDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	
	@Override
	public int addBorrowRequestPublic(HashMap<String, String> payload)
	{
		
		logger.info("Inside add borrow request public DAOIMPl\n\n ");
		String userId=String.valueOf(payload.get("userId"));
		//String to=String.valueOf(payload.get("to"));
		String duration = String.valueOf(payload.get("duration"));
		String amount = String.valueOf(payload.get("amount"));
		String interestRate = String.valueOf(payload.get("interestRate"));
		
		//optional parameter/search parameter
		String purpose = payload.containsKey("purpose") ? String.valueOf(payload.get("purpose")) : "NULL";
		
		String query="insert into borrow_request_public\r\n"
				+ " (requested_by,amount,purpose,interest_rate,repayment_duration)\r\n"
				+ " value (?,?,?,?,?) ";
		
		int trnInserted = entityManager.createNativeQuery(query).setParameter(1, userId)
				.setParameter(2, amount).setParameter(3, purpose).setParameter(4, interestRate)
				.setParameter(5, duration)
				.executeUpdate();
		
		return trnInserted;
	}
	
	@Override
	public int addLendRequestPublic(HashMap<String, String> payload)
	{
		logger.info("Inside add lend request public DAOIMPl\n ");
		String userId=String.valueOf(payload.get("userId"));
//		String to=String.valueOf(payload.get("to"));
		String duration = String.valueOf(payload.get("duration"));
		String amount = String.valueOf(payload.get("amount"));
		String interestRate = String.valueOf(payload.get("interestRate"));
		
		//optional parameter/search parameter
//		String purpose = payload.containsKey("purpose") ? String.valueOf(payload.get("purpose")) : "NULL";
		
		String query="insert into lend_request_public \r\n"
				+ " (lender_id,amount,interest_rate,repayment_duration)\r\n"
				+ " value (?,?,?,?) ";
		
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


		String queryString = "select concat(u.first_name,' ',COALESCE(u.last_name,'')) as name ,\r\n"
				+ "u.email_id as email, u.phone_no, coalesce(u.address,'') as address,\r\n"
				+ " coalesce(u.city,'') as city,\r\n"
				+ "coalesce(u.pincode,'') as pincode, coalesce(u.image_url,'') as userImage,\r\n"
				+ "lr.amount, lr.interest_rate, lr.repayment_duration, \r\n"
				+ "date_format(lr.created_at,'%d %M %Y') as createdAt, \r\n"
				+ "date_format(lr.updated_at,'%d %M %Y') as updatedAt, lr.id as lendId\r\n"
				+ "from lend_request_public lr\r\n"
				+ "join users u on u.email_id=lr.lender_id\r\n"
				+ "where (datediff(NOW(),lr.updated_at)<=15) and lr.deleted_at is null\r\n"
				+ "order by lr.updated_at desc";
		
//		queryString="select "

		Query query = currentSession.createNativeQuery(queryString);

		List<Object> result = query.getResultList();

		//LinkedHashMap<String, Object> finalHashMap = new LinkedHashMap<String, Object>();
		
		return result;
	}
	
	//not implemented yet, to be implemented soon
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

		String queryString = "select concat(u.first_name,' ',COALESCE(u.last_name,'')) as name ,\r\n"
				+ "u.email_id as email, u.phone_no, coalesce(u.address,'') as address,\r\n"
				+ " coalesce(u.city,'') as city,\r\n"
				+ "coalesce(u.pincode,'') as pincode, coalesce(u.image_url,'') as userImage,\r\n"
				+ "br.amount, br.interest_rate, br.repayment_duration, \r\n"
				+ "date_format(br.created_at,'%d %M %Y') as createdAt, \r\n"
				+ "date_format(br.updated_at,'%d %M %Y') as updatedAt, \r\n"
				+" br.purpose, br.id as borrowId "
				+ "from borrow_request_public br\r\n"
				+ "join users u on u.email_id=br.requested_by\r\n"
				+ "where (datediff(NOW(),br.updated_at)<=15) and br.deleted_at is null\r\n"
				+ "order by br.updated_at desc";
		
//		queryString="select "

		Query query = currentSession.createNativeQuery(queryString);

		List<Object> result = query.getResultList();

		//LinkedHashMap<String, Object> finalHashMap = new LinkedHashMap<String, Object>();
		
		return result;
	}
	
	//not implemented yet to be implemented soon
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
	
	
	@Override
	public List<Object> getLoanRequestPrivate(String email)
	{
		Session currentSession = entityManager.unwrap(Session.class);

		String queryString = "select concat(u.first_name,' ',COALESCE(u.last_name,'')) as requester_name ,\r\n"
				+ "u.email_id as requester_email, u.phone_no, coalesce(u.address,'') as address,\r\n"
				+ "coalesce(u.city,'') as city,\r\n"
				+ "coalesce(u.pincode,'') as pincode, coalesce(u.image_url,'') as userImage,\r\n"
				+ "lr.id as loan_id, lr.amount, lr.interest_rate,\r\n"
				+ "lr.duration, lr.purpose, lr.request_to ,\r\n"
				+ "date_format(lr.created_at,'%d %M %Y') as createdAt , \r\n"
				+ "date_format(lr.updated_at,'%d %M %Y') as updatedAt \r\n"
				+ "from loan_request lr\r\n"
				+ "join users u on u.email_id=lr.request_from\r\n"
				+ "where lr.request_to='" + email + "' \r\n"
				+ "and lr.responded='0' and lr.deleted_at is null\r\n"
				+ "order by lr.created_at desc ";
		
		Query query = currentSession.createNativeQuery(queryString);

		List<Object> result = query.getResultList();

		//LinkedHashMap<String, Object> finalHashMap = new LinkedHashMap<String, Object>();
		return result;
	}
	
	
}
