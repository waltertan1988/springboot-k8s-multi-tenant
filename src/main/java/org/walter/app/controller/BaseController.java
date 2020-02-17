package org.walter.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.walter.base.entity.JpaAclUser;
import org.walter.base.entity.JpaAclUserTenant;
import org.walter.base.repository.AclUserRepository;
import org.walter.base.repository.AclUserTenantRepository;
import org.walter.base.service.MultiTenantContextHolder;

import java.util.List;

@Slf4j
public class BaseController {
    @Autowired
    private AclUserRepository aclUserRepository;
    @Autowired
    private AclUserTenantRepository aclUserTenantRepository;

    protected JpaAclUserTenant findAnyUserTenant(){
        String tenantId = MultiTenantContextHolder.getContextTenantId();
        List<JpaAclUserTenant> list = aclUserTenantRepository.findAllByTenantId(tenantId);
        if(CollectionUtils.isEmpty(list)){
            return new JpaAclUserTenant();
        }else{
            return list.get(0);
        }
    }

    protected List<JpaAclUser> listAclUser() {
        List<JpaAclUser> list = aclUserRepository.findAll();
        for (JpaAclUser jpaAclUser : list) {
            log.info(">>>>>> jpaAclUser: {}", jpaAclUser);
        }
        return list;
    }
}
