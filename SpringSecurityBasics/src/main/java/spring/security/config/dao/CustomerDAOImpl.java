package spring.security.config.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import spring.security.config.dto.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Customer findCustomerByCustomerName(String name) 
	{
		String query = "select * from customer where username=?";
		
		List<Customer> customers = jdbcTemplate.query(query, new String [] {name}, new BeanPropertyRowMapper<>(Customer.class));
		
		return (customers != null && !customers.isEmpty()) ? customers.get(0) : null;
	}
}


/*
 * BeanPropertyRowMapper
 * 
 * RowMapper implementation that converts a row into a new instanceof the specified mapped target class. 
 * The mapped target class must be atop-level class and it must have a default or no-arg constructor.
 * Column values are mapped based on matching the column name as obtained from result setmeta-data to public setters for the corresponding properties. 
 * The names are matched eitherdirectly or by transforming a name separating the parts with underscores to the same nameusing "camel" case. 
 * 
 */

