package cl.blueprintsit.framework.web.auth.filter;

import cl.blueprintsit.framework.web.Constants;
import cl.blueprintsit.framework.web.auth.ABMAuthenticationBean;
import cl.blueprintsit.framework.auth.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by BluePrints Developer on 19-05-2016.
 */
public class AuthenticationFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;


        if (req.getRequestURI().contains(Constants.LOGIN_PAGE)
                || req.getRequestURI().contains(Constants.ERRORS_FOLDER)
                || req.getServletPath().startsWith("/javax.faces.resource")
                || hasPermission(req)) {
            chain.doFilter(request, response);
        } else if (!isLoggedIn(req)) { //perdio la sesion

            String queryString = "";
            if (req.getQueryString() != null)
                queryString = "?" + req.getQueryString().replace("&", "$");
            String redirectURL = req.getContextPath() + "/" + Constants.LOGIN_PAGE + "?viewExpired=true&originalURI=" + req.getRequestURI() + queryString;

            boolean isAjax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
            if (!isAjax) {
                res.sendRedirect(redirectURL);
            } else {
                res.getWriter().print(xmlPartialRedirectToPage());
                res.flushBuffer();
            }
            LOGGER.debug("usuario perdio sesion. redirigiendo a {}", redirectURL);


        } else if (!hasPermission(req)) {//no tiene permiso para acceder a la pagina solicitada
            String redirectURL = req.getContextPath() + "/" + Constants.ERRORS_FOLDER + "/" + Constants.AUTH_ERROR_PAGE;
            res.sendRedirect(redirectURL);
            LOGGER.debug("usuario sin sesion. redirigiendo a {}", redirectURL);

        } else {//otros casos que nunca deberian darse

            LOGGER.debug("Caso que no deberia pasar");
            res.sendRedirect(req.getContextPath() + "/" + Constants.LOGIN_PAGE);
        }

    }

    private String xmlPartialRedirectToPage() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version='1.0' encoding='UTF-8'?>");
        sb.append("<partial-response>");
        sb.append("<eval>");
        sb.append("<![CDATA[\n" +
                "      if (window.confirm(\"Su sesi\\u00F3n expir\\u00F3, desea ser redirigido?\")) {\n" +
                "            window.location.reload();\n" +
                "        }\n" +
                "    ]]>");
        sb.append("</eval>");
        sb.append("</partial-response>");

        return sb.toString();
    }

    private boolean isLoggedIn(HttpServletRequest req) {
        return req.getSession().getAttribute(ABMAuthenticationBean.AUTH_KEY) != null;
    }

    private boolean hasPermission(HttpServletRequest req) {
        if (!isLoggedIn(req))
            return false;

        ABMAuthenticationBean auth = (ABMAuthenticationBean) req.getSession().getAttribute("authenticationBean");
        User u = auth.getLoggedUser();

        return u != null;

    }


    public void destroy() {
        //nothing to do
    }


    public void init(FilterConfig arg0) throws ServletException {
        //nothing to do
    }


}


