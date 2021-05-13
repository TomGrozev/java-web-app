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
        if (!isUserNotLoggedIn(request, response)) {
            return;
        }

        view("login", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isUserNotLoggedIn(request, response)) {
            return;
        }

        Authentication auth = new Authentication();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = auth.authenticate(username, password);

        if (user != null) {
            setUserSession(request, user);
            response.sendRedirect("/Project1/topics");
            return;
        }

        view("login", request, response, new ArrayList<>(Collections.singletonList(new ProjectError(401, "Invalid authentication"))));
    }
}
