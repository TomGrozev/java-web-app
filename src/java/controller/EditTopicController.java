package controller;

import layout.BaseController;
import model.Product;
import model.ProductsService;
import model.Topic;
import model.TopicsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditTopicController", urlPatterns = {"/editTopic"})
public class EditTopicController extends BaseController {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ensureUserLoggedIn(request, response)) {
            return;
        }

        String id = request.getParameter("id");

        TopicsService service = new TopicsService();

        request.setAttribute("topic", service.topic(Integer.parseInt(id)));
        view("editTopic", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!ensureUserLoggedIn(request, response)) {
            return;
        }

        Topic topic = new Topic();
        topic.setId(Integer.parseInt(request.getParameter("id")));
        topic.setTitle(request.getParameter("title"));
        topic.setContent(request.getParameter("content"));
        topic.setEdited(true);

        TopicsService service = new TopicsService();
        service.editTopic(topic);

        response.sendRedirect("topics?id=" + topic.getId());
    }
}
