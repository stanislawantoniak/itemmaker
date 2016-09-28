package pl.essay.itemMaker.dao;

import org.springframework.transaction.annotation.Transactional;

import pl.essay.itemMaker.model.User;

@Transactional
public interface UserDao extends Dao<User> {
	public User getUserByName(String name);
	public boolean existsUserByName(String name);
}
