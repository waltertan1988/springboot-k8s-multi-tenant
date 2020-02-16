package org.walter.app.controller.fund;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.walter.app.entity.fund.JpaFundAccount;
import org.walter.app.repository.fund.FundAccountRepository;
import org.walter.base.entity.JpaAclUser;
import org.walter.base.repository.AclUserRepository;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/fund")
@Transactional
public class FundController {
    @Autowired
    private AclUserRepository aclUserRepository;
    @Autowired
    private FundAccountRepository fundAccountRepository;

    @GetMapping("/listFundAccount")
    public List<JpaFundAccount> listFundAccount() {
        List<JpaFundAccount> jpaFundAccounts = fundAccountRepository.findAll();
        for (JpaFundAccount jpaFundAccount : jpaFundAccounts) {
            log.info(">>>>>> jpaFundAccount: {}", jpaFundAccount);
        }
        return jpaFundAccounts;
    }

    @GetMapping("/listAclUser")
    public List<JpaAclUser> listAclUser() {
        List<JpaAclUser> list = aclUserRepository.findAll();
        for (JpaAclUser jpaAclUser : list) {
            log.info(">>>>>> jpaAclUser: {}", jpaAclUser);
        }
        return list;
    }
}
