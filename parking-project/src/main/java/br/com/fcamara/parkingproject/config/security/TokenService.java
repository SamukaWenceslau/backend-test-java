package br.com.fcamara.parkingproject.config.security;

import br.com.fcamara.parkingproject.model.Company;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${parking.jwt.expiration}")
    private String expiration;

    @Value("${parking.jwt.secret}")
    private String secret;

    public String generateToken(Authentication authentication) {

        Company authenticated = (Company) authentication.getPrincipal();
        Date currentDate = new Date();
        Date date = new Date(currentDate.getTime() + Long.parseLong(expiration));
        return Jwts.builder()
      .setIssuer("API")
              .setSubject(authenticated.getId().toString())
                .setIssuedAt(currentDate)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public Long getCompanyId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(this.secret)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }
}
