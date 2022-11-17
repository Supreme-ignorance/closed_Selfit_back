package com.ssafy.health.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class JwtUtil {

    private static final String SALT = "SSAFIT";

    //토큰 생성
    public String createToken(String claimId, String data) throws UnsupportedEncodingException {
        return Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                .claim(claimId, data)
                .signWith(SignatureAlgorithm.HS256, SALT.getBytes("UTF-8"))
                .compact();
    }

    public void valid(String token) throws Exception {
        System.out.println(token);
        Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(token).getBody();
    }
}
