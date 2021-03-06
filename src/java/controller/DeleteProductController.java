package controller;

import layout.BaseController;
import model.Product;
import model.ProductsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteProductController", urlPatterns = {"/deleteProduct"})
public class DeleteProductController extends BaseController {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ensureUserLoggedIn(request, response)) {
            return;
        }

        ProductsService service = new ProductsService();

        Product product = service.product(Integer.parseInt(request.getParameter("id")));

        if (!product.getUser().getUsername().equals(getUserSession(request).getUsername())) {
            response.sendRedirect("topics?id=" + product.getId() + "&error=You+are+not+the+owner+of+this+product");
            return;
        }

        request.setAttribute("product", product);

        view("deleteProduct", "Delete Product | " + product.getTitle(), request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!ensureUserLoggedIn(request, response)) {
            return;
        }

        ProductsService service = new ProductsService();
        service.deleteProduct(Integer.parseInt(request.getParameter("id")));

        response.sendRedirect("products");
    }
}
