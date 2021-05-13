package controller;

import layout.BaseController;
import layout.ProjectError;
import model.User;
import model.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends BaseController {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isUserNotLoggedIn(request, response)) {
            return;
        }

        view("register", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isUserNotLoggedIn(request, response)) {
            return;
        }

        User user = new User(request.getParameter("username"), request.getParameter("password"));

        UsersService service = new UsersService();

        if (service.register(user)) {
            response.sendRedirect("login?registered=true");
            return;
        }

        request.setAttribute("username", user.getUsername());
        request.setAttribute("password", user.getPassword());

        view("register", request, response, new ArrayList<>(Arrays.asList(new ProjectError(400, "There was an error registering this user. Maybe the account exists already."))));
    }
}
