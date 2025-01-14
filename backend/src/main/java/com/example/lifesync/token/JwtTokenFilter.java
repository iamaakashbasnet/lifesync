package com.example.lifesync.token;

import com.example.lifesync.token.TokenInvalidateService;
import com.example.lifesync.utils.UtilFunctions;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private TokenInvalidateService tokenInvalidateService;

    @Autowired
    private UtilFunctions utilFunctions;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException, ServletException, IOException {
        String token = utilFunctions.extractTokenFromRequest(request);

        if (token != null && tokenInvalidateService.isValid(token)) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.equals("/api/v1/token/logout") || path.equals("/api/v1/token/login") || path.equals("/users") || path.equals("/api/v1/token/refresh");
    }
}
