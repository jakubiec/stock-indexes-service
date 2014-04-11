package pl.edu.agh.iosr.sis.client.authentication;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String username = StringUtils.trimToEmpty(authentication.getName());
		String password = StringUtils.trimToEmpty(authentication.getCredentials().toString());
		
		if (username.isEmpty() || password.isEmpty()) {
			throw new BadCredentialsException("Bad credentials");
		}
		
		List<GrantedAuthority> authorities = new LinkedList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"))
		;
		System.out.println("u: " + username);
		System.out.println("p: " + password);
		
		return new UsernamePasswordAuthenticationToken(username, password, authorities);
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	

}
