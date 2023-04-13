package com.fitplanner.core.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.SignatureException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenUtil {

    private final Environment env;

    private final CustomUserDetailService customUserDetailService;

    public String generateJwtToken(Authentication authentication) {

        UserDetailsModel userPrincipal = (UserDetailsModel) authentication.getPrincipal();

        final String jwtSecretKey = env.getProperty("jwt.secret");
        final byte[] secretKeyBytes = Decoders.BASE64.decode(jwtSecretKey);
        final Key secretKey = Keys.hmacShaKeyFor(secretKeyBytes);


        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + Integer.parseInt(env.getProperty("jwt.token-validity-in-seconds"))))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(env.getProperty("jwt.secret")).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String getJwtToken) {
        try {
            Jwts.parserBuilder().setSigningKey(env.getProperty("jwt.secret")).build().parseClaimsJws(getJwtToken);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

}
