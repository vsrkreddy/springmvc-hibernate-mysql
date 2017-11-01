package com.company.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.company.springdemo.entity.Customer;



@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	//inject session factory to DAO
	//session factory is defined in config file (spring-mvc-demo-servlet.xml
	//session factory has a bean id of (id= sessionFactory)
	//you inject Dependency Injection of session factory to CustomerDAOImpl using @Autowired
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//let spring manage your transactions
	@Override
	 
	public List<Customer> getCustomers() {
		
		//get current hibernate session
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create query
		//sort by first name
		
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by firstName", Customer.class);
		
		//execute query and get result
		
		List<Customer> customers = theQuery.getResultList();
		//return results
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		// get the hibernate session 
		Session currentSession = sessionFactory.getCurrentSession();
		
		
		//save the customer to db
//		currentSession.save(theCustomer);
		
		
		// save or update
		currentSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {
		// get the hibernate session 
				Session currentSession = sessionFactory.getCurrentSession();
		//read the object from db using the primary key
				Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		// get the hibernate session 
		Session currentSession = sessionFactory.getCurrentSession();
		//delete the object from db using the primary key
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		theQuery.executeUpdate();
		
	}

	
}
