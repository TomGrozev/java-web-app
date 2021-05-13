package controller;

import layout.BaseController;
import model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CartController", urlPatterns = {"/cart"})
public class CartController extends BaseController {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isUserLoggedIn(request, response)) {
            return;
        }

        CartService service = new CartService();

        request.setAttribute("cart", service.cart(getUserSession(request)));
        view("cart", request, response);
    }

//    @Override
    //protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //    if (!isUserLoggedIn(request, response)) {
    //        return;
    //    }

    //    int id = Integer.parseInt(request.getParameter("topic_id"));

    //    Comment comment = new Comment();
    //    comment.setContent(request.getParameter("comment"));
    //    comment.setUser(getUserSession(request));

    //    TopicsService service = new TopicsService();
    //    service.createComment(id, comment);

    //    response.sendRedirect("topics?id=" + id);
    //}
}
