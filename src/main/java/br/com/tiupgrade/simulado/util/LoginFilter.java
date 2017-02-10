package br.com.tiupgrade.simulado.util;

import br.com.tiupgrade.simulado.seguranca.Autenticador;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {

    /**
     * Checks if user is logged in. If not it redirects to the login.xhtml page.
     */
    @Inject
    Autenticador autenticador;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Get the loginBean from session attribute
        //Autenticador autenticador = (Autenticador) ((HttpServletRequest) request).getSession().getAttribute("autenticador");

        // For the first application request there is no loginBean in the
        // session so user needs to log in
        // For other requests loginBean is present but we need to check if user
        // has logged in successfully
        if (autenticador == null || !autenticador.isLoggedIn()) {
            String contextPath = ((HttpServletRequest) request).getContextPath();
            ((HttpServletResponse) response).sendRedirect(contextPath + "/login.xhtml");
        }

        chain.doFilter(request, response);

    }

    public void init(FilterConfig config) throws ServletException {
        // Nothing to do here!
    }

    public void destroy() {
        // Nothing to do here!
    }

}
