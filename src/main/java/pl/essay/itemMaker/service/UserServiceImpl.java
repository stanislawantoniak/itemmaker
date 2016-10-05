package pl.essay.itemMaker.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.essay.itemMaker.model.UserT;
import pl.essay.itemMaker.controller.UserForm;
import pl.essay.itemMaker.dao.UserDao;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Inject private UserDao userDao;

	@PostConstruct
	public void init(){
	}

	public void updateUser(UserT i){
		this.userDao.update(i);
	}
	public long addUser(UserT i){
		this.userDao.create(i);
		return i.getId();
	}
	public List<UserT> listUsers(){
		return this.userDao.getAll();

	}
	public UserT getUserById(int id){
		return this.userDao.get(id);
	}
	public void removeUser(int id){
		this.userDao.deleteById(id);
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (userDao.existsUserByName(username)){
			System.out.println("user found! "+username);
			return this.userDao.getUserByName(username);
		}
		else
			throw new UsernameNotFoundException("User : "+username +" not found.");
	}

}
