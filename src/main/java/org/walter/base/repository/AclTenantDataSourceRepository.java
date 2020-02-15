package org.walter.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.walter.base.entity.JpaAclTenantDataSource;

public interface AclTenantDataSourceRepository extends JpaRepository<JpaAclTenantDataSource, Long>{

}
