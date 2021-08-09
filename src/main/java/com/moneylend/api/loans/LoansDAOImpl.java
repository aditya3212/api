package com.moneylend.api.loans;

import java.util.HashMap;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class LoansDAOImpl implements LoansDAO {
	
	private EntityManager entityManager;

	@Autowired
	public LoansDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public int requestLoan(HashMap<String, String> payload)
	{
		String from=String.valueOf(payload.get("from"));
		String to=String.valueOf(payload.get("to"));
		String duration = String.valueOf(payload.get("duration"));
		String amount = String.valueOf(payload.get("amount"));
		String interestRate = String.valueOf(payload.get("interestRate"));
		
		//optional parameter/search parameter
		String purpose = payload.containsKey("purpose") ? String.valueOf(payload.get("purpose")) : "NULL";
		
		String query="insert into loan_request\r\n"
				+ "(request_from, request_to,purpose,duration,amount,interest_rate)\r\n"
				+ "value (?,?,?,?,?,?)";
		
		int trnInserted = entityManager.createNativeQuery(query).setParameter(1, from)
				.setParameter(2, to).setParameter(3, purpose).setParameter(4, duration)
				.setParameter(5, amount).setParameter(6, interestRate)
				.executeUpdate();
		
		return trnInserted;
	}
	
	@Override
	public int giveLoan(HashMap<String, String> payload)
	{
		//compulsory parameter
		String from=String.valueOf(payload.get("from"));
		String to=String.valueOf(payload.get("to"));
		String duration = String.valueOf(payload.get("duration"));
		String amount = String.valueOf(payload.get("amount"));
		String interestRate = String.valueOf(payload.get("interestRate"));
		String paymentSuccessfull=String.valueOf(payload.get("paymentSuccessfull"));
		
		//optional parameter
		String purpose = payload.containsKey("purpose") ? String.valueOf(payload.get("purpose")) : "NULL";
		String transactionId = payload.containsKey("transactionId") ? String.valueOf(payload.get("transactionId")) : "NULL";
		String paymentMedium = payload.containsKey("paymentMedium") ? String.valueOf(payload.get("paymentMedium")) : "NULL";
		String commentText = payload.containsKey("commentText") ? String.valueOf(payload.get("commentText")) : "NULL";
		
		String query="insert into loan_given\r\n"
				+ "(from_id , to_id , purpose, transaction_id , payment_successfull , \r\n"
				+ "original_principal , new_principal , interest_rate ,"
				+ " payment_medium, transaction_date, \r\n"
				+ "tentative_return_date, new_tentative_return_date, interest_calculator_date )\r\n"
				+ "value (?, ?, ?, ?, ?,\r\n"
				+ "?, ?, ?, ?, ?, NOW(), \r\n"
				+ "date_add(NOW(), interval " + duration + " MONTH), "
				+ "date_add(NOW(), interval "+ duration + " MONTH), NOW())	";
		
		int trnInserted = entityManager.createNativeQuery(query).setParameter(1, from)
				.setParameter(2, to).setParameter(3, purpose).setParameter(4, transactionId)
				.setParameter(5, paymentSuccessfull).setParameter(6, amount)
				.setParameter(7, amount).setParameter(8, interestRate)
				.setParameter(9, paymentMedium).setParameter(9, commentText)
				.executeUpdate();
		
		return trnInserted;
	}
	
	@Override
	public int returnLoan(HashMap<String, String> payload)
	{
		String loanId=String.valueOf(payload.get("loanId"));
		String from=String.valueOf(payload.get("from"));
		String to=String.valueOf(payload.get("to"));
		String amount = String.valueOf(payload.get("amount"));
		String transactionId=String.valueOf(payload.get("transactionId"));
		String paymentMedium=String.valueOf(payload.get("paymentMedium"));
		String paymentSuccesfull = String.valueOf(payload.get("paymentSuccesfull"));
		//optional parameter
		String commentText = payload.containsKey("commentText") ? String.valueOf(payload.get("commentText")) : "NULL";
		String query="insert into loan_repayment\r\n"
				+ "(loan_id, from_id, to_id, amount, transaction_id, \r\n"
				+ "payment_medium, payment_successfull, comment_text)\r\n"
				+ "value (?, ?, ?, ?, ?, \r\n"
				+ " ?, ?, ?	)";
		
		int trnInserted = entityManager.createNativeQuery(query).setParameter(1, loanId)
				.setParameter(2, from).setParameter(3, to).setParameter(4, amount)
				.setParameter(5, transactionId).setParameter(6, paymentMedium)
				.setParameter(7, paymentSuccesfull).setParameter(8, commentText)
				.executeUpdate();
		
		//after returning the loan we also need to update the loan_table,
		//we need to update remaining amount
		return trnInserted;
	}
	
	
}
