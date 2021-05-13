package controller;

import layout.BaseController;
import model.Product;
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
        if (!ensureUserLoggedIn(request, response)) {
            return;
        }

        String id = request.getParameter("id");

        ProductsService service = new ProductsService();

        if (id == null || id.equals("")) {
            request.setAttribute("products", service.products());
            view("products", "Products", request, response);
            return;
        }

        Product product = service.product(Integer.parseInt(id));
        request.setAttribute("product", product);
        view("product", product.getTitle() + " | Product", request, response);
    }
}
