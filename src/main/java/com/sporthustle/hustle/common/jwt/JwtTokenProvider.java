package com.sporthustle.hustle.common.jwt;

import static com.sporthustle.hustle.common.consts.Constants.*;

import com.sporthustle.hustle.common.jwt.dto.TokenInfo;
import com.sporthustle.hustle.user.entity.User;
import io.jsonwebtoken.*;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
  @Value("${jwt.secret.key}")
  private String secretKey;

  private final UserDetailsService userDetailsService;

  private String createAccessToken(String userPk, Date issueAt, Date tokenExpiredIn) {
    return Jwts.builder()
        .setSubject(userPk)
        .setIssuedAt(issueAt)
        .claim(TOKEN_TYPE, ACCESS_TOKEN)
        .setExpiration(tokenExpiredIn)
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }

  private String createRefreshToken(String userPk, Date issueAt, Date tokenExpiredIn) {
    return Jwts.builder()
        .setSubject(userPk)
        .setIssuedAt(issueAt)
        .claim(TOKEN_TYPE, REFRESH_TOKEN)
        .setExpiration(tokenExpiredIn)
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }

  public TokenInfo createToken(String userPk) {
    Date now = new Date();

    Date accessTokenExpiredIn = new Date(now.getTime() + ACCESS_TOKEN_EXPIRATION_TIME);
    Date refreshTokenExpiredIn = new Date(now.getTime() + REFRESH_TOKEN_EXPIRATION_TIME);

    String accessToken = createAccessToken(userPk, now, accessTokenExpiredIn);
    String refreshToken = createRefreshToken(userPk, now, refreshTokenExpiredIn);

    return TokenInfo.builder().accessToken(accessToken).refreshToken(refreshToken).build();
  }

  public Authentication getAuthentication(String token) {
    User user = (User) userDetailsService.loadUserByUsername(this.getUserPk(token));
    return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
  }

  public String getUserPk(String token) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
  }

  public String resolveToken(HttpServletRequest request) {
    String token = request.getHeader("Authorization");
    return refineToken(token);
  }

  public String refineToken(String token) {
    if (token != null && token.substring(0, 6).equals("Bearer")) {
      token = token.substring(7);
    }
    return token;
  }

  public boolean validateToken(String jwtToken) {
    try {
      Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
      return !claims.getBody().getExpiration().before(new Date());
    } catch (SecurityException | MalformedJwtException e) {
      log.info("Invalid JWT Token", e);
    } catch (ExpiredJwtException e) {
      log.info("Expired JWT Token", e);
    } catch (UnsupportedJwtException e) {
      log.info("Unsupported JWT Token", e);
    } catch (IllegalArgumentException e) {
      log.info("JWT claims string is empty.", e);
    } catch (SignatureException e) {
      log.info("JWT Signature does not match locally compuited signature.", e);
    }
    return false;
  }

  public String getTokenType(String jwtToken) {
    Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
    String type = (String) claims.getBody().get(TOKEN_TYPE);
    return type;
  }
}
