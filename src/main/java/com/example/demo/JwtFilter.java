package com.example.demo;

import com.example.demo.Entities.User;
import com.example.demo.Services.UserService;
import com.example.demo.Utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import static org.springframework.security.authentication.UsernamePasswordAuthenticationToken.authenticated;

@Component
public class JwtFilter implements Filter {
    private final JwtUtil jwtUtil;
    private final UserService userService;
    public JwtFilter(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httprequest = (HttpServletRequest) request;
        HttpServletResponse httpresponse = (HttpServletResponse) response;
        Cookie[] cookies = httprequest.getCookies();
        for (Cookie x : cookies) {
            if (x.getName().equals("authToken")) {
                try {
                if (!jwtUtil.isValid(x.getValue())) {
                    httpresponse.sendError(401, "invalid authentication token.");
                    return;
                }
                Jws<Claims> jws = jwtUtil.parse(x.getValue());
                Optional<User> user = userService.findUserById(Long.getLong(jws.getBody().getSubject()));
                if (user.isEmpty()) {
                    httpresponse.sendError(401, "invalid user");
                    return;
                }
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken upat =  UsernamePasswordAuthenticationToken.authenticated(user.get(), null, Collections.emptyList());
                context.setAuthentication(upat);
                SecurityContextHolder.setContext(context);
                chain.doFilter(httprequest, httpresponse);
                return;
                } catch (Exception exception) {
                    httpresponse.sendError(500, "internal server error.");
                }
            }
        }
    }
}
