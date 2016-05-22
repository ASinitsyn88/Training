package ru.alfabank.filter;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class Filter1 implements Filter {

    private FilterConfig config = null;
    private boolean isActive = false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        this.config = filterConfig;
        String act = config.getInitParameter("auth");
        if (act != null) {
            isActive = act.equalsIgnoreCase("true");
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (isActive == true) {
            PrintWriter out = response.getWriter();
            out.print("<br>Filter1 is invoked before</br>");

            chain.doFilter(request, response);

            out.print("<br>Filter2 is invoked after</br>");
        } else {
            PrintWriter out = response.getWriter();
            out.print("<br>You are not authorize</br>");
        }
    }

    @Override
    public void destroy() {

        config = null;
    }
}
