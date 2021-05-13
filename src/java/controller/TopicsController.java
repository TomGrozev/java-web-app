package controller;

import layout.BaseController;
import model.TopicsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TopicsController", urlPatterns = {"/topics"})
public class TopicsController extends BaseController {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isUserLoggedIn(request, response)) {
            return;
        }

        String id = request.getParameter("id");

        TopicsService service = new TopicsService();

        if (id == null || id.equals("")) {
            request.setAttribute("topics", service.topics());
            view("topics", request, response);
            return;
        }

        request.setAttribute("topic", service.topic(Integer.parseInt(id)));
        view("topic", request, response);
    }
}
