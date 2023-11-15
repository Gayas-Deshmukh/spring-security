package spring.security.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import spring.security.config.dao.CustomerDAO;
import spring.security.config.dto.Customer;

@Service
public class CustomerUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		Customer customer = customerDAO.findCustomerByCustomerName(username);
		
		if (customer == null)
		{
			throw new UsernameNotFoundException(username + " not found.");
		}
		
		UserDetails userDetails = User.withUsername(customer.getUsername())
										.password(customer.getPassword())
										.authorities(customer.getRoles())
										.build();
		
		
		return userDetails;
	}
}
