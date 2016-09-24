package pl.essay.itemMaker.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.essay.itemMaker.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import javax.persistence.NoResultException;

@Repository
public class UserDaoImpl implements UserDao {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	private static SessionFactory sessionFactory; 
	@Autowired
	public  void setSessionFactory(SessionFactory sf){
		sessionFactory = sf;
	}

	public long addUser(User user){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			logger.error("", e);
		}finally {
			session.close(); 
		}
		logger.info("User saved successfully, details="+user);
		return user.getId();
	}

	public void updateUser(User user){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.update(user); 
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			logger.error("", e);
		}finally {
			session.close(); 
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> listUsers( ){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<User> Users = null;
		try{
			tx = session.beginTransaction();
			Users = (List<User>) session.createQuery("FROM User").getResultList();
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			logger.error("", e); 
		}finally {
			session.close(); 
		}
		return Users;
	}

	public void removeUser(int i){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			User user = (User) session.load(User.class, i);
			session.delete(user); 
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			logger.error("", e);
		}finally {
			session.close(); 
		}
	}
	public User getUserById(int i){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		User user = null;
		try{
			tx = session.beginTransaction();
			user = (User) session.load(User.class, i);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			logger.error("", e);
		}finally {
			session.close(); 
		}
		logger.info("User retrieved, details="+user);
		return user;
	}

	public User getUserByName(String name) {
		Session session = sessionFactory.openSession();
		User user = null;
		try {
			user = (User) session
				//.createNamedQuery("get_user_by_name")
				.createQuery("select u from User u where name = :name")
				.setParameter("name", name)
				.getSingleResult();
		} catch ( NoResultException e) {
			logger.error("", e);
		} finally {
			session.close();
		}
		
		logger.info("User retrieved, details="+user);
		return user;
	}
}
