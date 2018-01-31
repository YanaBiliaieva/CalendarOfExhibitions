package exhibitions.controller.filters;

import exhibitions.controller.authentification.Authentication;
import exhibitions.controller.SecurityConfiguration;
import exhibitions.controller.command.FactoryCommand;
import exhibitions.model.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class SecurityFilter implements Filter {
    private Logger logger = Logger.getLogger(SecurityFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("SecurityFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        logger.info("SecurityFilter doFilter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        SecurityConfiguration configuration = SecurityConfiguration.getInstance();
        logger.info("request.getRequestURI() in SecurityFilter===" + request.getRequestURI());
        String command = getStringCommand(getPath(request), configuration.getEndPoints());

        logger.info("command in SecurityFilter===" + command);
        String role = configuration.security(command);
        if ("ALL".equals(role)) {
            logger.info("SecurityFilter -----ALL.equals(role)");
            request.setAttribute("exhibitions/controller/command", command);
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if ("AUTH".equals(role)) {

            logger.info("SecurityFilter -----AUTH");
            logger.info("((User)(request.getSession().getAttribute(user))).getLogin()"+
                    ((User)(request.getSession().getAttribute("user"))).getLogin());
            boolean logged = Authentication.isUserLogIn(request.getSession());
            if (logged) {
                request.setAttribute("exhibitions/controller/command", command);
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendRedirect(FactoryCommand.ERROR);
            return;
        }
        if ("ADMIN".equals(role)) {
            logger.info("SecurityFilter -----ADMIN");
            boolean isAdmin = Authentication.isAdmin(request.getSession());
            if (isAdmin) {
                request.setAttribute("exhibitions/controller/command", command);
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendRedirect(FactoryCommand.ERROR);
            return;
        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.sendRedirect(FactoryCommand.ERROR);
    }

    @Override
    public void destroy() {
    }

    private String getStringCommand(String URI, Set<String> endPoints) {
        logger.info("in getStringCommand method URI= "+URI);
        for (String endPoint : endPoints) {
            logger.info("endPoint in loop= "+endPoint);
            if (URI.contains(endPoint))
                return endPoint;
        }
        return null;
    }

    private String getPath(HttpServletRequest request) {
        String path = request.getRequestURI().toLowerCase();
        int index=path.lastIndexOf("/");
        logger.info("int index: "+index);
        logger.info("path length: "+path.length());
        if (index != -1 &&index+1!=path.length()) {
            path=path.substring(index);
        }
        logger.info("RESULT path: "+path);
        return path;
    }
}
