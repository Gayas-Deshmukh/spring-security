package spring.security.config.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import spring.security.config.dto.SignUpDTO;

@Service
public class SignUpDAOImpl implements SignUpDAO
{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void saveUser(SignUpDTO signUpDTO) 
	{
		String query1 = "insert into users values (?,?,?)";
		String query2 = "insert into authorities values (?,?)";

		jdbcTemplate.update(query1, signUpDTO.getUsername(), signUpDTO.getPassword(), 1);
		jdbcTemplate.update(query2, signUpDTO.getUsername(), "USER");

	}

}
