package com.nyanmyohtet.studentregistrationservice.util;

import com.nyanmyohtet.studentregistrationservice.persistence.model.User;
import io.jsonwebtoken.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24 hour

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    private static final Logger logger = LogManager.getLogger(JwtTokenUtil.class);

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            logger.error("JWT expired: {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            logger.error("Token is null, empty or only whitespace: {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            logger.error("JWT is invalid: {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            logger.error("JWT is not supported: {}", ex.getMessage());
        } catch (SignatureException ex) {
            logger.error("Signature validation failed: {}", ex.getMessage());
        }

        return false;
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(String.format("%s,%s", user.getId(), user.getEmail()))
                .setIssuer("spring")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}
