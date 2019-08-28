package demo.service;

import demo.model.User;

public interface UserService {

    User getById(long id);

    User save(User user);

}
