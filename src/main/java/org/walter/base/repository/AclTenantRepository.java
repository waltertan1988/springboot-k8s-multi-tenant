package org.walter.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.walter.base.entity.JpaAclTenant;

public interface AclTenantRepository extends JpaRepository<JpaAclTenant, Long>{

}
