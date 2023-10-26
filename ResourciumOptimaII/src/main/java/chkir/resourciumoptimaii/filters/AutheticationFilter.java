//package chkir.resourciumoptimaii.filters;
//
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//
//@WebFilter("/dashboard")
//public class AutheticationFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        HttpSession session = request.getSession(false);
//        if (session != null && session.getAttribute("user") != null){
//
//            filterChain.doFilter(servletRequest,servletResponse);
//        } else {
//            response.sendRedirect(request.getContextPath() + "/login");
//        }
//    }
//}
