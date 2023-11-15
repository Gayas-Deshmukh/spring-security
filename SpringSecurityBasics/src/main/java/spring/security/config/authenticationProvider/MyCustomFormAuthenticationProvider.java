package spring.security.config.authenticationProvider;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import spring.security.config.dao.CustomerDAOImpl;
import spring.security.config.dto.Customer;

@Component
public class MyCustomFormAuthenticationProvider implements AuthenticationProvider 
{
	@Autowired
	CustomerDAOImpl customerDAOImpl;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException 
	{
		// Retrieve username & password entered by user
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		// fetch the user with username
		Customer customer = customerDAOImpl.findCustomerByCustomerName(username);
		
		if (customer != null)
		{
			// if user found with given username then check the password
			boolean matches = passwordEncoder.matches(password, customer.getPassword());
			
			if (matches)
			{
				// if password matches then create new Authentication(UsernamePasswordAuthenticationToken) obj & return it 
				ArrayList<GrantedAuthority> grantedAuthority = new ArrayList<GrantedAuthority>();
				grantedAuthority.add(new SimpleGrantedAuthority(customer.getRoles()));
				
				return new UsernamePasswordAuthenticationToken(username, password, grantedAuthority);
			}
			else
			{
				throw new BadCredentialsException("Invalid username or password");
			}
		}
		else
		{
			throw new BadCredentialsException("user does not exists");
		}		
	}

	@Override
	public boolean supports(Class<?> authentication) 
	{
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
