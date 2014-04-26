package pl.edu.agh.iosr.sis.client.interceptors;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import pl.edu.agh.iosr.sis.client.authentication.SecurityHelper;

public class AdminInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Collection<? extends GrantedAuthority> authorities = SecurityHelper.getAuthorities();
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");

		if ( authorities.contains(authority) ) {
			return true;
		}

		String redirect = createRedirectUrl(request, "/error/denied");
		response.sendRedirect(redirect);
		return false;
	}

	private String createRedirectUrl(HttpServletRequest request, String redirect) {
		if ( redirect.startsWith("/") ) {
			return request.getContextPath() + redirect;
		} else {
			return redirect;
		}
	}
}
