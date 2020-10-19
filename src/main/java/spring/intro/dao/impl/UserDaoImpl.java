package spring.intro.dao.impl;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import spring.intro.dao.UserDao;
import spring.intro.exceptions.DataProcessingException;
import spring.intro.model.User;
import spring.intro.util.HibernateUtil;

@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Override
    public void add(User user) {
        logger.debug("Method add() invoked");
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert User entity with email "
                    + user.getEmail(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> listUsers() {
        logger.debug("Method getAll() invoked");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> getMovies = session.createQuery("from User", User.class);
            return getMovies.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Failed to get Users list", e);
        }
    }
}
