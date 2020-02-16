package org.walter.app.controller.fund;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.walter.app.entity.fund.JpaFundAccount;
import org.walter.app.repository.fund.FundAccountRepository;
import org.walter.app.service.fund.FundService;
import org.walter.base.entity.JpaAclUser;
import org.walter.base.repository.AclUserRepository;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/fund")
public class FundController {
    @Autowired
    private AclUserRepository aclUserRepository;
    @Autowired
    private FundAccountRepository fundAccountRepository;
    @Autowired
    private FundService fundService;

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

    @GetMapping("/listObject")
    public List<Object> listObject(){
        List<Object> list = new ArrayList<>();
        list.addAll(listFundAccount());
        list.addAll(listAclUser());
        return list;
    }

    @GetMapping("/deposit")
    public String deposit(HttpServletRequest request) throws Exception {
        String username = "0009785";
        if("B".equals(request.getParameter("tenantId"))){
            username = "walter";
        }
        String accountType = "1";
        String billCode = "deposit";
        BigDecimal transferAmount = new BigDecimal(100);
        Boolean isFail = Boolean.valueOf(request.getParameter("fail"));
        fundService.deposit(username, accountType, billCode, transferAmount, isFail);
        return "success";
    }
}
