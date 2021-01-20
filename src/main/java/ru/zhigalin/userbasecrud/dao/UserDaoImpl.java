package ru.zhigalin.userbasecrud.dao;

import org.springframework.stereotype.Repository;
import ru.zhigalin.userbasecrud.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<User> allUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void add(User user) {
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setUserEmail(user.getUserEmail());
        entityManager.persist(user);
    }

    @Override
    public void delete(User user) {
        User deletedUser = entityManager.find(User.class, user.getId());
        entityManager.remove(deletedUser);
    }

    @Override
    public void edit(User user) {
        User updatedUser = entityManager.find(User.class, user.getId());
        updatedUser.setUserName(user.getUserName());
        updatedUser.setUserEmail(user.getUserEmail());
    }

    @Override
    public User getById(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.detach(user);
        return user;
    }
}
