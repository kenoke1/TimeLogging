package com.example.time_logging.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

//    @Value("${application.security.jwt.secret-key}")
//    private  String secretKey;
//    @Value("${application.security.jwt.expiration}")
//    private  Long jwtExpiration;
//    @Value("${application.security.jwt.refresh-token.expiration}")
//    private  Long refreshExpiration;
    private static final String SECRET_KEY = "423F4528482B4D6251655468566D597133743677397A24432646294A404E6352";

    private String jwtExpiration = String.valueOf(1000 * 60 * 60 * 24 * 24);


    private long refreshExpiration = 1000 * 60 * 60 * 24 * 24 * 7;


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractALlClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return buildToken(extraClaims, userDetails, Long.parseLong(String.valueOf(jwtExpiration)));

    }

    public String generateRefreshToken(
            UserDetails userDetails
    ) {
        return buildToken(new HashMap<>(), userDetails, Long.parseLong(String.valueOf(refreshExpiration)));
    }

    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(UserDetails userDetails){
        return  generateToken(new HashMap<>(), userDetails);
    }

    private Claims extractALlClaims(String token){
        return Jwts
                .parserBuilder().
                setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
