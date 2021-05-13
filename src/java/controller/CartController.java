package controller;

import layout.BaseController;
import model.CartService;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CartController", urlPatterns = {"/cart"})
public class CartController extends BaseController {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ensureUserLoggedIn(request, response)) {
            return;
        }

        CartService service = new CartService();

        request.setAttribute("cart", service.cart(getUserSession(request)));
        view("cart", "Cart", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ensureUserLoggedIn(request, response)) {
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        User user = getUserSession(request);

        CartService service = new CartService();
        if (!service.addProduct(user, id)) {
            response.sendRedirect("products?error=Product+Already+In+Cart");
            return;
        }

        response.sendRedirect("cart");
    }
}
