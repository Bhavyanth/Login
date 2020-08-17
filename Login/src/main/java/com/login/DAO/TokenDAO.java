package com.login.DAO;

public interface TokenDAO {
	public void saveUserEmail(String email , int adminId);  
    
    public boolean updateToken(String email , String authToken , String secretKey);  
      
    public int getTokenDetail(String email );  
  
    public int tokenAuth(String token , int emailId);  
}
