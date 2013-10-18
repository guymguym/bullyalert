package me.bullyalert.dal.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import me.bullyalert.dal.pojo.Subscriber;

public class DAO {

    private static DAO instance = new DAO();
    private SessionFactory sessionFactory = createSessionFactory();

    public static DAO getInstance() {
        return instance;
    }

    private DAO() {
    }

    private static SessionFactory createSessionFactory() {
        try {

            return new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }

    private Session getSession() {
        try {
            if (sessionFactory == null) {
                sessionFactory = createSessionFactory();
            }
            return sessionFactory.openSession();
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }

    public List<Subscriber> getSubscribersByEmail(String email) {
        Session session = null;
        List<Subscriber> subscribers = null;
        try {
            session = getSession();
            subscribers = (List<Subscriber>) session.createQuery("FROM Subscriber WHERE email=:email").setParameter("email", email).list();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            session.close();
        }
        return subscribers;
    }

    public List<Subscriber> getSubscribersByPhone(String phone) {
        Session session = null;
        List<Subscriber> subscribers = null;
        try {
            session = getSession();
            subscribers = (List<Subscriber>) session.createQuery("FROM Subscriber WHERE phone=:phone").setParameter("phone", phone).list();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            session.close();
        }
        return subscribers;
    }

    public Subscriber getSubscriberById(String id) {
        Session session = null;
        Subscriber subscriber = null;
        try {
            session = getSession();
            subscriber = (Subscriber) session.get(Subscriber.class, id);
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            session.close();
        }
        return subscriber;
    }

    public List<Subscriber> getSubscribers() {
        Session session = null;
        List<Subscriber> subscribers = null;
        try {
            session = getSession();
            subscribers = (List<Subscriber>) session.createQuery("FROM Subscriber").list();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            session.close();
        }
        return subscribers;
    }

    public void saveSubscriber(Subscriber subscriber) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            session.save(subscriber);
            transaction.commit();
        } catch (Throwable t) {
            t.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    public void updateSubscriber(Subscriber subscriber) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            session.update(subscriber);
            transaction.commit();
        } catch (Throwable t) {
            t.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    public void deleteSubscriber(Subscriber subscriber) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            session.delete(subscriber);
            transaction.commit();
        } catch (Throwable t) {
            t.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }
}
