package com.login.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.DAO.TokenDAO;
import com.login.service.interfaces.TokenService;

@Service("tokenService")  
public class TokenServiceImpl implements TokenService{
	
    @Autowired  
    private TokenDAO tokenDAO;  
	
	public void saveUserEmail(String email, int adminId) {
		  tokenDAO.saveUserEmail(email, adminId);
	}

	public boolean updateToken(String email, String authenticationToken, String secretKey) {
		return tokenDAO.updateToken(email, authenticationToken, secretKey);
	}

	public int getTokenDetail(String email) {
		return tokenDAO.getTokenDetail(email);
	}

	public int tokenAuth(String token, int emailId) {
		 return tokenDAO.tokenAuth(token, emailId);  
	}

}
