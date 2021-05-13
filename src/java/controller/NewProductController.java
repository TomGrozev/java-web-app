package controller;

import layout.BaseController;
import model.Product;
import model.ProductsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NewProductController", urlPatterns = {"/newProduct"})
public class NewProductController extends BaseController {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ensureUserLoggedIn(request, response)) {
            return;
        }

        view("newProduct", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!ensureUserLoggedIn(request, response)) {
            return;
        }

        Product product = new Product();
        product.setTitle(request.getParameter("title"));
        product.setDescription(request.getParameter("description"));
        product.setPrice(Double.parseDouble(request.getParameter("price")));
        product.setUser(getUserSession(request));

        ProductsService service = new ProductsService();
        service.createProduct(product);

        response.sendRedirect("products");
    }
}
