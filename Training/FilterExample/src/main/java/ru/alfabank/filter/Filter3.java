package ru.alfabank.filter;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class Filter3 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        PrintWriter out = response.getWriter();
        out.print("<br>Filter3 is invoked before</br>");

        chain.doFilter(request, response);

        out.print("<br>Filter3 is invoked after</br>");
    }

    @Override
    public void destroy() {

    }
}
