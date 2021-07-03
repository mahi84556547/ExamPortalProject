package com.examportal.server.config;

import com.examportal.server.services.impl.UserServiceDetailsImpl;
import com.examportal.server.utils.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private UserServiceDetailsImpl userServiceDetails;
    private JwtUtils jwtUtils;

    @Autowired
    public JwtAuthenticationFilter(UserServiceDetailsImpl userServiceDetails, JwtUtils jwtUtils) {
        this.userServiceDetails = userServiceDetails;
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
         final String requestTokenHeader=httpServletRequest.getHeader("Authorization");
        String username=null;
        String jwtToken=null;
        if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")){
            jwtToken= requestTokenHeader.substring(7);
            try {
                username=this.jwtUtils.extractUsername(jwtToken);

            }catch (ExpiredJwtException eje){
                eje.printStackTrace();
                System.out.println("jwtToken has been expired");
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Error");
            }
        }else {
            System.out.println("invalid token, not start with bearer string.");
        }

        if(username != null && SecurityContextHolder.getContext() != null){
            final UserDetails userDetails=userServiceDetails.loadUserByUsername(username);
            if(this.jwtUtils.validateToken(jwtToken,userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }else {
            System.out.println("token is not valid");
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
