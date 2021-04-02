package hibernate.dao.daoImpl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;

import hibernate.dao.dao.ItemDao;
import hibernate.entities.Item;
import hibernate.util.HibernateUtil;

public class ItemDaoImpl implements ItemDao {

	@Override
	public Item getItemById(long id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			Item item = session.get(Item.class,id);
			return item;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Item getItemByName(String name) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			String hql = "from Item where ItemName=:name";
			Item item=null;
			try {
			item = session.createQuery(hql,Item.class).setParameter("name",name).getSingleResult();
			}catch(NoResultException nor)
			{
				return null;
			}
			return item;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Item> getAllItems() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			String hql = "from Item";
			List<Item> list = session.createQuery(hql,Item.class).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int saveItem(Item item) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			if(item.getId()==0)
			{
				session.save(item);
				session.getTransaction().commit();
				return 1;
			}
			else
			{
				session.update(item);
				session.getTransaction().commit();
				return 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
