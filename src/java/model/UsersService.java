package model;

import helper.UsersHelper;

import java.util.List;

public class UsersService {
    private final UsersHelper helper;

    public UsersService() {
        helper = new UsersHelper();
    }

    public List<Product> products(User user) {
        ProductsService service = new ProductsService();
        return service.userProducts(user);
    }

    public List<Topic> engagedTopics(User user) {
        TopicsService service = new TopicsService();
        return service.userEngagedTopics(user);
    }

    public boolean register(User user) {
        if (helper.userExists(user)) {
            return false;
        }

        helper.register(user);
        return true;
    }
}
