package pl.essay.itemMaker.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.essay.itemMaker.model.User;
import pl.essay.itemMaker.dao.UserDao;

@Service
public class UserServiceImpl implements UserService{

	private UserDao userDao;

	//init for dev purposes
	//just to have a user in db in case of db re-creation
	//to fix
	@PostConstruct
	public void init(){
		UserDetails u = null;
		String name = "stan@wp.pl";
		try {
			u = this.loadUserByUsername(name);
		} catch( UsernameNotFoundException e ) {
			System.out.println(e.getLocalizedMessage());
			User user = new User(name,"123456",User.roleAdmin,true);
			this.addUser(user);
			System.out.println("user : "+user+" added to db");
		}
	}

	@Autowired
	public void setUserDAO(UserDao i){
		this.userDao = i;
	}

	public void updateUser(User i){
		this.userDao.updateUser(i);
	}
	public long addUser(User i){
		return this.userDao.addUser(i);
	}
	public List<User> listUsers(){
		return this.userDao.listUsers();

	}
	public User getUserById(int id){
		return this.userDao.getUserById(id);
	}
	public void removeUser(int id){
		this.userDao.removeUser(id);
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u = this.userDao.getUserByName(username);
		if (u == null)
			throw new UsernameNotFoundException("User : "+username +" not found.");
		else
			return u;
	}

}
