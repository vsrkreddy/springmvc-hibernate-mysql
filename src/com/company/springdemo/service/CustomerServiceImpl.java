package com.company.springdemo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.springdemo.dao.CustomerDAO;
import com.company.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	//inject DAO to Service layer
	//use @Autowired
	
	@Autowired
	private CustomerDAO customerDAO;
	
	
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		// TODO Auto-generated method stub
		return customerDAO.getCustomers();
	}



	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {
		//delegate calls to DAO
		customerDAO.saveCustomer(theCustomer);
		
	}



	@Override
	@Transactional
	public Customer getCustomer(int theId) {
		// TODO Auto-generated method stub
		return customerDAO.getCustomer(theId);
	}



	@Override
	public void deleteCustomer(int theId) {
		// TODO Auto-generated method stub
		customerDAO.deleteCustomer(theId);
	}

}
