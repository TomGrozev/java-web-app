package layout;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BaseController extends HttpServlet {

    private static final String SESSION_KEY = "username";

    protected void view(String viewName, String title, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // add request url errors
        String error = request.getParameter("error");

        if (error != null) {
            List<ProjectError> errors = (List<ProjectError>) request.getAttribute("errors");
            if (errors == null) {
                errors = new ArrayList<>();
            }
            errors.add(new ProjectError(500, error));
            request.setAttribute("errors", errors);
            request.setAttribute("hasErrors", true);
        }

        request.setAttribute("login_session", getUserSession(request));
        request.setAttribute("pageTitle", title);
        request.setAttribute("pagePath", viewName);
        request.getRequestDispatcher("/templates/template.jsp").forward(request, response);
    }

    protected void view(String viewName, String title, HttpServletRequest request, HttpServletResponse response, List<ProjectError> errs) throws ServletException, IOException {
        request.setAttribute("login_session", getUserSession(request));
        request.setAttribute("hasErrors", !errs.isEmpty());
        if (!errs.isEmpty()) {
            request.setAttribute("errors", errs);
        }

        view(viewName, title, request, response);
    }

    protected User getUserSession(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute(SESSION_KEY);

        if (username != null) {
            return new User(username);
        }

        return null;
    }

    protected void setUserSession(HttpServletRequest request, User user) {
        request.getSession().setAttribute(SESSION_KEY, user.getUsername());
    }

    protected void deleteUserSession(HttpServletRequest request) {
        request.getSession().setAttribute(SESSION_KEY, null);
        request.getSession().invalidate();
    }

    protected boolean isUserLoggedIn(HttpServletRequest request) {
        User user_session = getUserSession(request);
        String username = (user_session == null ? null : user_session.getUsername());

        return username != null && !username.equals("");
    }

    protected boolean ensureUserLoggedIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean loggedIn = isUserLoggedIn(request);

        if (!loggedIn) {
            String path = request.getRequestURI();
            response.sendRedirect("login?redirect_after=" + path);
        }

        return loggedIn;
    }

    protected boolean ensureUserNotLoggedIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean loggedIn = isUserLoggedIn(request);

        if (loggedIn) {
            response.sendRedirect("topics");
        }

        return !loggedIn;
    }
}
