package org.walter.base.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.walter.base.entity.CustomUser;

import java.util.Optional;

@Component
public class UsernameAuditorAware implements AuditorAware<String> {
	
	public static final String ANONYMOUS_USER_NAME = "*ANONYMOUS";
	
	@Override
	public Optional<String> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    if (authentication == null || !authentication.isAuthenticated()) {
	      return Optional.of(ANONYMOUS_USER_NAME);
	    }

	    String username = ((CustomUser) authentication.getPrincipal()).getUsername();
	    return Optional.ofNullable(username);
	}
}
