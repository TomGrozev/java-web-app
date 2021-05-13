package controller;

import layout.BaseController;
import model.Feedback;
import model.TopicsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FeedbackController", urlPatterns = {"/feedback"})
public class FeedbackController extends BaseController {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ensureUserLoggedIn(request, response)) {
            return;
        }

        int id = Integer.parseInt(request.getParameter("topic_id"));

        Feedback feedback = new Feedback();
        feedback.setContent(request.getParameter("feedback"));
        feedback.setUser(getUserSession(request));

        TopicsService service = new TopicsService();
        service.createFeedback(id, feedback);

        response.sendRedirect("topics?id=" + id);
    }
}
