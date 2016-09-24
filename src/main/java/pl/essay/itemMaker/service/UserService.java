package pl.essay.itemMaker.service;

import java.util.List;

import pl.essay.itemMaker.model.User;

public interface UserService {

	    public int addUser(User i);
		public void updateUser(User i);
		public List<User> listUsers();
		public User getUserById(int id);
		public void removeUser(int id);

}
