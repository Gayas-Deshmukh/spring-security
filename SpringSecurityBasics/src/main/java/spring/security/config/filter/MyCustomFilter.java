package spring.security.config.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class MyCustomFilter implements Filter
{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		String username = request.getParameter("username");	
		String password = request.getParameter("password");
		
		System.out.println("UserName " + username);
		System.out.println("PassWord " + password);
		
		// we can write our custom logic here
		
		chain.doFilter(request, response);
	}

}
