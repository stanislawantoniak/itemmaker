package pl.essay.itemMaker.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.essay.itemMaker.model.Item;
import pl.essay.itemMaker.model.ItemComponent;

import java.util.List;

@Repository
public class ItemDaoImpl implements ItemDao {

	private static final Logger logger = LoggerFactory.getLogger(ItemDaoImpl.class);

	private static SessionFactory sessionFactory; 
	@Autowired
	public  void setSessionFactory(SessionFactory sf){
		sessionFactory = sf;
	}

	public int addItem(Item item){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			if (item.getIsComposed()){
				ItemComponent ic = new ItemComponent();
				ic.setParent(item);
				ic.setComponent((Item) session.load(Item.class, 1));
				ic.setQuantity(5);
				item.getItemComponents().add(ic);
				session.save(item);
				session.save(ic);
			} else 
				session.save(item);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			logger.error("", e);
		}finally {
			session.close(); 
		}
		logger.info("Item saved successfully, details="+item);
		return item.getId();
	}

	public void updateItem(Item item){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.update(item); 
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			logger.error("", e);
		}finally {
			session.close(); 
		}
	}

	@SuppressWarnings("unchecked")
	public List<Item> listItems( ){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Item> items = null;
		try{
			tx = session.beginTransaction();
			items = (List<Item>) session.createQuery("FROM Item").getResultList();

			/*for (Iterator iterator = 
					items.iterator(); iterator.hasNext();){
				Item item = (Item) iterator.next(); 
				System.out.print("Nazwa: " + item.getName()); 
				System.out.println("  czy z³o¿ony: " + item.getIsComposed()); 
			}
			 */
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			logger.error("", e); 
		}finally {
			session.close(); 
		}
		return items;
	}

	public void removeItem(int i){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Item item = (Item) session.load(Item.class, i);
			List<ItemComponent> icomponents = item.getItemComponents();
			for (ItemComponent ic : icomponents)
				session.delete(ic);
			session.delete(item); 
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			logger.error("", e);
		}finally {
			session.close(); 
		}
	}
	public Item getItemById(int i){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Item item = null;
		try{
			tx = session.beginTransaction();
			item = (Item) session.load(Item.class, i);
			item.getId();//just get any field - it is lazyloadig! if you get fields outside session life then you get error
			List<ItemComponent> ic = item.getItemComponents();
			for (ItemComponent icm : ic){
				icm.getId();
			}
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			logger.error("", e);
		}finally {
			session.close(); 
		}
		logger.info("Item retrieved, details="+item);
		return item;
	}

	public void removeItemComponent(int component){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			ItemComponent ic = (ItemComponent) session.load(ItemComponent.class, component);
			Item item = ic.getParent();
			if (item.getItemComponents().size() == 1){//we are removing last component
				item.setIsComposed(false);
				session.update(item);
			}
			session.delete(ic);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			logger.error("", e);
		}finally {
			session.close(); 
		}
	}

	public List<ItemComponent> listItemComponents(Item item){
		return item.getItemComponents();
	}

	public ItemComponent getItemComponentById(int id){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		ItemComponent ic = null;
		try{
			tx = session.beginTransaction();
			ic = (ItemComponent) session.load(ItemComponent.class, id);
			ic.getComponent();
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			logger.error("", e);
		}finally {
			session.close(); 
		}
		logger.info("ItemComponent retrieved, details="+ic);
		return ic;
	}

	public int addItemComponent(ItemComponent ic){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			
			int i = ic.getParent().getId();
			Item item = (Item) session.load(Item.class, i);
			System.out.println("form itemDao.addItemComp::"+ic);
			System.out.println("form itemDao.addItemComp - item::"+item);
			if (ic.getId() == 0){
				session.persist(ic);
			}
			else{
				session.update(ic);
			}
			if (!item.getIsComposed()){
				item.setIsComposed(true);
				session.update(item);
			}
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			logger.error("", e);
		}finally {
			session.close(); 
		}
		logger.info("Item saved successfully, details="+ic);
		return ic.getId();
	}

}
