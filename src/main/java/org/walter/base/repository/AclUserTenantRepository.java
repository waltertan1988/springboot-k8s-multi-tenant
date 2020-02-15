package org.walter.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.walter.base.entity.JpaAclUserTenant;

public interface AclUserTenantRepository extends JpaRepository<JpaAclUserTenant, Long>{

}
