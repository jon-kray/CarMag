package ru.ecosystem.carsale.app.security;


import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class CustomSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        var credentials = ((UserContext) authentication.getPrincipal());
        var session = request.getSession();
        session.setAttribute("USER_ID", credentials.getId());
        session.setAttribute("username", credentials.getUsername());
        response.sendRedirect(String.format("%s/posts", request.getContextPath()));
    }
}
