package chinchulin.varano.Security.JWT;

import chinchulin.varano.Security.Models.PrincipalUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;


@Service
public class JwtProvider {

    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    private static final String SECRET_KEY = "5468576D5A7134743777397A24432646294A404E635266556A586E3272357538";

    @Value("${jwt.expiration}")
    private long EXPIRATION_MINUTES;

    //Genera el token
    public String generateToken(Authentication authentication){

        Date issuedAt = new Date(System.currentTimeMillis());

        Date expiration = new Date(issuedAt.getTime() + (EXPIRATION_MINUTES * 60 * 1000));

        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();

        return Jwts.builder()
                .subject(principalUser.getUsername())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(generateKey(), Jwts.SIG.HS256)
                .compact();
    }


    public String getUsernameFromToken(String token){
        return getPayload(token).getSubject();
    }

    public boolean validateToken(String token){
        try {
            getPayload(token);
            return true;
        }catch (MalformedJwtException e){
            logger.error("token mal formado");
        }catch (UnsupportedJwtException e){
            logger.error("token no soportado");
        }catch (ExpiredJwtException e){
            logger.error("token expirado");
        }catch (IllegalArgumentException e){
            logger.error("token vacío");
        }catch (SignatureException e) {
            logger.error("fail en la firma");
        }

        return false;
    }

    private Claims getPayload(String token) {
        return Jwts.parser().verifyWith(generateKey()).build()
                .parseSignedClaims(token).getPayload();
    }

    private SecretKey generateKey(){
        byte[] secretBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        //secretBytes = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();
        return Keys.hmacShaKeyFor(secretBytes);
    }


}
