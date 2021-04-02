package hibernate.dao.daoImpl;

import java.util.List;

import org.hibernate.Session;

import hibernate.dao.dao.LoginDao;
import hibernate.entities.Login;
import hibernate.util.HibernateUtil;

public class LoginDaoImpl implements LoginDao {

	@Override
	public Login getLoginById(int id) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			Login login = session.get(Login.class,id);
			return login;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int saveLogin(Login login) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			if(login.getId()==0)
			{
				session.save(login);
				session.getTransaction().commit();
				return 1;
			}
			else
			{
				session.update(login);
				session.getTransaction().commit();
				return 2;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<String> getAllUserNames() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select userName from Login";
			@SuppressWarnings("unchecked")
			List<String> list = session.createQuery(hql).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Login getLoginByName(String userName) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = " from Login where userName=:name";
			Login login = session.createQuery(hql,Login.class).setParameter("name",userName).uniqueResult();
			return login;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
