package com.vetallWebapp.filter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

public class AddAttributesToSessionFilter { //extends BaseFilter
    /*public AddAttributesToSessionFilter() {
        System.out.println(this.getClass().getSimpleName() + " NEW");
    }

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession(true);

        Enumeration<String> iter = filterConfig.getInitParameterNames();
        while(iter.hasMoreElements()) {
            String initParametrName = iter.nextElement();
            String initParametrValue = filterConfig.getInitParameter(initParametrName);
            session.setAttribute(initParametrName, initParametrValue);
        }
        chain.doFilter(request, response);
    }*/
}
