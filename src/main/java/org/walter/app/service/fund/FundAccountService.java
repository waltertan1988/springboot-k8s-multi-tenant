package org.walter.app.service.fund;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.walter.app.entity.fund.JpaFundAccount;
import org.walter.app.repository.fund.FundAccountRepository;

import java.math.BigDecimal;

@Slf4j
@Service
public class FundAccountService {
    @Autowired
    private FundAccountRepository fundAccountRepository;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public BigDecimal fundAccountTransfer(String username, String accountType, BigDecimal transferAmount){
        JpaFundAccount jpaFundAccount = fundAccountRepository.findOneByUsernameAndAccountType(username, accountType);
        BigDecimal originalBalanceAmount = jpaFundAccount.getBalanceAmount();
        jpaFundAccount.setBalanceAmount(originalBalanceAmount.add(transferAmount));
        fundAccountRepository.save(jpaFundAccount);
        return originalBalanceAmount;
    }
}
