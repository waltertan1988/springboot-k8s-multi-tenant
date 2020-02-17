package org.walter.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.walter.base.entity.JpaAclUserTenant;

import java.util.List;

public interface AclUserTenantRepository extends JpaRepository<JpaAclUserTenant, Long>{

    List<JpaAclUserTenant> findAllByTenantId(String tenantId);
}
