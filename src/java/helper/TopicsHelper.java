package helper;

import layout.DatabaseDriver;
import model.Feedback;
import model.Topic;
import model.User;

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
                topic.setFeedback(getTopicFeedback(id));
                return topic;
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }

        return null;
    }

    public List<Feedback> getTopicFeedback(int id) {
        List<Feedback> feedbacks = new ArrayList<>();

        try {
            Connection conn = (new DatabaseDriver.Database()).getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM feedback WHERE topic = ? ORDER BY id ASC");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                feedbacks.add(createFeedbackObject(rs));
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }

        return feedbacks;
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

    public void editTopic(Topic topic) {
        try {
            Connection conn = (new DatabaseDriver.Database()).getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE topics SET title = ?, content = ?, edited = ? WHERE id = ?");
            ps.setString(1, topic.getTitle());
            ps.setString(2, topic.getContent());
            ps.setBoolean(3, topic.isEdited());
            ps.setInt(4, topic.getId());
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

    public void deleteFeedbackForTopic(int id) {
        try {
            Connection conn = (new DatabaseDriver.Database()).getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM feedback WHERE topic = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    public void createFeedbackOnTopic(int topicID, Feedback feedback) {
        try {
            Connection conn = (new DatabaseDriver.Database()).getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO feedback(content, username, topic) VALUES (?, ?, ?)");
            ps.setString(1, feedback.getContent());
            ps.setString(2, feedback.getUser().getUsername());
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
        topic.setEdited(rs.getBoolean("edited"));
        User user = new User(rs.getString("username"));
        topic.setUser(user);
        return topic;
    }

    private Feedback createFeedbackObject(ResultSet rs) throws SQLException {
        Feedback feedback = new Feedback();
        feedback.setId(rs.getInt("id"));
        feedback.setContent(rs.getString("content"));
        User user = new User(rs.getString("username"));
        feedback.setUser(user);
        return feedback;
    }
}
