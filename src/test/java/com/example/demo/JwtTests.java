package com.example.demo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Ignore;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JwtTests {

    private static final Long EXPIRATION_TIME = 1000L * 60L * 10L;

    @Ignore
    @Test
    public void build() {
        String secretKey = "secret";
        Date issuedAt = new Date();                                      // "iat" < TODAY
        Date notBefore = new Date(issuedAt.getTime());                   // "nbf" > TODAY
        Date expiresAt = new Date(issuedAt.getTime() + EXPIRATION_TIME); // "exp" > TODAY

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            String token = JWT.create()
                    //.withJWTId("jwtId")        //JWT ID          | Use of this claim is OPTIONAL. JWTの一意の識別子
                    //.withAudience("audience")  //Audience        | Use of this claim is OPTIONAL. JWTの利用者
                    //.withIssuer("issuer")      //Issuer          | Use of this claim is OPTIONAL. JWTの発行者
                    .withSubject("test")         //Subject         | Use of this claim is OPTIONAL. JWTのプリンシパル JWTの発行者のコンテキスト内でユニークまたはグローバルユニーク
                    .withIssuedAt(issuedAt)      //Issued At       | Use of this claim is OPTIONAL. JWTの発行時間
                    .withNotBefore(notBefore)    //Not Before      | Use of this claim is OPTIONAL. JWTの有効期限の開始時間 to account for clock skew. 通常は数分
                    .withExpiresAt(expiresAt)    //Expiration Time | Use of this claim is OPTIONAL. JWTの有効期限の終了時間 to account for clock skew. 通常は数分
                    //private claims
                    .withClaim("X-AUTHORITIES", "aaa")
                    .withClaim("X-USERNAME", "bbb")
                    .sign(algorithm);
            System.out.println("generate token : " + token);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    public void verify() {
        String secretKey = "secret";
        String token = "";
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            String subject = jwt.getSubject();
            Date issuedAt = jwt.getIssuedAt();
            Date notBefore = jwt.getNotBefore();
            Date expiresAt = jwt.getExpiresAt();
            System.out.println("subject : [" + subject + "] issuedAt : [" + issuedAt.toString() + "] notBefore : [" + notBefore.toString() + "] expiresAt : [" + expiresAt.toString() + "]");
            String authorities = jwt.getClaim("X-AUTHORITIES").asString();
            String username = jwt.getClaim("X-USERNAME").asString();
            System.out.println("private claim  X-AUTHORITIES : [" + authorities + "] X-USERNAME : [" + username + "]");
        } catch (UnsupportedEncodingException | JWTVerificationException e) {
            e.printStackTrace();
        }
    }

}
