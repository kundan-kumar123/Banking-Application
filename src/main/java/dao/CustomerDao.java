package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import Dto.Customer;
  

public class CustomerDao {

	// TODO Auto-generated method stub
	EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("dev");
	EntityManager entitymanager = entitymanagerfactory.createEntityManager();
	EntityTransaction entitytransaction = entitymanager.getTransaction();

	public void save(Customer customer) {
		entitytransaction.begin();
		entitymanager.persist(customer);
		entitytransaction.commit();
	}

	public List<Customer> check1(String email) {
		List<Customer> list1 = entitymanager.createQuery("select x from Customer x where Email =?1")
				.setParameter(1, email).getResultList();
		return list1;
	}

	public List<Customer> check2(long mob_no) {
		List<Customer> list = entitymanager.createQuery("select x from Customer x where mobile =?1")
				.setParameter(1, mob_no).getResultList();
		return list;
	}
	public Customer login(int customerid)
	{
		Customer customer=entitymanager.find(Customer.class, customerid);
		return customer;
	}

	public void update(Customer customer) {
		// TODO Auto-generated method stub
		entitytransaction.begin();
		  entitymanager.merge(customer);
		  entitytransaction.commit();
		
	}

}
