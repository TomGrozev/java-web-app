package controller;

import layout.BaseController;
import model.ProductsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductsController", urlPatterns = {"/products"})
public class ProductsController extends BaseController {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isUserLoggedIn(request, response)) {
            return;
        }

        String id = request.getParameter("id");

        ProductsService service = new ProductsService();

        if (id == null || id.equals("")) {
            request.setAttribute("products", service.products());
            view("products", request, response);
            return;
        }

        request.setAttribute("product", service.product(Integer.parseInt(id)));
        view("product", request, response);
    }
}
