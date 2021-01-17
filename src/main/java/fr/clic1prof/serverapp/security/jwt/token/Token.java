package fr.clic1prof.serverapp.security.jwt.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public class Token {

    private static final long TOKEN_VALIDITY = 2 * 60 * 60; // 2 hours.
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512; // Using HMAC + SHA512.

    private String subject;
    private Claims claims;

    public Token(String subject) {
        this.subject = subject;
        this.claims = this.generate();
    }

    public Token(String token, String secretKey) {
        this.claims = this.parse(token, secretKey);
    }

    public String getUsername() {
        return this.claims.getSubject();
    }

    public Date getExpirationDate() {
        return this.claims.getExpiration();
    }

    public void setExpired() {
        this.claims.setExpiration(new Date());
    }

    public boolean isExpired() {
        Date expiration = this.getExpirationDate();
        return expiration.before(new Date());
    }

    public boolean isValid(UserDetails details) {

        if(this.isExpired()) return false;

        String username = details.getUsername();

        return username.equals(details.getUsername());
    }

    public String getToken(String secretKey) {
        return Jwts.builder()
                .setClaims(this.claims)
                .signWith(SIGNATURE_ALGORITHM, secretKey)
                .compact();
    }

    private Claims generate() {

        Date issuedAt = new Date();
        Date expireAt = new Date(System.currentTimeMillis() + (TOKEN_VALIDITY * 1000));

        return new DefaultClaims()
                .setSubject(this.subject)
                .setIssuedAt(issuedAt)
                .setExpiration(expireAt);
    }

    private Claims parse(String token, String secretKey) {
        return Jwts.parser()
                .setSigningKey(secretKey) // Specifying secret key.
                .parseClaimsJws(token) // Parsing claims.
                .getBody(); // Retrieving them.
    }
}
