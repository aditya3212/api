package com.moneylend.api.users;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UsersDAOImpl implements UsersDAO {
	
	private EntityManager entityManager;

	@Autowired
	public UsersDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public boolean addUser(JSONObject json, String userImage) {
		// TODO Auto-generated method stub
		//logger.info("add college started\n\n");
		Session currentSession = entityManager.unwrap(Session.class);
		

		String address=json.containsKey("address")?String.valueOf(json.get("address")):null;
		String city=json.containsKey("city")?String.valueOf(json.get("city")):null;
		//String state=json.containsKey("state")?String.valueOf(json.get("state")):null;
		String pincode=json.containsKey("pincode")?String.valueOf(json.get("pincode")):null;
		String lastName=json.containsKey("lastName")?String.valueOf(json.get("lastName")):null;
		String userId=json.containsKey("userId")?String.valueOf(json.get("userId")):null;
		String aadharNo=json.containsKey("aadharNo")?String.valueOf(json.get("aadharNo")):null;
		String panNo=json.containsKey("panNo")?String.valueOf(json.get("panNo")):null;
		
		String firstName = String.valueOf(json.get("firstName"));
		String phoneNo=String.valueOf(json.get("phoneNo"));
		String email=String.valueOf(json.get("email"));
		String password=String.valueOf(json.get("password"));
		//String userId=String.valueOf(json.get("userId"));
		
		//String encryptedPassword=CommonFunctions.getBcrypt(password);		
		String query="";
		NativeQuery nquery;
		
		
		query = "insert into users \r\n"
				+ "(first_name,last_name,user_id,phone_no,\r\n"
				+ "email_id,aadhar_no,pan_no,address,"
				+ "city,pincode,password,image_url) \r\n"
				+ "value (?,?,?, ?,?,?, ?,?,?, ?,?,?)";
			
		
		int trnInserted = entityManager.createNativeQuery(query).setParameter(1, firstName)
				.setParameter(2, lastName).setParameter(3, userId).setParameter(4, phoneNo)
				.setParameter(5, email).setParameter(6, aadharNo).setParameter(7, panNo)
				.setParameter(8, address).setParameter(9, city).setParameter(10, pincode)
				.setParameter(11, password).setParameter(12, userImage)
				.executeUpdate();
		
				
		return true;

	}
	
	@Override
	public boolean validateContact(String searchParam, String type) {
		Session currentSession = entityManager.unwrap(Session.class);

		String queryString = "";
		boolean outcome;

//		queryString = "SELECT count(id) FROM users\n" + "WHERE email <> " + "'" + "'" + " AND " + type + " = " + "'"
//				+ searchParam + "'" + " AND deleted_at IS NULL " 
//				+ "and deleted_at is null and is_archived = 0 and is_active = 1";
		
		queryString="SELECT COUNT(id) FROM users WHERE "+type+"='"+searchParam+"' AND deleted_at IS NULL AND is_active=1";

		NativeQuery nativeHostelQuery = currentSession.createNativeQuery(queryString);
		List<BigInteger> result = nativeHostelQuery.getResultList();
		int count = 0;
		for (BigInteger val : result) {
			count = val.intValue();
		}

		outcome = count > 0;
		//logger.info("outcome");
		return outcome;
	}
}
