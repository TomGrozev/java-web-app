package controller;

import layout.BaseController;
import model.CartService;
import model.Product;
import model.ProductsService;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteCartItemController", urlPatterns = {"/deleteCartItem"})
public class DeleteCartItemController extends BaseController {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ensureUserLoggedIn(request, response)) {
            return;
        }

        ProductsService service = new ProductsService();

        Product product = service.product(Integer.parseInt(request.getParameter("id")));

        request.setAttribute("product", product);

        view("deleteCartItem", "Delete Cart Item | " + product.getTitle(), request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!ensureUserLoggedIn(request, response)) {
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        User user = getUserSession(request);

        CartService service = new CartService();
        service.removeProduct(user, id);

        response.sendRedirect("cart");
    }
}
