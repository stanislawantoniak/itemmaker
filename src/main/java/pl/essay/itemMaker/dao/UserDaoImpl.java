package pl.essay.itemMaker.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.essay.itemMaker.model.User;

@Repository
@Transactional
public class UserDaoImpl extends AbstractDaoHbn<User> implements UserDao {

	public User getUserByName(String name) {
		return (User) getSession()
				.createQuery("select u from User u where name = :name")
				//.createNamedQuery("getUserByName") //to fix later - named query does not work 
				.setParameter("name", name)
				.getSingleResult();
	}

	public boolean existsUserByName(String name) {
		
		try {
			this.getUserByName(name);
		} catch ( Exception e ) {
			System.out.println(e.getLocalizedMessage());
			return false;
		}
		return true;
	}	

}
