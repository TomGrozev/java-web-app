package model;

import helper.TopicsHelper;

import java.util.List;

public class TopicsService {

    private final TopicsHelper helper;

    public TopicsService() {
        helper = new TopicsHelper();
    }

    public List<Topic> topics() {
        return helper.getTopics();
    }

    public Topic topic(int id) {
        return helper.getTopic(id);
    }

    public void createTopic(Topic topic) {
        helper.createTopic(topic);
    }

    public void deleteTopic(int id) {
        helper.deleteCommentsForTopic(id);
        helper.deleteTopic(id);
    }

    public void createComment(int topicID, Comment comment) {
        helper.createCommentOnTopic(topicID, comment);
    }
}
