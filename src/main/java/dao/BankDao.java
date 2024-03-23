package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import Dto.Bank_Account;

public class BankDao {
	
	 EntityManagerFactory entitymanagerfactory=Persistence.createEntityManagerFactory("dev");
	  EntityManager entitymanager=entitymanagerfactory.createEntityManager();
	  EntityTransaction entitytransaction=entitymanager.getTransaction();
	  public void save_account(Bank_Account bank_account)
	  {
		  entitytransaction.begin();
		  entitymanager.persist(bank_account);
		  entitytransaction.commit();
	  }
	public List<Bank_Account> fetchAll() {
		// TODO Auto-generated method stub
		List<Bank_Account> list = entitymanager.createQuery("select x from Bank_Account x").getResultList();
		return list;
	}
	public Bank_Account find(Long ac_number) 
	{
		Bank_Account bank_Account = entitymanager.find(Bank_Account.class, ac_number);
		return bank_Account;
		
	}
	public void update_the_details(Bank_Account bank_Account) {
		entitytransaction.begin();
		entitymanager.merge(bank_Account);
		entitytransaction.commit();
	}
	public void update_status_detail(Bank_Account bank_Account) {
		// TODO Auto-generated method stub
		
	}

}