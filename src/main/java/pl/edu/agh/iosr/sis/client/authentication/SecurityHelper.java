package pl.edu.agh.iosr.sis.client.authentication;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityHelper {

    public static boolean hasUserRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ( authentication == null ) {
            return false;
        }

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for ( GrantedAuthority grantedAuthority : authorities ) {
            if ( role.equals(grantedAuthority.getAuthority()) ) {
                return true;
            }
        }
        return false;
    }

    public static String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public static Collection<? extends GrantedAuthority> getAuthorities() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }

}
