package com.challenge.api.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Checks a provided API key against the one set in <code>application.yml</code>
 * If they don't match, the request is blocked with a 401 Unauthorized response.
 */
@Component
public class ApiKeyInterceptor implements HandlerInterceptor {

    private final String validApiKey;

    public ApiKeyInterceptor(@Value("${api.key}") String validApiKey) {
        this.validApiKey = validApiKey;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler)
            throws Exception {
        String apiKey = request.getHeader("api-key");
        if (validApiKey.equals(apiKey)) {
            return true;
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Unauthorized: Invalid or missing API key");
        return false;
    }
}
