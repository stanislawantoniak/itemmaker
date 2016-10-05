package pl.essay.itemMaker.dao;

import org.springframework.transaction.annotation.Transactional;

import pl.essay.itemMaker.model.UserT;

@Transactional
public interface UserDao extends Dao<UserT> {
	public UserT getUserByName(String name);
	public boolean existsUserByName(String name);
}
