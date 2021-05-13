package controller;

import layout.BaseController;
import model.Topic;
import model.TopicsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NewTopicController", urlPatterns = {"/newTopic"})
public class NewTopicController extends BaseController {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ensureUserLoggedIn(request, response)) {
            return;
        }

        view("newTopic", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!ensureUserLoggedIn(request, response)) {
            return;
        }

        Topic topic = new Topic();
        topic.setTitle(request.getParameter("title"));
        topic.setContent(request.getParameter("content"));
        topic.setUser(getUserSession(request));

        TopicsService service = new TopicsService();
        service.createTopic(topic);

        response.sendRedirect("topics");
    }
}
