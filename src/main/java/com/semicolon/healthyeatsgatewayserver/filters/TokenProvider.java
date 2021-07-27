package com.semicolon.healthyeatsgatewayserver.filters;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
@Slf4j
public class TokenProvider {
    @Value("${jwt.token.validity}")
    public long TOKEN_VALIDITY;

    @Value("${jwt.signing.key}")
    public String SIGNING_KEY;

    @Value("${jwt.authorities.key}")
    public String AUTHORITIES_KEY;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateJwtToken(String token) {
        boolean tokenValid = false;
        try {
            Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token);
            tokenValid = !isTokenExpired(token);
        } catch (SignatureException e) {
            log.error("Invalid Jwt Signature {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid jwt {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("Jwt Expired {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("jwt unsupported {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("jwt claims string is empty {}", e.getMessage());
        }

        return tokenValid;
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


}
