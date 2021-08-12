package com.moneylend.api.loans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoansServiceImpl implements LoansService {
	
	@Autowired
	LoansDAO loansDAO;
	
	
	@Override
	public int requestLoan(HashMap<String, String> payload) {
		// TODO Auto-generated method stub
		return loansDAO.requestLoan(payload);
		//return null;
	}
	
	@Override
	public int giveLoan(HashMap<String, String> payload) {
		// TODO Auto-generated method stub
		return loansDAO.giveLoan(payload);
		//return null;
	}
	
	@Override
	public int returnLoan(HashMap<String, String> payload) {
		// TODO Auto-generated method stub
		return loansDAO.returnLoan(payload);
		//return null;
	}
	
	@Override
	public Object getLoanGiven(String email)
	{
		List<Object> loanGivenList=loansDAO.getLoanGiven(email);
		
		List res=new ArrayList();
//		List res = new ArrayList();
		for(Object obj:loanGivenList)
		{
			Object[] resObj=(Object[]) obj;
			
			LinkedHashMap<String,String> hm=new LinkedHashMap<String, String>();
			
			hm.put("name", String.valueOf(resObj[0]));
			hm.put("email", String.valueOf(resObj[1]));
			hm.put("phoneNo", String.valueOf(resObj[2]));
			hm.put("address", String.valueOf(resObj[3]));
			hm.put("city", String.valueOf(resObj[4]));
			hm.put("pincode", String.valueOf(resObj[5]));
//			hm.put("userImage",String.valueOf(resObj[6]));
			
			hm.put("loanId", String.valueOf(resObj[7]));
			hm.put("lenderId", String.valueOf(resObj[8]));
			hm.put("purpose", String.valueOf(resObj[9]));
			hm.put("commentText", String.valueOf(resObj[10]));
			
			hm.put("transactionId", String.valueOf(resObj[11]));
			hm.put("originalPrincipal", String.valueOf(resObj[12]));
			hm.put("newPrincipal", String.valueOf(resObj[13]));
			hm.put("interestRate", String.valueOf(resObj[14]));
			hm.put("amountReturned", String.valueOf(resObj[15]));
			
			hm.put("transactionDate", String.valueOf(resObj[16]));
			hm.put("tentativeReturnDate", String.valueOf(resObj[17]));
			hm.put("newTentativeReturnDate", String.valueOf(resObj[18]));
			hm.put("interestCalculatorDate", String.valueOf(resObj[19]));
			hm.put("duration", String.valueOf(resObj[20]));
			
			hm.put("createdAt", String.valueOf(resObj[21]));
			hm.put("updatedAt", String.valueOf(resObj[22]));
			
			//calcalate the new amount and return that also
			res.add(hm);
			
		}
		
		return res;
	}
	
	@Override
	public Object getLoanTaken(String email)
	{
		List<Object> loanTakenList=loansDAO.getLoanTaken(email);
		List res=new ArrayList();
//		List res = new ArrayList();
		for(Object obj:loanTakenList)
		{
			Object[] resObj=(Object[]) obj;
			
			LinkedHashMap<String,String> hm=new LinkedHashMap<String, String>();
			
			hm.put("name", String.valueOf(resObj[0]));
			hm.put("email", String.valueOf(resObj[1]));
			hm.put("phoneNo", String.valueOf(resObj[2]));
			hm.put("address", String.valueOf(resObj[3]));
			hm.put("city", String.valueOf(resObj[4]));
			hm.put("pincode", String.valueOf(resObj[5]));
//			hm.put("userImage",String.valueOf(resObj[6]));
			
			hm.put("loanId", String.valueOf(resObj[7]));
			hm.put("borrowerId", String.valueOf(resObj[8]));
			hm.put("purpose", String.valueOf(resObj[9]));
			hm.put("commentText", String.valueOf(resObj[10]));
			
			hm.put("transactionId", String.valueOf(resObj[11]));
			hm.put("originalPrincipal", String.valueOf(resObj[12]));
			hm.put("newPrincipal", String.valueOf(resObj[13]));
			hm.put("interestRate", String.valueOf(resObj[14]));
			hm.put("amountReturned", String.valueOf(resObj[15]));
			
			hm.put("transactionDate", String.valueOf(resObj[16]));
			hm.put("tentativeReturnDate", String.valueOf(resObj[17]));
			hm.put("newTentativeReturnDate", String.valueOf(resObj[18]));
			hm.put("interestCalculatorDate", String.valueOf(resObj[19]));
			hm.put("duration", String.valueOf(resObj[20]));
			
			hm.put("createdAt", String.valueOf(resObj[21]));
			hm.put("updatedAt", String.valueOf(resObj[22]));
			
			//calcalate the new amount and return that also
			res.add(hm);
		}
		return res;
	}
	
	

}
