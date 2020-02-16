package org.walter.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.walter.base.entity.JpaAclTenantDataSource;

import java.util.List;

public interface AclTenantDataSourceRepository extends JpaRepository<JpaAclTenantDataSource, Long>{

    List<JpaAclTenantDataSource> findAllByDataSourceId(String dataSourceId);
}
