package com.example.demo.Utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Value;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {
    private SecretKey key = Keys.hmacShaKeyFor(System.getenv("JWT_SECRET").getBytes());
    private final JwtParser jwtParser = Jwts.parserBuilder().setAllowedClockSkewSeconds(30).setSigningKey(key).build();
    public Jws<Claims> parse(String jwtoken) {
        return jwtParser.parseClaimsJws(jwtoken);
    }

    public boolean isValid(String jwt) {
        if (!jwtParser.isSigned(jwt)) {
            return false;
        }
        Jws<Claims> jws = jwtParser.parseClaimsJws(jwt);
        return !jws.getBody().getExpiration().before(new Date());
    }

    public String sign(long id) {
       return Jwts.builder().setSubject(String.valueOf(id)).setExpiration(new Date(new Date().getTime() + 1000*60*60*24*3)).signWith(key).compact();
    }
}
