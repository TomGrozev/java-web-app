package controller;

import layout.BaseController;
import model.Comment;
import model.TopicsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CommentsController", urlPatterns = {"/comments"})
public class CommentsController extends BaseController {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ensureUserLoggedIn(request, response)) {
            return;
        }

        int id = Integer.parseInt(request.getParameter("topic_id"));

        Comment comment = new Comment();
        comment.setContent(request.getParameter("comment"));
        comment.setUser(getUserSession(request));

        TopicsService service = new TopicsService();
        service.createComment(id, comment);

        response.sendRedirect("topics?id=" + id);
    }
}
