package com.example.assignment1.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component                    //allows spring to automatically detect custom beans
@RequiredArgsConstructor      //generates a constructor with 1 parameter for each field that requires special handling(using that private final field)
public class JwtAuthenticationFilter extends OncePerRequestFilter {   //it s a filter that is guaranteed to be executed only once for a given request

    private final JwtService jwtService; // manipulates the JWT token
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String header = request.getHeader("Authorization");
        final String jwt;
        final String username;
        if(header == null || !header.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        jwt = header.substring(7);
        // user in our database or not?
        username = jwtService.extractUsername(jwt);
        // if our user exists and it is not authenticated
        if(username != null || SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if(jwtService.isValidToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
