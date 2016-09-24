package pl.essay.itemMaker.dao;

import java.util.List;

import pl.essay.itemMaker.model.User;

public interface UserDao {
	public long addUser(User i);
	public void updateUser(User i);
	public List<User> listUsers();
	public User getUserById(int id);
	public User getUserByName(String name);
	public void removeUser(int id);
}
