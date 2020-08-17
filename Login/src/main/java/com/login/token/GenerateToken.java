package com.login.token;

import java.security.Key;
import java.util.Date;
import java.util.Random;

import io.jsonwebtoken.*;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.SignatureAlgorithm;

public class GenerateToken {
	public String[] createJWT(String id, String issuer, String subject, String role, long ttlMillis) {
		SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		Random random = new Random();
		String secretKey = id + Integer.toString(random.nextInt(1000));

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);

		Key signingKey = null;
		try {

			signingKey = new SecretKeySpec(apiKeySecretBytes, algorithm.getJcaName());
		} catch (Exception e) {
			System.out.println("Exception while generating key " + e.getMessage());
		}

		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(date).setSubject(subject).setIssuer(issuer)
				.setPayload(role).signWith(algorithm, signingKey);


		if (ttlMillis >= 0) {
			long expMillis = millis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		String[] tokenInfo = { builder.compact(), secretKey };
		return tokenInfo;

	}
}
