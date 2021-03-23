package hibernate.dao.dao;

import java.util.List;

import hibernate.entities.Login;

public interface LoginDao {
	public Login getLoginById(int id);
	public int saveLogin(Login login);
	public List<String>getAllUserNames();
	public Login getLoginByName(String userName);
}
