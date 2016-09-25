package pl.essay.itemMaker.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import pl.essay.itemMaker.model.User;

public interface UserService extends UserDetailsService{

	    public long addUser(User i);
		public void updateUser(User i);
		public List<User> listUsers();
		public User getUserById(int id);
		public void removeUser(int id);

}
