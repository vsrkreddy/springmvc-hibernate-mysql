package com.company.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.springdemo.dao.CustomerDAO;
import com.company.springdemo.entity.Customer;
import com.company.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	/*
	//Need to inject DAOImpl to Controller
	//so here we try to inject dependency from CustomerDAO to customerController Class
	//so here spring will scan for a component that implements CustomerDAO --> CustomerImplDAO
	//to inject this we use keyword @Autowired
	
	@Autowired
	private CustomerDAO customerDAO;
	
*/
	
	//inject service layer to controller
	//inject customerService to customer controller
	//use @Autowired
	@Autowired
	private CustomerService customerService;
	
	
	
	@GetMapping("/list")
	public String listCustomers(Model model){
		
		//get customers from DAO
		
//		List<Customer> theCustomers = customerDAO.getCustomers();
		List<Customer> theCustomers = customerService.getCustomers();
		
		// add that to model
		
		model.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model){
		//create model to bind the data
		Customer theCustomer = new Customer();
		
		model.addAttribute("customerformdata", theCustomer);
		
		
		return "customer-form";
	}
	
	//input form data comes to controller (there action = saveCustomer so map it)
	//(form modelAttribute="customerformdata") use this to get the model
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customerformdata") Customer theCustomer){
		
		//save the customer using our service
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	
	//requestmapping name is defined in list-customer.jsp in line43 as name="customerId"
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model model){
		
		//get the customer from service
		Customer theCustomer = customerService.getCustomer(theId);
		
		//set customer as model attribute to pre populate the form
		// the name should match with model attribute of form data other wise you get an error
		model.addAttribute("customerformdata", theCustomer);
		
		//send over to our form
		
		return "customer-form";
		
	}
	
	//delete link setup
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId){
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
}
