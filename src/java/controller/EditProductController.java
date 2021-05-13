package controller;

import layout.BaseController;
import model.Product;
import model.ProductsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditProductController", urlPatterns = {"/editProduct"})
public class EditProductController extends BaseController {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ensureUserLoggedIn(request, response)) {
            return;
        }

        String id = request.getParameter("id");

        ProductsService service = new ProductsService();

        request.setAttribute("product", service.product(Integer.parseInt(id)));
        view("editProduct", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!ensureUserLoggedIn(request, response)) {
            return;
        }

        Product product = new Product();
        product.setId(Integer.parseInt(request.getParameter("id")));
        product.setTitle(request.getParameter("title"));
        product.setDescription(request.getParameter("description"));
        product.setPrice(Double.parseDouble(request.getParameter("price")));

        ProductsService service = new ProductsService();
        service.editProduct(product);

        response.sendRedirect("products?id=" + product.getId());
    }
}
