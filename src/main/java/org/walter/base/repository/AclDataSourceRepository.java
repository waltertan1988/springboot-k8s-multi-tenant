package org.walter.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.walter.base.entity.JpaAclDataSource;

public interface AclDataSourceRepository extends JpaRepository<JpaAclDataSource, Long>{

}
