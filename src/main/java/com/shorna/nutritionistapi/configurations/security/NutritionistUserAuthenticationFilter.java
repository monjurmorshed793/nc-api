package com.shorna.nutritionistapi.configurations.security;

import com.shorna.nutritionistapi.models.NutritionistUser;
import com.shorna.nutritionistapi.repositories.NutritionistUserRepository;
import com.shorna.nutritionistapi.services.NutritionistUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimAccessor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class NutritionistUserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final NutritionistUserRepository nutritionistUserRepository;
    private final JwtEncoder jwtEncoder;
    private final NutritionistUserService nutritionistUserService;

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

        String scope = authentication
                .getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(30, ChronoUnit.MINUTES))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();

        String bearerToken = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        Instant refreshTokenExpirationTime = Instant.now().plus(30, ChronoUnit.DAYS);
        JwtClaimsSet refreshClaims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(30, ChronoUnit.MINUTES))
                .subject(authentication.getName())
                .build();

        String refreshToken = this.jwtEncoder.encode(JwtEncoderParameters.from(refreshClaims)).getTokenValue();

        response.setHeader("bearer_token", bearerToken);
        response.setHeader("refresh_token", refreshToken);

        NutritionistUser user = nutritionistUserRepository.getNutritionistUserByEmail(authentication.getName());
        nutritionistUserService.updateRefreshToken(user.id(), refreshToken, refreshTokenExpirationTime);
    }
}
