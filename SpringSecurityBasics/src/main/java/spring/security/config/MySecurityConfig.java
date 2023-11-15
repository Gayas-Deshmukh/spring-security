package spring.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import spring.security.config.authenticationProvider.MyCustomFormAuthenticationProvider;
import spring.security.config.filter.MyCustomFilter;
import spring.security.config.service.CustomerUserDetailsServiceImpl;

// This class is going to help you to create Spring Security filter chain
// Custom Spring security filters, we will able to create in the SecurityInitializer class that is extending AbstractSecurityWebApplicationInitializer
// AbstractSecurityWebApplicationInitializer also provide some default filters
/*
 * Security filter chain: [
                         WebAsyncManagerIntegrationFilter
                         SecurityContextPersistenceFilter
                         HeaderWriterFilter
                         CsrfFilter
                         LogoutFilter
                         UsernamePasswordAuthenticationFilter
                         DefaultLoginPageGeneratingFilter
                         DefaultLogoutPageGeneratingFilter
                         BasicAuthenticationFilter
                         RequestCacheAwareFilter
                         SecurityContextHolderAwareRequestFilter
                         AnonymousAuthenticationFilter
                         SessionManagementFilter
                         ExceptionTranslationFilter
                         FilterSecurityInterceptor
                       ]
*/
// Using @EnableWebSecurity annotation we can enable web security to an application
@EnableWebSecurity(debug = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter
{

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private CustomerUserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private MyCustomFormAuthenticationProvider authenticationProvider;
	
	@Autowired
	private MyCustomFilter customFilter;
	
	// It is the authentication provider use to authenticate given details (i.e username & password) with in memory details
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{   /*
		* 1. In memory authentication
		auth
			.inMemoryAuthentication()  // Using in memory authentication
			.withUser("gayas")
			//.password("Gayas@123")  // useful when using NoOpPasswordEncoder
			.password("$2a$12$cMayEQzIqe10zkwzjGfLouVGT36.xwMX4BPBzYsMwuYb7Rnwtxy0.") // useful when using BCryptPasswordEncoder
			.roles("admin");
		*/
		
		/*
		 * 2. Load & authenticate user from database
		 *
		auth
			.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(getPasswordEncoder());
		*/
		
		/*
		 * 3.Authenticating user by loading user details from custom table (customer table)
		 *
		auth.userDetailsService(userDetailsServiceImpl)
			.passwordEncoder(getPasswordEncoder());
		*/
		
		/*
		 * 4. Validation user using CustomAuthenticationProvider
		 */
		auth.authenticationProvider(authenticationProvider);
			
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder()
	{
		/** NoOpPasswordEncoder **/
		// NoOpPasswordEncoder is implementation class of  PasswordEncoder interface
		// NoOpPasswordEncoder is deprecated because this PasswordEncoder is not secured
		// This PasswordEncoder is provided for legacy and testing purposes only and is not considered secure.	 
		// A password encoder that does nothing. Useful for testing where working with plain text passwords may be preferred.
		 return NoOpPasswordEncoder.getInstance();
		
		/** BCryptPasswordEncoder **/
		// Implementation of PasswordEncoder that uses the BCrypt strong hashing function.
		//return new BCryptPasswordEncoder();
	}
	
	// Override this method for to the customize the type of authentication i.e formLogin, http basic login
	// by default, if you login from browser then form based login will get enabled & if you login from any web client like RestTemplate or Postman then basic authentication will get enabled  
	// we can override default types of authentication
	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		
		http
		//.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class)
		.addFilterAfter(customFilter, UsernamePasswordAuthenticationFilter.class)
		.authorizeRequests()
		.antMatchers("/admin-dash").hasAuthority("ADMIN")
		.antMatchers("/user-dash").hasAuthority("USER")
		.antMatchers("/").hasAnyAuthority("Coder")
		.and()
		.formLogin().loginPage("/custom-login").permitAll()
		.and()
		.httpBasic()
		.and()
		.logout().permitAll()
		.and().exceptionHandling().accessDeniedPage("/access-denied");
		
		/*
		 *1. Enable or disable Basic & form based authentication
		http
			.authorizeRequests()
			.anyRequest()
			.authenticated()
			.and()
			.formLogin();
			//.and()
			//.httpBasic(); 
	
		 */
		
		/*
		 * 2. Authorizing & permitting specific requests
		http
			.authorizeRequests()
			.antMatchers("/home").authenticated()
			.antMatchers("/details").authenticated()
			.antMatchers("/city").permitAll()
			.and()
			.formLogin()
			.and()
			.httpBasic();
		*/
		
		/*
		 * 3. Custom Login & Logout implementation
		 *
		http
		.authorizeRequests()
		.antMatchers("/home").authenticated()
		.antMatchers("/details").authenticated()
		.antMatchers("/city", "/custom-login").permitAll()
		.and()
		// we can define custom login page url & custom login processing url as well
		.formLogin().loginPage("/custom-login")//.loginProcessingUrl("/process-url") - where to submit form
		.and()
		.httpBasic()
		.and()
		// we are enabled custom logout , we can also specify custom logout url
		.logout();//.logoutUrl("/custom-logout");
		*/
		
		/*
		 * 4. Allowing logged in user to access endpoint based on role assigned & show access denied page
		 *
		http
		.authorizeRequests()
		.antMatchers("/admin-dash").hasAuthority("ADMIN")
		.antMatchers("/user-dash").hasAuthority("USER")
		.antMatchers("/").hasAnyAuthority("Coder")
		.and()
		.formLogin().loginPage("/custom-login").permitAll()
		.and()
		.httpBasic()
		.and()
		.logout().permitAll()
		.and().exceptionHandling().accessDeniedPage("/access-denied");
		*/
		
		/*
		 * 5. Add custom filer
		 *
		 */
		http
		//.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class)
		.addFilterAfter(customFilter, UsernamePasswordAuthenticationFilter.class)
		.authorizeRequests()
		.antMatchers("/admin-dash").hasAuthority("ADMIN")
		.antMatchers("/user-dash").hasAuthority("USER")
		.antMatchers("/").hasAnyAuthority("Coder")
		.and()
		.formLogin().loginPage("/custom-login").permitAll()
		.and()
		.httpBasic()
		.and()
		.logout().permitAll()
		.and().exceptionHandling().accessDeniedPage("/access-denied");
		
	}
}
