package cl.blueprintsit.framework.web.urlfilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class URLFilter implements Filter {

    public void destroy() {
// TODO Auto-generated method stub

    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
// TODO Auto-generated method stub
        HttpServletRequest httpServletRequest = ((HttpServletRequest)request);
        String dispatchURL =((HttpServletRequest)request).getServletPath()+".xhtml";
        try{

            httpServletRequest.getRequestDispatcher(dispatchURL).forward(request, response);
        }catch(Exception e){
        }

    }


    public void init(FilterConfig arg0) throws ServletException {
// TODO Auto-generated method stub

    }

}