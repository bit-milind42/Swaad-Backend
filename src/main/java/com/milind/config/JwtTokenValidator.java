package com.milind.config;

import java.io.IOException;
import java.util.List;
import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenValidator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String jwt = request.getHeader(JwtConstant.JWT_HEADER);

        if (jwt != null && jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7); // Remove the "Bearer " prefix

            try {
                SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
                
                Claims claims = Jwts.parserBuilder()
                                     .setSigningKey(key)
                                     .build()
                                     .parseClaimsJws(jwt)
                                     .getBody();

                String email = claims.get("email", String.class);
                String authorities = claims.get("authorities", String.class);

                // Convert authorities to a list of GrantedAuthority
                List<GrantedAuthority> authList = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
                
                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authList);

                // Set the authentication object in the Security Context
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                // Handle exceptions related to JWT parsing and validation
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid token: " + e.getMessage());
                return;
            }
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}
