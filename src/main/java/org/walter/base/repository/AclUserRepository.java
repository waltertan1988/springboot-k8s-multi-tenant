package org.walter.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.walter.base.entity.JpaAclUser;

import java.util.List;

public interface AclUserRepository extends JpaRepository<JpaAclUser, Long>{

	@Query("FROM JpaAclUser o WHERE o.username=:usernameOrMobile OR o.mobile=:usernameOrMobile")
	public JpaAclUser findByUsernameOrMobile(String usernameOrMobile);
	
	public List<JpaAclUser> findByUserRealName(String userRealName);
}
