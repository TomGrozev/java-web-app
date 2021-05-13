package helper;

import layout.DatabaseDriver;
import model.Comment;
import model.Topic;
import model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicsHelper {
    public List<Topic> getTopics() {
        List<Topic> topics = new ArrayList<>();

        try {
            Connection conn = (new DatabaseDriver.Database()).getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM topics ORDER BY id DESC");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                topics.add(createTopicObject(rs));
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }

        return topics;
    }

    public Topic getTopic(int id) {
        try {
            Connection conn = (new DatabaseDriver.Database()).getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM topics WHERE id = ? ORDER BY id DESC");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Topic topic = createTopicObject(rs);
                topic.setComments(getTopicComments(id));
                return topic;
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }

        return null;
    }

    public List<Comment> getTopicComments(int id) {
        List<Comment> comments = new ArrayList<>();

        try {
            Connection conn = (new DatabaseDriver.Database()).getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM comments WHERE topic = ? ORDER BY id ASC");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                comments.add(createCommentObject(rs));
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }

        return comments;
    }

    public void createTopic(Topic topic) {
        try {
            Connection conn = (new DatabaseDriver.Database()).getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO topics(title, content, username) VALUES (?, ?, ?)");
            ps.setString(1, topic.getTitle());
            ps.setString(2, topic.getContent());
            ps.setString(3, topic.getUser().getUsername());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    public void deleteTopic(int id) {
        try {
            Connection conn = (new DatabaseDriver.Database()).getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM topics WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    public void deleteCommentsForTopic(int id) {
        try {
            Connection conn = (new DatabaseDriver.Database()).getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM comments WHERE topic = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    public void createCommentOnTopic(int topicID, Comment comment) {
        try {
            Connection conn = (new DatabaseDriver.Database()).getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO comments(content, username, topic) VALUES (?, ?, ?)");
            ps.setString(1, comment.getContent());
            ps.setString(2, comment.getUser().getUsername());
            ps.setInt(3, topicID);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    private Topic createTopicObject(ResultSet rs) throws SQLException {
        Topic topic = new Topic();
        topic.setId(rs.getInt("id"));
        topic.setTitle(rs.getString("title"));
        topic.setContent(rs.getString("content"));
        User user = new User(rs.getString("username"));
        topic.setUser(user);
        return topic;
    }

    private Comment createCommentObject(ResultSet rs) throws SQLException {
        Comment comment = new Comment();
        comment.setId(rs.getInt("id"));
        comment.setContent(rs.getString("content"));
        User user = new User(rs.getString("username"));
        comment.setUser(user);
        return comment;
    }
}
