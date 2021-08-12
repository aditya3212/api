package com.moneylend.api.loans;

import java.util.HashMap;
import java.util.List;

public interface LoansDAO {
	
	public int requestLoan(HashMap<String, String> hm);
	
	public int giveLoan(HashMap<String, String> hm);
	
	public int returnLoan(HashMap<String, String> hm);
	
	public List<Object> getLoanGiven(String email);
	
	public List<Object> getLoanTaken(String email);
	
}
