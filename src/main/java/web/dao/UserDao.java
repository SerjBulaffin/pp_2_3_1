package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    public List<User> getAllUsers();
    public User getUser(int id);
    public void addUser(User user);
    public void updateUser(int id, User user);
    public void removeUserById(int id);
}
