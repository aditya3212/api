package com.moneylend.api.loans;

import java.util.HashMap;

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

}
