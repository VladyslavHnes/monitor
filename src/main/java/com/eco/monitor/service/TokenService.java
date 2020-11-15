package com.eco.monitor.service;

import com.eco.monitor.config.ConfigurationManager;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Service
public class TokenService {

    @Autowired
    private ConfigurationManager configurationManager;

    public String createJWT(String userId) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(configurationManager.getJwtSecret());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        return Jwts.builder()
                .claim("scope", "user")
                .setIssuedAt(now)
                .setIssuer(userId)
                .setExpiration(new Date(nowMillis + 7200000))
                .signWith(signatureAlgorithm, signingKey)
                .compact();
    }

    public Integer decodeJWT(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(configurationManager.getJwtSecret()))
                .parseClaimsJws(jwt).getBody();
        return Integer.valueOf(claims.getIssuer());
    }
}
