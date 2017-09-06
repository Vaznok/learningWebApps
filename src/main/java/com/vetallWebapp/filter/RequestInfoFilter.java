package com.vetallWebapp.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestInfoFilter extends BaseFilter {
    public RequestInfoFilter() {
        System.out.println(this.getClass().getSimpleName() + " NEW");
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String method = request.getMethod();
        String remoteAddr = request.getRemoteAddr();
        String queryString = request.getQueryString();
        String protocol = request.getProtocol();
        System.out.println(">> RequestInfoFilter: method=" + method +
                ", remoteAddr=" + remoteAddr +
                ", queryString=" + queryString +
                ", protocol=" + protocol);
        System.out.println();

        chain.doFilter(request, response);
    }
}
