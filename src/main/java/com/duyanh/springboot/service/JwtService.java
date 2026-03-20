package com.duyanh.springboot.service;

import com.duyanh.springboot.model.User;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtService {

    private String secretKey = "9f3a7c2d8e6b1a4f5d92c3e7b8a1f0d4c6e2b9a7f3d1c5e8a4b2c7d9e6f1a3b8";

    public String generationAccessToken(User user) {

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        Date issueTIme = new Date();
        Date expireTime = Date.from(issueTIme.toInstant().plus(30, ChronoUnit.MINUTES));

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issueTime(issueTIme)
                .expirationTime(expireTime)
                .build();

        Payload payload = new Payload(claimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header,payload);
        try {
            jwsObject.sign(new MACSigner(secretKey));
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        return jwsObject.serialize();
    }

    public String generationRefreshToken(User user) {

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        Date issueTIme = new Date();
        Date expireTime = Date.from(issueTIme.toInstant().plus(30, ChronoUnit.DAYS));

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issueTime(issueTIme)
                .expirationTime(expireTime)
                .build();

        Payload payload = new Payload(claimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header,payload);
        try {
            jwsObject.sign(new MACSigner(secretKey));
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        return jwsObject.serialize();
    }
}
