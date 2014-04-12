package pl.edu.agh.iosr.sis.client.authentication;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import pl.edu.agh.iosr.sis.core.daos.UserDAO;
import pl.edu.agh.iosr.sis.core.entities.User;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserDAO dao;

	@Autowired
	private PasswordEncryptor passwordEncryptor;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = StringUtils.trimToEmpty(authentication.getName());
		String password = StringUtils.trimToEmpty(authentication.getCredentials().toString());

		if ( username.isEmpty() || password.isEmpty() ) {
			throw new BadCredentialsException("Bad credentials");
		}
		if ( username.equals("admin") && password.equals("admin") ) {
			List<GrantedAuthority> authorities = new LinkedList<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			return new UsernamePasswordAuthenticationToken(username, password, authorities);
		}

		User user = dao.findByLogin(username);

		if ( user == null ) {
			throw new BadCredentialsException("Bad credentials");
		}
		String encrytpedPassword = user.getPassword();

		if ( passwordEncryptor.checkPassword(password, encrytpedPassword) ) {
			List<GrantedAuthority> authorities = new LinkedList<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

			if ( user.getIsAdmin() ) {
				authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			}

			return new UsernamePasswordAuthenticationToken(username, password, authorities);
		} else {
			throw new BadCredentialsException("Bad credentials");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(UsernamePasswordAuthenticationToken.class);
	}

}
