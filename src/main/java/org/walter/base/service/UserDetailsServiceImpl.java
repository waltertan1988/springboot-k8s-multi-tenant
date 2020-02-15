package org.walter.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.walter.base.repository.AclUserRepository;
import org.walter.base.entity.CustomUser;
import org.walter.base.entity.JpaAclUser;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private AclUserRepository aclUserRepository;

	@Override
	public UserDetails loadUserByUsername(String usernameOrMobile) throws UsernameNotFoundException {
		
		JpaAclUser sysUser = aclUserRepository.findByUsernameOrMobile(usernameOrMobile);
		if(sysUser == null){
			throw new UsernameNotFoundException(String.format("用户名或手机号%s不存在", usernameOrMobile));
		}
		
		Set<GrantedAuthority> authoritySet = new HashSet<>();
		authoritySet.add(new SimpleGrantedAuthority("ROLE_USER"));
		
//		AuthorityUtils
		CustomUser customUser = new CustomUser(sysUser.getUsername(), sysUser.getPassword(), sysUser.isEnabled(), !sysUser.isExpired(), !sysUser.isPasswordExpired(), !sysUser.isLocked(), authoritySet);
		customUser.setUserRealName(sysUser.getUserRealName());
		customUser.setGender(sysUser.getGender());
		customUser.setMobile(sysUser.getMobile());
		
		return customUser;
	}
}
