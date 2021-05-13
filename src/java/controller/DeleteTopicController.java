package controller;

import layout.BaseController;
import model.Topic;
import model.TopicsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteTopicController", urlPatterns = {"/deleteTopic"})
public class DeleteTopicController extends BaseController {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ensureUserLoggedIn(request, response)) {
            return;
        }

        TopicsService service = new TopicsService();

        Topic topic = service.topic(Integer.parseInt(request.getParameter("id")));

        if (!topic.getUser().getUsername().equals(getUserSession(request).getUsername())) {
            response.sendRedirect("topics?id=" + topic.getId() + "&error=You+are+not+the+owner+of+this+topic");
            return;
        }

        request.setAttribute("topic", topic);

        view("deleteTopic", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!ensureUserLoggedIn(request, response)) {
            return;
        }

        TopicsService service = new TopicsService();
        service.deleteTopic(Integer.parseInt(request.getParameter("id")));

        response.sendRedirect("topics");
    }
}
