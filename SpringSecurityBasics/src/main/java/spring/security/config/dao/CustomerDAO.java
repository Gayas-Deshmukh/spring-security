package spring.security.config.dao;

import spring.security.config.dto.Customer;

public interface CustomerDAO 
{
   Customer findCustomerByCustomerName(String name);
}
