package com.eco.monitor.filter;

import com.eco.monitor.config.ConfigurationManager;
import com.eco.monitor.secutiry.UserPrincipal;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

/**
 * Authorization filter.
 *
 * @author Vladyslav Hnes
 */
public class JWTAuthorizationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_SCHEMA = "bearer";

    @Autowired
    private ConfigurationManager configurationManager;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) request;

        var optionalToken = resolveToken(httpRequest);
        if (optionalToken.isPresent() && SecurityContextHolder.getContext().getAuthentication() == null) {

            Claims claims = decodeJWT(optionalToken.get());
            String scope = (String) claims.get("scope");
            var userPrincipal = new UserPrincipal(Long.valueOf(claims.getIssuer()), List.of(scope));
            var authentication = new UsernamePasswordAuthenticationToken(
                    userPrincipal, null, userPrincipal.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    public static Optional<String> resolveToken(HttpServletRequest request) {
        var bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && (bearerToken.toLowerCase().startsWith(AUTHORIZATION_SCHEMA))) {
            /* Get token */
            var parts = bearerToken.split(" ");
            return parts.length == 2 ? of(parts[1]) : empty();
        }
        return empty();
    }

    public Claims decodeJWT(String jwt) {
        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(configurationManager.getJwtSecret()))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

}
