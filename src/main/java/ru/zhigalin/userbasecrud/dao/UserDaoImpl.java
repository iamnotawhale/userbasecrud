package ru.zhigalin.userbasecrud.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.zhigalin.userbasecrud.model.Role;
import ru.zhigalin.userbasecrud.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("userDao")
@Transactional
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
        entityManager.persist(user);
    }

    @Override
    public void delete(User user) {
        User deletedUser = entityManager.find(User.class, user.getId());
        entityManager.remove(deletedUser);
    }

    @Override
    public void edit(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getById(Long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public User getUserByName(String login) {
        return entityManager.createQuery("from User where login = '" + login + "'", User.class).getSingleResult();
    }

    @Override
    public Role getRoleByName(String name) {
        return entityManager.createQuery("from Role where name = '" + name + "'", Role.class).getSingleResult();
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }
}
