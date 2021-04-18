package hibernate.dao.daoImpl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;

import hibernate.dao.dao.CounterDao;
import hibernate.entities.Counter;
import hibernate.util.HibernateUtil;

public class CounterDaoImpl implements CounterDao {

	@Override
	public Counter getCounterById(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			Counter counter = session.get(Counter.class, id);
			return counter;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Counter getCounterByName(String name) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql="from Counter where counterName=:name";
			Counter counter=null;
			try {
			counter = session.createQuery(hql,Counter.class).setParameter("name",name).getSingleResult();
			}catch(NoResultException nor)
			{
				return null;
			}
			return counter;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Counter getCounterByPerson(String person) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql="from Counter where person=:person";
			Counter counter = session.createQuery(hql,Counter.class).setParameter("person",person).getSingleResult();
			return counter;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Counter> getAllCounter() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql="from Counter";
			List<Counter> list = session.createQuery(hql,Counter.class).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public char getBillInitial(int counterId) {
			return getCounterById(counterId).getBillinitial();
	}

	@Override
	public int saveCounter(Counter counter) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			if(counter.getId()==0)
			{
				session.save(counter);
				session.getTransaction().commit();
				return 1;
			}
			else {
				session.update(counter);
				session.getTransaction().commit();
				return 2;	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
