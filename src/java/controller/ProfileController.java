package controller;

import layout.BaseController;
import model.CartService;
import model.User;
import model.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProfileController", urlPatterns = {"/profile"})
public class ProfileController extends BaseController {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ensureUserLoggedIn(request, response)) {
            return;
        }

        User user = getUserSession(request);

        UsersService service = new UsersService();

        request.setAttribute("user", user);
        request.setAttribute("products", service.products(user));
        request.setAttribute("topics", service.engagedTopics(user));
        view("profile", request, response);
    }
}
