package web.dao;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Controller
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        Query query = entityManager.createQuery("Select e from User e");
        List<User> userList = query.getResultList();

        return userList;
    }

    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(int id, User user) {
        User updateUser = getUser(id);

        updateUser.setName(user.getName());
        updateUser.setLastName(user.getLastName());
        updateUser.setAge(user.getAge());
    }

    @Override
    public void removeUserById(int id) {

        try {
            entityManager.remove(getUser(id));
        } catch (Exception e) {
            System.out.println("Пользователь не существует!!!");
        }
    }
}
