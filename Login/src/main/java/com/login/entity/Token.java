package com.login.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="Token")

public class Token {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name= "Token_id")
	private int tokenId;
	
	@Column (name= "User_ID",unique = true)
	private int userId;
	
	@Column (name= "authToken")
	private String authToken;
	
	@Column (name= "SecretKey")
	private String secretKey;
	
	@Column (name= "EmailID")
	private String emailId;
	
	public Token(){}

	public Token(int tokenId, int userId, String authToken, String secretKey, String emailId) {
		super();
		this.tokenId = tokenId;
		this.userId = userId;
		this.authToken = authToken;
		this.secretKey = secretKey;
		this.emailId = emailId;
	}

	public int getTokenId() {
		return tokenId;
	}

	public void setTokenId(int tokenId) {
		this.tokenId = tokenId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "Token [tokenId=" + tokenId + ", userId=" + userId + ", authToken=" + authToken + ", secretKey="
				+ secretKey + ", emailId=" + emailId + "]";
	}
	
	
}
