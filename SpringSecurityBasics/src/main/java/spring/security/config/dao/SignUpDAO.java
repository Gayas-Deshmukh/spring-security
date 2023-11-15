package spring.security.config.dao;

import spring.security.config.dto.SignUpDTO;

public interface SignUpDAO 
{
	void saveUser(SignUpDTO signUpDTO);
}
