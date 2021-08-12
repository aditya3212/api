package com.moneylend.api.loans;

import java.util.HashMap;

public interface LoansService {
	public int requestLoan(HashMap<String,String> payload);
	
	public int giveLoan(HashMap<String,String> payload);
	
	public int returnLoan(HashMap<String,String> payload);
	
	public Object getLoanGiven(String email);
	
	public Object getLoanTaken(String email);
	
}
