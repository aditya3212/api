package com.moneylend.api.loans;

import java.util.HashMap;

public interface LoansDAO {
	
	public int requestLoan(HashMap<String, String> hm);
	
	public int giveLoan(HashMap<String, String> hm);
	
	public int returnLoan(HashMap<String, String> hm);
	
	
	
}
