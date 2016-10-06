package pl.essay.itemMaker.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.essay.itemMaker.model.UserT;

@Repository
public class UserDaoImpl extends AbstractDaoHbn<UserT> implements UserDao {

	public UserT getUserByName(String name) {
		return (UserT) getSession()
				.getNamedQuery("getUserByName") //to fix later - named query does not work 
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
