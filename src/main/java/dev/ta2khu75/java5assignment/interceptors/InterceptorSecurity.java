package dev.ta2khu75.java5assignment.interceptors;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import dev.ta2khu75.java5assignment.models.Role;
import dev.ta2khu75.java5assignment.resps.UserResp;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class InterceptorSecurity implements HandlerInterceptor{
    private final HttpSession session;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
                UserResp user=(UserResp)session.getAttribute("user");
                String error = "";
                if(user==null){
                    error = "Please login!";
                }else if(!user.getRole().equals(Role.ADMIN) && (request.getRequestURI().startsWith("/admin") || request.getRequestURI().startsWith("/crud"))){
                    error = "Access denied!";
                }
                if(error.length()>0){
                    session.setAttribute("securityUri", request.getRequestURI());
                    response.sendRedirect("/login?error="+error);
                    return false;
                }
                return true;
            }
}