package hibernate.service.serviceImpl;

import java.util.List;

import hibernate.dao.dao.LoginDao;
import hibernate.dao.daoImpl.LoginDaoImpl;
import hibernate.entities.Login;
import hibernate.service.service.LoginService;

public class LoginServiceImpl implements LoginService {

	private LoginDao dao;
	public LoginServiceImpl() {
		this.dao = new LoginDaoImpl();
	}
	@Override
	public Login getLoginById(int id) {
		return dao.getLoginById(id);
	}

	@Override
	public int saveLogin(Login login) {
		return dao.saveLogin(login);
		
	}
	@Override
	public List<String> getAllUserNames() {
		return dao.getAllUserNames();
	}
	@Override
	public Login getLoginByName(String userName) {
		return dao.getLoginByName(userName);
	}

}
