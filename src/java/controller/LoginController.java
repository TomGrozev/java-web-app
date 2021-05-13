package controller;

import layout.BaseController;
import layout.ProjectError;
import model.Authentication;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends BaseController {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ensureUserNotLoggedIn(request, response)) {
            return;
        }

        String redirect_after = request.getParameter("redirect_after");
        if (redirect_after != null) {
            request.setAttribute("redirect_after", redirect_after);
        }

        view("login", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ensureUserNotLoggedIn(request, response)) {
            return;
        }

        String redirect_after = request.getParameter("redirect_after");
        System.out.println(redirect_after);

        Authentication auth = new Authentication();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = auth.authenticate(username, password);

        if (user != null) {
            setUserSession(request, user);

            // FIXME: Possible redirect to external site vulnerability
            response.sendRedirect(redirect_after != null ? redirect_after : "/Project1/topics");
            return;
        }

        view("login", request, response, new ArrayList<>(Collections.singletonList(new ProjectError(401, "Invalid authentication"))));
    }
}
