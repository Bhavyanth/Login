package com.login.service.interfaces;

public interface TokenService {
	public void saveUserEmail(String email, int adminId);

	public boolean updateToken(String email, String authenticationToken, String secretKey);

	public int getTokenDetail(String email);

	public int tokenAuth(String token, int emailId);
}
