package pl.essay.itemMaker.model;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Tester {
	private static SessionFactory factory; 

	public static void main(String[] args) {

		try{
			factory = new Configuration()
					.configure()
					//.addAnnotatedClass(Item.class)
					.buildSessionFactory();
		}catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}
		Tester t = new Tester();
		
		Integer i1 = t.addItem(new Item("piasek",false));
		Integer i2 = t.addItem(new Item("woda",false));
		Item item3 = new Item("mleko",false);
		Integer i3 = t.addItem(item3);
		t.listItems();
		t.deleteItem(item3);
		System.out.println("after delete");
		t.listItems();
	}

	public Integer addItem(Item item){
		Session session = factory.openSession();
		Transaction tx = null;
		Integer itemID = null;
		try{
			tx = session.beginTransaction();
			itemID = (Integer) session.save(item); 
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return itemID;
	}


	public void listItems( ){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         List items = session.createQuery("FROM Item").getResultList(); 
	         for (Iterator iterator = 
	                           items.iterator(); iterator.hasNext();){
	            Item item = (Item) iterator.next(); 
	            System.out.print("Nazwa: " + item.getName()); 
	            System.out.println("  czy z³o¿ony: " + item.getIsComposed()); 
	         }
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }

	public void deleteItem(Item item){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction(); 
	         session.delete(item); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	
		/*
			      ManageEmployee ME = new ManageEmployee();

      Integer empID1 = ME.addEmployee("Zara", "Ali", 1000);
      Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
      Integer empID3 = ME.addEmployee("John", "Paul", 10000);

      ME.listEmployees();

      ME.updateEmployee(empID1, 5000);

      ME.deleteEmployee(empID2);

      ME.listEmployees();
   }

   public Integer addEmployee(String fname, String lname, int salary){
      Session session = factory.openSession();
      Transaction tx = null;
      Integer employeeID = null;
      try{
         tx = session.beginTransaction();
         Employee employee = new Employee(fname, lname, salary);
         employeeID = (Integer) session.save(employee); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return employeeID;
   }
   public void listEmployees( ){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         List employees = session.createQuery("FROM Employee").list(); 
         for (Iterator iterator = 
                           employees.iterator(); iterator.hasNext();){
            Employee employee = (Employee) iterator.next(); 
            System.out.print("First Name: " + employee.getFirstName()); 
            System.out.print("  Last Name: " + employee.getLastName()); 
            System.out.println("  Salary: " + employee.getSalary()); 
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
   public void updateEmployee(Integer EmployeeID, int salary ){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         Employee employee = 
                    (Employee)session.get(Employee.class, EmployeeID); 
         employee.setSalary( salary );
		 session.update(employee); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
   public void deleteEmployee(Integer EmployeeID){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         Employee employee = 
                   (Employee)session.get(Employee.class, EmployeeID); 
         session.delete(employee); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
		 */

}
